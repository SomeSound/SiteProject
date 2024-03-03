package br.com.hyper.utils;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;

public class S3Uploader {

    public static void main(String[] args) {
        String bucketName = "seu-bucket";
        String keyName = "seu-arquivo.txt";
        String filePath = "/caminho/do/seu-arquivo.txt";

        // Configurar o cliente S3
        S3Client s3Client = S3Client.builder()
                .region(Region.US_EAST_1) // Substitua pela região desejada
                .build();

        // Criar uma solicitação para fazer upload do arquivo
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();

        // Executar o upload do arquivo
        PutObjectResponse response = s3Client.putObject(putObjectRequest, new File(filePath).toPath());

        // Imprimir informações sobre o upload
        System.out.println("Arquivo enviado com sucesso. ETag: " + response.eTag());
    }
}

