package com.example.demo.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GraphServices {

	
	
	 @Value("${microsoft.client-id}")
	    private String clientId;

	    @Value("${microsoft.client-secret}")
	    private String clientSecret;

	    @Value("${microsoft.tenant-id}")
	    private String tenantId;

	    private final RestTemplate restTemplate =new RestTemplate();
	    private final ObjectMapper objectMapper = new ObjectMapper();
	
	    public static final Logger logger= LoggerFactory.getLogger(GraphServices.class);
	    
	 

	 
	public String createEvent(String userEmail, Map<String, Object> eventDetails) throws Exception {
		// TODO Auto-generated method stub
		 String accessToken = getAccessToken();
	        String createEventUrl = "https://graph.microsoft.com/v1.0/users/" + userEmail + "/events";

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setBearerAuth(accessToken);

	        HttpEntity<Map<String, Object>> request = new HttpEntity<>(eventDetails, headers);
	        ResponseEntity<String> response = restTemplate.postForEntity(createEventUrl, request, String.class);

	        if (response.getStatusCode() == HttpStatus.CREATED) {
	            logger.info("Event created successfully.");
	            return response.getBody();
	        } else {
	            logger.error("Failed to create event: " + response.getStatusCode() + " " + response.getBody());
	            throw new Exception("Failed to create event");
	        }
	    }
    
	private String getAccessToken()   throws Exception{
		// TODO Auto-generated method stub
		 String tokenUrl = "https://login.microsoftonline.com/" + tenantId + "/oauth2/v2.0/token";
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	        
	        MultiValueMap<String, String> bodyParams = new LinkedMultiValueMap<>();
	        bodyParams.add("client_id", clientId);
	        bodyParams.add("scope", "https://graph.microsoft.com/.default");
	        bodyParams.add("client_secret", clientSecret);
	        bodyParams.add("grant_type", "client_credentials");

	        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodyParams, headers);
	        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

	        if (response.getStatusCode() == HttpStatus.OK) {
	            logger.info("Access token retrieved successfully.");
	            return (String) response.getBody().get("access_token");
	        } else {
	            logger.error("Failed to retrieve access token: " + response.getStatusCode());
	            logger.error("Response Body: " + response.getBody());
	            throw new Exception("Failed to retrieve access token");
	        }	}

	public void deleteEvent(String userEmail, String eventId) throws Exception {
        String accessToken = getAccessToken();
        String deleteEventUrl = "https://graph.microsoft.com/v1.0/users/" + userEmail + "/events/" + eventId;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Void> response = restTemplate.exchange(deleteEventUrl, HttpMethod.DELETE, request, Void.class);

        if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
            logger.info("Event deleted successfully.");
        } else {
            logger.error("Failed to delete event: " + response.getStatusCode() + " " + response.getBody());
            throw new Exception("Failed to delete event");
        }
    }
	
	 public String updateEvent(String userEmail, String eventId, Map<String, Object> eventDetails) throws Exception {
	        String accessToken = getAccessToken();
	        String updateEventUrl = "https://graph.microsoft.com/v1.0/users/" + userEmail + "/events/" + eventId;

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setBearerAuth(accessToken);

	        HttpEntity<Map<String, Object>> request = new HttpEntity<>(eventDetails, headers);
	        ResponseEntity<String> response = restTemplate.exchange(updateEventUrl, HttpMethod.PATCH, request, String.class);

	        if (response.getStatusCode() == HttpStatus.OK) {
	            logger.info("Event updated successfully.");
	            return response.getBody();
	        } else {
	            logger.error("Failed to update event: " + response.getStatusCode() + " " + response.getBody());
	            throw new Exception("Failed to update event");
	        }
	    }
	

}
	
	
	


