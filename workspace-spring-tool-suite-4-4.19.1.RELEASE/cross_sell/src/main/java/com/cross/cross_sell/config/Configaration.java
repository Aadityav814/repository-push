package com.cross.cross_sell.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;


@Configuration
@EnableDynamoDBRepositories
(basePackages = "com.cross.cross_sell.service")
public class Configaration {
	
	
	
	
	

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
	
	
	
	
	
	
//	  @Bean
//	    public DynamoDBMapper dynamoDBMapper() {
//	        return new DynamoDBMapper(buildAmazonDynamoDB());
//	    }
//
//	    private AmazonDynamoDB buildAmazonDynamoDB() {
//	        return AmazonDynamoDBClientBuilder
//	                .standard()
//	                .withEndpointConfiguration(
//	                        new AwsClientBuilder.EndpointConfiguration(
//	                                "http://localhost:8000/",
//	                                "ap-south-1"
//	                        )
//	                )
//	                .withCredentials(
//	                        new AWSStaticCredentialsProvider(
//	                                new BasicAWSCredentials(
//	                                        "6vm6kb",
//	                                        "xc5fdt"
//	                                )
//	                        )
//	                )
//	                .build();
//	    }

	
	
	
	
	
	
	
//    @Bean
//    public DynamoDBMapper dynamoDBMapper() {
//        return new DynamoDBMapper(amazonDynamoDB());
//    }
//	
//	   @Value("${aws.dynamodb.endpoint}")
//	    private String amazonDynamoDBEndpoint;
//
//	    @Value("${aws.dynamodb.accessKey}")
//	    private String amazonAWSAccessKey;
//
//	    @Value("${aws.dynamodb.secretKey}")
//	    private String amazonAWSSecretKey;
//
//	    @Value("${aws.dynamodb.region}")
//	    private String region;
//	    
//	   
	
	
//	    @Bean
//	    public AmazonDynamoDB amazonDynamoDB() {
//	        return AmazonDynamoDBClientBuilder.standard()
//	                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, "ap-south-1"))
//	                .withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials()))
//	                .build();
//	    }
//
//	    @Bean
//	    @Primary
//	    public BasicAWSCredentials amazonAWSCredentials() {
//	        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
//	    }
	
	
	
//	    private AWSCredentialsProvider awsDynamoDBCredentials() {
//	        return new AWSStaticCredentialsProvider(
//	            new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey));
//	      }
//
//	      @Primary
//	      @Bean
//	      public DynamoDBMapperConfig dynamoDBMapperConfig() {
//	        return DynamoDBMapperConfig.DEFAULT;
//	      }
//
//	      @Bean
//	      @Primary
//	      public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB,
//	                                           DynamoDBMapperConfig config) {
//	        return new DynamoDBMapper(amazonDynamoDB, config);
//	      }
//
//	      @Bean
//	      public AmazonDynamoDB amazonDynamoDB() {
//
//	        return AmazonDynamoDBClientBuilder.standard()
//	            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, region))
//	            .withCredentials(awsDynamoDBCredentials()).build();
//	      }
	      
	      
	    
	    
	    
	    
	
	
	
//	 private final AmazonDynamoDB amazonDynamoDB;
//
//	    @Bean
//	    public DynamoDBMapperConfig dynamoDBMapperConfig() {
//	        return DynamoDBMapperConfig.DEFAULT;
//	    }
//
//	    @Bean
//	    public DynamoDBMapper dynamoDBMapper(DynamoDBMapperConfig config) {
//	        return new DynamoDBMapper(amazonDynamoDB, config);
//	    }
//
//	   
//
//	  
//
//	    @Bean
//	    public ModelMapper modelMapper() {
//	        return new ModelMapper();
//	    }
//
//	    // Assuming you have your own implementation for AmazonDynamoDB bean
//	    @Bean
//	    public AmazonDynamoDB amazonDynamoDB() {
//	        return AmazonDynamoDBClientBuilder.standard()
//	                .withCredentials(amazonAWSCredentialsProvider())
//	                .withEndpointConfiguration(
//	                        new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "ap-south-1"))
//	                .build();
//	    }
//
//	    private AWSCredentialsProvider amazonAWSCredentialsProvider() {
//	        return new AWSStaticCredentialsProvider(
//	                new BasicAWSCredentials("gtyd38", "s2s99"));
//	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

    
//    @Bean
//    public DynamoDBMapperConfig dynamoDBMapperConfig() {
//        return DynamoDBMapperConfig.DEFAULT;
//    }
//    
// 
//    
//    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
//		return new AWSStaticCredentialsProvider(
//				new BasicAWSCredentials("fakeMyKeyId", "fakeSecretAccessKey"));
//	}
// 
//	@Bean
//	public AmazonDynamoDB amazonDynamoDB() {
//		return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
//				.withEndpointConfiguration(
//						new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "ap-south-1"))
//				.build();
//	}
// 
//	@Bean
//	public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
//		return new DynamoDBMapper(amazonDynamoDB, config);
//	}
// 
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.headers().xssProtection().and().contentSecurityPolicy("form-action 'self'").and()
//				.addHeaderWriter(new StaticHeadersWriter("Expect-CT", "max-age=31536000, enforce"));
//		return http.build();
//	}
// 
//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() {
//		return web -> web.ignoring().requestMatchers("/**");
//	}
// 
//	@Bean
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}
// 
//}
    
    
    
    
    
    
    
    
    
    
    
  ///////////////////
//  @Bean
//    public AmazonDynamoDB amazonDynamoDB(AWSCredentialsProvider awsCredentialsProvider) {
//        AmazonDynamoDB amazonDynamoDB
//            = AmazonDynamoDBClientBuilder.standard()
//            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, "ap-south-1"))
//            .withCredentials(awsCredentialsProvider).build();
//        return amazonDynamoDB;
//    }
//
//    @Bean
//    public AWSCredentialsProvider awsCredentialsProvider() {
//        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey));
//    }
//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor() {
//        return new MethodValidationPostProcessor();
//    }
//
//    //	///////////////////
//    @Bean
//    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
//        return new DynamoDBMapper(amazonDynamoDB, config);
//    }
//
//    @Bean
//    public SecurityFilterAutoConfiguration filterChain(HttpSecurity http) throws Exception {
//        http.headers().xssProtection().and().contentSecurityPolicy("form-action 'self'").and()
//                .addHeaderWriter(new StaticHeadersWriter("Expect-CT", "max-age=31536000, enforce"));
//        return http.build();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers(requestMatcher());
//    }
//
//    @Bean
//    public StaticResourceServerWebExchange requestMatcher() {
//        return PathRequest.toStaticResources().atCommonLocations();
//    }
//
//    @Bean
//    public ModelMap modelMapper() {
//        return new ModelMap();
//    }
//
//
//
//}
	


//@Bean
//public MethodValidationPostProcessor methodValidationPostProcessor() {
//  return new MethodValidationPostProcessor();
//}

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
//	    public AmazonDynamoDB amazonDynamoDB() {
//	        return AmazonDynamoDBClientBuilder.standard()
//	                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, "ap-south-1")) // Set the appropriate region
//	                .withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials()))
//	                .build();
//	    }
//
//	    private BasicAWSCredentials amazonAWSCredentials() {
//	        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
//	    }

//    @Value("${amazon.dynamodb.endpoint}")
//    private String amazonDynamoDBEndpoint;
//
//    @Value("${amazon.aws.accesskey}")
//    private String amazonAWSAccessKey;
//
//    @Value("${amazon.aws.secretkey}")
//    private String amazonAWSSecretKey;
//
//    @Bean
//    public AmazonDynamoDB amazonDynamoDB() {
//        return AmazonDynamoDBClientBuilder.standard()
//                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, "ap-south-1"))
//                .withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials()))
//                .build();
//    }
//
//    @Bean
//    public AWSCredentials amazonAWSCredentials() {
//        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
//    }
}
