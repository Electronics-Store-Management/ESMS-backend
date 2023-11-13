package com.penguin.esms.utils;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.amazonaws.services.s3.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

public class AwsS3Client {
    private S3Client s3Client;
    private Region region;

    public AwsS3Client(Region awsRegion) {
        init(awsRegion);
        region = awsRegion;
    }

    public void init(Region awsRegion) {
        s3Client = S3Client.builder()
                .region(awsRegion)
                .credentialsProvider(ProfileCredentialsProvider.create("default"))
                .build();
    }

    public PutObjectResponse putObject(String bucketName, String key, MultipartFile file) throws IOException {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        return s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getInputStream().available()));
    }

    public PutObjectResponse updateObject(String bucketName, String key, MultipartFile file) throws IOException {
        return this.putObject(bucketName, key, file);
    }

    //get an object
    public void getObject(String bucketName, String objectKey) {
        try {
            GetObjectRequest objectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    .build();

            ResponseBytes<GetObjectResponse> responseResponseBytes = s3Client.getObjectAsBytes(objectRequest);

            byte[] data = responseResponseBytes.asByteArray();

            // Write the data to a local file.
            java.io.File myFile = new java.io.File("/Users/user/Desktop/hello.txt" );
            OutputStream os = new FileOutputStream(myFile);
            os.write(data);
            System.out.println("Successfully obtained bytes from an S3 object");
            os.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    //deleting an object
    public void deleteObject(String bucketName, String objectKey) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
    }

    public boolean doesObjectExistByDefaultMethod(String bucket, String key) {
        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();

            s3Client.headObject(headObjectRequest);

            System.out.println("Object exists");
            return true;
        } catch (S3Exception e) {
            if (e.statusCode() == 404) {
                System.out.println("Object does not exist");
                return false;
            } else {
                throw e;
            }
        }
    }
}
