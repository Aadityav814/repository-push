package com.excel.empdetails.model;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamoDBTable(tableName = "member")
@Data
public class Employee {
	
	@DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBAttribute
    private String firstName;

    @DynamoDBAttribute
    private String lastName;
    
    @DynamoDBAttribute
    private String email;
    
    @DynamoDBAttribute
    private String date;
    

}
