package com.cards.cardDtails.config;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import jakarta.persistence.AttributeConverter;

@Configuration
public class AesEncryptor implements AttributeConverter<Object, String> {
	
	
	
	
//	 private final String FIXED_ENCRYPTION_KEY = "your_encryption_key_here";
//
//	    private final String encryptionCipher = "AES";
//
//	    private Key key;
//	    private Cipher cipher;
//
//	    private Key getKey() {
//	        // Use the fixed encryption key directly
//	        if (key == null)
//	            key = new SecretKeySpec(FIXED_ENCRYPTION_KEY.getBytes(), encryptionCipher);
//	        return key;
//	    }
//
//	    private Cipher getCipher() throws GeneralSecurityException {
//	        if (cipher == null)
//	            cipher = Cipher.getInstance(encryptionCipher);
//	        return cipher;
//	    }
//
//	    private void initCipher(int encryptMode) throws GeneralSecurityException {
//	        getCipher().init(encryptMode, getKey());
//	    }
//
//	    public String convertToDatabaseColumn(Object attribute) {
//	        if (attribute == null)
//	            return null;
//	        try {
//	            initCipher(Cipher.ENCRYPT_MODE);
//	            byte[] bytes = SerializationUtils.serialize(attribute);
//	            return Base64.getEncoder().encodeToString(getCipher().doFinal(bytes));
//	        } catch (Exception e) {
//	            // Handle exceptions
//	            e.printStackTrace();
//	            return null;
//	        }
//	    }
//
//	    public Object convertToEntityAttribute(String dbData) {
//	        if (dbData == null)
//	            return null;
//	        try {
//	            initCipher(Cipher.DECRYPT_MODE);
//	            byte[] bytes = Base64.getDecoder().decode(dbData);
//	            return SerializationUtils.deserialize(getCipher().doFinal(bytes));
//	        } catch (Exception e) {
//	            // Handle exceptions
//	            e.printStackTrace();
//	            return null;
//	        }
//	    }
	
	
	/*
	 *  
	 * it not create final-> it compulsory to initialized "he blank final field encryptionKey may not have been initialized " 
	 * and static because it static is class relate not instance related
	that's way below given instance is without final and static (commented instance)
	*/
	//	@Value("${aes.encryption.key}")
 //	private  String encryptionKey ;
	
	
	
	
	
	

   // @Value("${aes.encryption.key}")
    private final String encryptionKey ="your_encryption_key_here";
	

    private final String encryptionCipher = "AES";

    private Key key;
    private Cipher cipher;

    private Key getKey() {
        if (key == null)
            key = new SecretKeySpec(encryptionKey.getBytes(), encryptionCipher);
        return key;
    }

    private Cipher getCipher() throws GeneralSecurityException {
        if (cipher == null)
            cipher = Cipher.getInstance(encryptionCipher);
        return cipher;
    }

    private void initCipher(int encryptMode) throws GeneralSecurityException {
        getCipher().init(encryptMode, getKey());
    }

   
    @Override
    public String convertToDatabaseColumn(Object attribute) {
    	if (attribute == null)
            return null;
    	try {initCipher(Cipher.ENCRYPT_MODE);
        byte[] bytes = SerializationUtils.serialize(attribute);
        return Base64.getEncoder().encodeToString(getCipher().doFinal(bytes));}catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }
    	
        
    }

   
    @Override
    public Object convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        try {
			initCipher(Cipher.DECRYPT_MODE);
		
        byte[] bytes = getCipher().doFinal(Base64.getDecoder().decode(dbData));
        return SerializationUtils.deserialize(bytes);} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    





//    private final String encryptionKey ="your_encryption_key_here";
//
//    private final String encryptionCipher = "AES";
//
//    private Key key;
//    private Cipher cipher;
//
//    private Key getKey() {
//        if (key == null)
//            key = new SecretKeySpec(encryptionKey.getBytes(), encryptionCipher);
//        return key;
//    }
//
//    private Cipher getCipher() throws GeneralSecurityException {
//        if (cipher == null)
//            cipher = Cipher.getInstance(encryptionCipher);
//        return cipher;
//    }
//
//    private void initCipher(int encryptMode) throws GeneralSecurityException {
//        getCipher().init(encryptMode, getKey());
//    }
//
//    @SneakyThrows
//    @Override
//    public String convertToDatabaseColumn(Object attribute) {
//        if (attribute == null)
//            return null;
//        initCipher(Cipher.ENCRYPT_MODE);
//        byte[] bytes = SerializationUtils.serialize(attribute);
//        return Base64.getEncoder().encodeToString(getCipher().doFinal(bytes));
//    }
//
//    @SneakyThrows
//    @Override
//    public Object convertToEntityAttribute(String dbData) {
//        if (dbData == null)
//            return null;
//        initCipher(Cipher.DECRYPT_MODE);
//        byte[] bytes = getCipher().doFinal(Base64.getDecoder().decode(dbData));
//        return SerializationUtils.deserialize(bytes);
//    }
    }
