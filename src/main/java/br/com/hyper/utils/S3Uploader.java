package br.com.hyper.utils;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;

@Component
public class S3Uploader {

    public static void saveTrackOnBucket(String trackName) {
        String bucketName = "hyper-tracks";
        String keyName = trackName;
        String filePath = "/genre";

        S3Client s3Client = S3Client.builder()
                .region(Region.US_EAST_1)
                .build();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();

        PutObjectResponse response = s3Client.putObject(putObjectRequest, new File(filePath).toPath());
        System.out.println(response);
    }
}

