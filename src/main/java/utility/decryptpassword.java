package utility;

public class decryptpassword {

	public String decryptUserPassword(String encryptString)
	{
		char encChar;
		String decryptedString;
		
		StringBuilder sbEncrypted = new StringBuilder(encryptString);
		sbEncrypted.reverse();
		StringBuilder encryptedString = new StringBuilder();
		
		for(int i = 0; i < sbEncrypted.length(); i++)
		{
			encryptedString.append((char)((int) sbEncrypted.charAt(i) - 10));
		}
		
		return encryptedString.toString();
	}
	
}
