package com.penguin.esms.services;

import com.penguin.esms.utils.AwsS3Client;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.regions.Region;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;

@Service
public class AmazonS3Service {
    @Value("${amazonProperties.bucket-name}")
    private String AWS_BUCKET;
    public Region AWS_REGION = Region.AP_SOUTHEAST_1;
    AwsS3Client s3Service;

    public AmazonS3Service() {
        s3Service = new AwsS3Client(AWS_REGION);
    }

    public boolean isExistFile(String fileName) {
        return s3Service.doesObjectExistByDefaultMethod(AWS_BUCKET, fileName);
    }

    public String addFile(MultipartFile file, String filename) throws IOException {
        s3Service.putObject(AWS_BUCKET, filename, file);
        return getObjectURL(filename);
    }


    public String updateFile(MultipartFile file, String filename) throws IOException {
        s3Service.putObject(AWS_BUCKET, filename, file);
        return getObjectURL(filename);
    }

    public void deleteFile(String fileName) {
        s3Service.deleteObject(AWS_BUCKET, fileName);
    }

    public String  getObjectURL(String objectKey) {
        return "https://" + AWS_BUCKET + ".s3.amazonaws.com/" + objectKey;
    }
}
