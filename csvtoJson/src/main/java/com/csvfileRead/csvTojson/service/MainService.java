package com.csvfileRead.csvTojson.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import ch.qos.logback.classic.pattern.Abbreviator;
import io.netty.handler.codec.http2.Http2Stream.State;


@Service
public class MainService {

	 @Value("${csv.file.path}")
	 private String csv;
	 
	 @Autowired
	Repository repository;
	 
	 
	 
	 private final ResourceLoader resourceLoader;
	 private List<String> jsonDataList;
	 
	 
	 public MainService(ResourceLoader resourceLoader) {
	        this.resourceLoader = resourceLoader;
	        
	        this.jsonDataList = new ArrayList<>();
	    }
	 
	private static String jsonData;
	
	
	
	public String convertCsvToJson(MultipartFile file) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        MappingIterator<Map<String, String>> mappingIterator = csvMapper.readerFor(Map.class)
                .with(csvSchema)
                .readValues(file.getInputStream());
        
        List<Map<String, String>> list = mappingIterator.readAll();
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonData=jsonMapper.writeValueAsString(list);
    }
	public String getJsonData() {
		// TODO Auto-generated method stub
		return jsonData;
	}
	public String convertCsvToJsons(String file)throws IOException {
		// TODO Auto-generated method stub
		CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        File csvFile = new File(file);
        MappingIterator<Map<String, String>> mappingIterator = csvMapper.readerFor(Map.class)
                .with(csvSchema)
                .readValues(csvFile);
        List<Map<String, String>> list = mappingIterator.readAll();
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonData = jsonMapper.writeValueAsString(list);
        return jsonData;
    }
	
	
	
	
	
	
	
	
	/*
	 * 
	 * 
	 * */
	public String convertCsvToJsons() throws IOException {
		
		
		 if (!csv.toLowerCase().endsWith(".csv")) {
	            throw new IllegalArgumentException("File must have a .csv extension");
	        }
		// TODO Auto-generated method stub
		CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        File csvFile = new File(csv);
        MappingIterator<Map<String, String>> mappingIterator = csvMapper.readerFor(Map.class)
                .with(csvSchema)
                .readValues(csvFile);
        
        List<Map<String, String>> list = mappingIterator.readAll().stream()
                .map(this::removeNullValues) 
                .filter(map -> !map.isEmpty())
                .collect(Collectors.toList());
       // List<Map<String, String>> list = mappingIterator.readAll();
        
        if(list==null||list.isEmpty()) {
        	return null;
        }else {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonData = jsonMapper.writeValueAsString(list);
        return jsonData;}
	}
	
	 private Map<String, String> removeNullValues(Map<String, String> map) {
	        return map.entrySet().stream()
	                .filter(entry -> entry.getValue() != null && !entry.getValue().isEmpty())
	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	    }
	 
	 
	 
	 
	public String convertCsvToJsonsReadFromResource(String fileName)throws IOException  {
		// TODO Auto-generated method stub
		if (!fileName.toLowerCase().endsWith(".csv")) {
            throw new IllegalArgumentException("File must have a .csv extension");
        }

        // Load the CSV file from the resources folder
        Resource resource = resourceLoader.getResource("classpath:" + fileName);

        // Convert the CSV file to JSON
        return convertCsvToJson(resource);
	}
	
	
	
	private String convertCsvToJson(Resource resource) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

        try (InputStream csvInputStream = resource.getInputStream()) {
            MappingIterator<Map<String, String>> mappingIterator = csvMapper.readerFor(Map.class)
                    .with(csvSchema)
                    .readValues(csvInputStream);

            List<Map<String, String>> list = mappingIterator.readAll().stream()
                    .map(this::removeNullValues)
                    .filter(map -> !map.isEmpty())
                    .collect(Collectors.toList());
         
            if (list == null || list.isEmpty()) {
                return null;
            } else {
                ObjectMapper jsonMapper = new ObjectMapper();
                jsonData = jsonMapper.writeValueAsString(list);
                return jsonData;
            }
        }
	 
	 
	

	

}
	public String getJsonDataByAbbreviation(String abbreviation) throws IOException {
        if (jsonData == null || jsonData.isEmpty()) {
            return null;
        }

        ObjectMapper jsonMapper = new ObjectMapper();
        List<Map<String, String>> dataList = jsonMapper.readValue(jsonData, new TypeReference<List<Map<String, String>>>() {});

        Optional<Map<String, String>> result = dataList.stream()
                .filter(map -> abbreviation.equals(map.get("Abbreviation")))
                .findFirst();

        if (result.isPresent()) {
            return jsonMapper.writeValueAsString(result.get());
        }

        return null;
    }
	public String getJsonDataByAbbreviation(String key, String value)throws IOException {
		// TODO Auto-generated method stub
		if (jsonData == null || jsonData.isEmpty()) {
            return null;
        }

        ObjectMapper jsonMapper = new ObjectMapper();
        List<Map<String, String>> dataList = jsonMapper.readValue(jsonData, new TypeReference<List<Map<String, String>>>() {});

        Optional<Map<String, String>> result = dataList.stream()
                .filter(map -> value.equals(map.get(key)))
                .findFirst();

        if (result.isPresent()) {
            return jsonMapper.writeValueAsString(result.get());
        }

        return null;
	}
	
	
	
	
	
	
	public String getJsonDataByDynamicKeys(Map<String, String> keyValues) throws IOException {
        if (jsonData == null || jsonData.isEmpty()) {
            return null;
        }

        ObjectMapper jsonMapper = new ObjectMapper();
        List<Map<String, String>> dataList = jsonMapper.readValue(jsonData, new TypeReference<List<Map<String, String>>>() {});

        Optional<Map<String, String>> result = dataList.stream()
                .filter(map -> keyValues.entrySet().stream()
                        .allMatch(e -> e.getValue().equals(map.get(e.getKey()))))
                .findFirst();

        if (result.isPresent()) {
            return jsonMapper.writeValueAsString(result.get());
        }

        return null;
    }
	public String getALLJsonDataByDynamicKeys(Map<String, String> keyValues)throws IOException {
		// TODO Auto-generated method stub
		 if (jsonData == null || jsonData.isEmpty()) {
	            return null;
	        }

	        ObjectMapper jsonMapper = new ObjectMapper();
	        List<Map<String, String>> dataList = jsonMapper.readValue(jsonData, new TypeReference<List<Map<String, String>>>() {});

	        List<Map<String, String>> result = dataList.stream()
	                .filter(map -> keyValues.entrySet().stream()
	                        .allMatch(e -> e.getValue().equals(map.get(e.getKey()))))
	                 .collect(Collectors.toList());

	        if (result.isEmpty()) {
	            
	            return null;
	        }else {
	        	return jsonMapper.writeValueAsString(result);
	        }

	       
	}
	public String convertCsvToJsonsallcsv(String fileName)throws IOException {
		// TODO Auto-generated method stub
		if (!fileName.toLowerCase().endsWith(".csv")) {
            throw new IllegalArgumentException("File must have a .csv extension");
        }

        // Load the CSV file from the resources folder
        Resource resource = resourceLoader.getResource("classpath:" + fileName);

        // Convert the CSV file to JSON
        return convertCsvToJsons(resource);
	}
	
	 
	private String convertCsvToJsons(Resource resource) throws IOException {
		// TODO Auto-generated method stub
		 CsvMapper csvMapper = new CsvMapper();
	        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

	        try (InputStream csvInputStream = resource.getInputStream()) {
	            MappingIterator<Map<String, String>> mappingIterator = csvMapper.readerFor(Map.class)
	                    .with(csvSchema)
	                    .readValues(csvInputStream);

	            List<Map<String, String>> csvDataList = mappingIterator.readAll().stream()
	                    .map(this::removeNullValues)
	                    .filter(map -> !map.isEmpty())
	                    .collect(Collectors.toList());

	            if (csvDataList == null || csvDataList.isEmpty()) {
	                return null;
	            } else {
	                ObjectMapper jsonMapper = new ObjectMapper();
	                jsonData = jsonMapper.writeValueAsString(csvDataList);
	                
	                jsonDataList.add(jsonData);
	                return jsonData;
	            }
	        }
	}
	public String getALLCSVfileJsonDataByDynamicKeys(Map<String, String> keyValues)throws IOException {
		// TODO Auto-generated method stub
		 if (jsonDataList.isEmpty()) {
	            return null;
	        }

	        ObjectMapper jsonMapper = new ObjectMapper();
	        List<Map<String, String>> filteredData = new ArrayList<>();

	        for (String jsonData : jsonDataList) {
	            List<Map<String, String>> dataList = jsonMapper.readValue(jsonData, new TypeReference<List<Map<String, String>>>() {});
	            List<Map<String, String>> result = dataList.stream()
	                    .filter(map -> keyValues.entrySet().stream()
	                            .allMatch(e -> e.getValue().equals(map.get(e.getKey()))))
	                    .collect(Collectors.toList());
	            filteredData.addAll(result);
	        }

	        if (filteredData.isEmpty()) {
	            return null;
	        } else {
	            return jsonMapper.writeValueAsString(filteredData);
	        }
	    }
	
	 public String getALLCSVfileJsonDataByDynamicKeysNEW(Map<String, String> keyValues) throws IOException {
		 if (jsonDataList.isEmpty()) {
	            return null;
	        }

	        ObjectMapper jsonMapper = new ObjectMapper();
	        List<Map<String, String>> filteredData = new ArrayList<>();

	        for (String jsonData : jsonDataList) {
	            List<Map<String, String>> dataList = jsonMapper.readValue(jsonData, new TypeReference<List<Map<String, String>>>() {});
	            
//	            List<Map<String, String>> result = dataList.stream()
//	                    .filter(map -> keyValues.entrySet().stream()
//	                            .allMatch(e -> map.entrySet().stream()
//	                            		.anyMatch(entry -> entry.getKey().equals(e.getKey()) && entry.getValue().startsWith(e.getValue()) )))
//	                    .collect(Collectors.toList());
	            
	            
	            
	            List<Map<String, String>> result = dataList.stream()
	                    .filter(map -> keyValues.entrySet().stream()
	                            .allMatch(e -> {
	                                String key = e.getKey();
	                                String value = e.getValue();
	                                if ("sequence".equals(key)) {
	                                    return value.equals(map.get(key));
	                                } else {
	                                    return map.entrySet().stream()
	                                        .anyMatch(entry -> entry.getKey().equals(key) && entry.getValue().startsWith(value));
	                                }
	                            }))
	                    .collect(Collectors.toList());
	            filteredData.addAll(result);
	        }

	        if (filteredData.isEmpty()) {
	            return null;
	        } else {
	            return jsonMapper.writeValueAsString(filteredData);
	        }
	    }


	 
	 
	 
	 
	 public List<com.csvfileRead.csvTojson.model.State> exportStatesToCsv(String fileName) throws IOException {
		 
		 
		 Path resourceDirectory = Paths.get("src", "main", "resources", fileName);
	           Files.createDirectories(resourceDirectory.getParent());
	        
	         
	       
	        List<com.csvfileRead.csvTojson.model.State> states = repository.findAll();
	        
	        try (CSVWriter writer = new CSVWriter(new FileWriter(resourceDirectory.toFile()))) {
	            // Write header
	            writer.writeNext(new String[]{"Abbreviation", "State"});
	            
	            // Write data
	            for (com.csvfileRead.csvTojson.model.State state : states) {
	                writer.writeNext(new String[]{
	                    
	                    state.getAbbreviation(),
	                    state.getState()
	                });
	            
	            
	            }
	            return states;  }
	    }
	
	 }


