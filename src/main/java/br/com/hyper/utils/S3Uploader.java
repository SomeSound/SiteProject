package br.com.hyper.utils;

import br.com.hyper.constants.ErrorCodes;
import br.com.hyper.exceptions.InvalidCollectionDataException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import br.com.hyper.enums.Genre;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class S3Uploader {

    public void saveTrackOnBucket(String artistUsername, Genre genrePath, MultipartFile file) {

        String secretString;
        try {

            SecretsManagerClient secretsManager = SecretsManagerClient.create();

            SecretsManagerClient client = SecretsManagerClient.builder()
                    .region(Region.US_EAST_1)
                    .build();

            GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                    .secretId("Access_Key/Hyper")
                    .build();

            secretsManager.getSecretValue(getSecretValueRequest);

            GetSecretValueResponse getSecretValueResponse;

            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
            secretString = getSecretValueResponse.secretString();

            secretsManager.close();

        } catch (Exception e) {
            throw new InvalidCollectionDataException(ErrorCodes.AWS_SECRETS_ERROR,
                    ErrorCodes.AWS_SECRETS_ERROR.getMessage());
        }


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode secretJson = objectMapper.readTree(secretString);

            String awsAccessKeyId = secretJson.get("aws_access_key_id").asText();
            String awsSecretAccessKey = secretJson.get("aws_secret_access_key").asText();
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(awsAccessKeyId , awsSecretAccessKey);

        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(awsCredentials);

        S3Client s3Client = S3Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentialsProvider)
                .build();

            String fileName = file.getOriginalFilename();

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket("hyper-tracks")
                    .key(artistUsername + "/" + genrePath + "/" + fileName)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            s3Client.close();
        } catch (Exception e) {
            throw new InvalidCollectionDataException(ErrorCodes.AWS_CONNECTION_ERROR,
                    ErrorCodes.AWS_CONNECTION_ERROR.getMessage());
        }
    }
}

