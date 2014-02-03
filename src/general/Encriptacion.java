package general;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Encriptacion {

	private static final String CLAVE_ENCRYPT = "25dmlw9LCOdH9WZUL1cq";
	
	public static String encrypt(String cadena,String clave) { 
        StandardPBEStringEncryptor s = new StandardPBEStringEncryptor(); 
        s.setPassword(clave); 
		
        return s.encrypt(cadena); 
    } 
 
    public static String encrypt(String cadena) { 
        return encrypt(cadena, CLAVE_ENCRYPT); 
    }
	
	public static String decrypt(String cadena, String clave) { 
        StandardPBEStringEncryptor s = new StandardPBEStringEncryptor(); 
        s.setPassword(clave); 
        String res = ""; 
		
        try { 
            res = s.decrypt(cadena); 
        } catch (Exception e) { 
        } 

        return res; 
    } 
	
    public static String decrypt(String cadena) { 
        return decrypt(cadena, CLAVE_ENCRYPT); 
    }
}