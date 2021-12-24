package com.beaconfire.week9day4housing.Util;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.beaconfire.week9day4housing.Domain.User;
import com.beaconfire.week9day4housing.Filter.JwtFilter;



@Component
public class AmazonS3Util {
	@Value("${amazons3.accesskey}")
	private String accesskey;

	@Value("${amazons3.secretkey}")
	private String secretkey;

	@Value("${amazons3.bucket}")
	private String bucket;

	private AmazonS3 amazonS3;

	@PostConstruct
	public void postConstruct() {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accesskey, secretkey);
		amazonS3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2)
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
	}

	public String uploadMultipartFile(HttpServletRequest httpServletRequest, MultipartFile file, String key)
			throws IOException {
		User user = JwtFilter.getUser(httpServletRequest);
		key = String.format("%d_%s_%s_%s", user.getId(), user.getUsername(), key, file.getOriginalFilename());

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(file.getSize());

		amazonS3.putObject(new PutObjectRequest(bucket, key, file.getInputStream(), objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucket, key);
		String url = amazonS3.generatePresignedUrl(urlRequest).toString();
//		return url;
		return url.substring(0, url.indexOf('?'));
	}
}
