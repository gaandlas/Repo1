package com.spe.aws

import com.kms.katalon.core.annotation.Keyword

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider
import software.amazon.awssdk.core.ResponseInputStream
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.model.GetObjectResponse

public class S3 {

	@Keyword
	public void downloadFile(String bucketName, String keyName, String destination) {
		S3Client s3Client = S3Client.builder()
				.region(Region.US_EAST_1)
				.credentialsProvider(ProfileCredentialsProvider.create("non-prod"))
				.build();

		GetObjectRequest objectRequest = GetObjectRequest.builder()
				.bucket(bucketName)
				.key(keyName)
				.build();



		ResponseInputStream<GetObjectResponse> response = s3Client.getObject(objectRequest);

		BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destination));

		byte[] buffer = new byte[4096];
		int bytesRead = -1;

		while ((bytesRead = response.read(buffer)) !=  -1) {
			outputStream.write(buffer, 0, bytesRead);
		}

		response.close();
		outputStream.close();
	}
}
