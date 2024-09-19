package com.excel.empdetails.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
@Configuration
@EnableDynamoDBRepositories(basePackages = "com.excel.empdetails.service")
public class Configaraation {
	
//	 @Value("${amazon.dynamodb.endpoint}")
//	    private String amazonDynamoDBEndpoint;
//
//	    @Value("${amazon.aws.accesskey}")
//	    private String amazonAWSAccessKey;
//
//	    @Value("${amazon.aws.secretkey}")
//	    private String amazonAWSSecretKey;
//
//	    @Bean
//	    public AmazonDynamoDB amazonDynamoDB(AWSCredentialsProvider awsCredentialsProvider) {
//	        AmazonDynamoDB amazonDynamoDB
//	            = AmazonDynamoDBClientBuilder.standard()
//	            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, "ap-south-1"))
//	            .withCredentials(awsCredentialsProvider).build();
//	        return amazonDynamoDB;
//	    }
//
//	    @Bean
//	    public AWSCredentialsProvider awsCredentialsProvider() {
//	        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey));
//	    }
	

	 @Value("${amazon.dynamodb.endpoint}")
	    String endpoint;
	    @Value("${amazon.aws.accesskey}")
	    String accesskey;
	    @Value("${amazon.aws.secretkey}")
	    String secretkey;
	    @Value("${amazon.aws.region}")
	    String region;

	    @Bean
	    public AmazonDynamoDB amazonDynamoDB() {
	        return AmazonDynamoDBClientBuilder
	                .standard()
	                .withEndpointConfiguration(
	                        new AwsClientBuilder.EndpointConfiguration(endpoint,region))
	                .withCredentials(new AWSStaticCredentialsProvider(
	                        new BasicAWSCredentials(accesskey,secretkey)))
	                .build();
	    }

}
