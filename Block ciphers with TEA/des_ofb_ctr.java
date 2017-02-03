import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.Key;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

public class des_ofb_ctr {
	public static void main(String args[]) throws IOException {
		System.out.println("This is the Encryption and Decryption using DES");
		System.out.println("We have two Block ciphers modes available:");
		System.out.println("1. Output Feedback mode (OFB)");
		System.out.println("2. Counter mode (CTR)");
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();
		
		// FileReader reads text files in the default encoding.
		FileInputStream fip = new FileInputStream("plainText.txt"); 

		// Always wrap FileReader in BufferedReader.
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fip));
		            
		FileOutputStream fout = new FileOutputStream("cipherText.txt");

		// Always wrap FileWriter in BufferedWriter.
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fout));
		
		StringBuffer sb;
		String line,plainText = null;
		
		switch (option) {
		case 1:
			try {
				long time1 = System.nanoTime();
				while((line = bufferedReader.readLine()) != null) {
	            	sb = new StringBuffer (line);
	            	plainText = sb.toString();
				}
				
				KeyGenerator kg = KeyGenerator.getInstance("DES");
				Cipher c = Cipher.getInstance("DES/OFB/PKCS5Padding");
				Key key = kg.generateKey();
				
				c.init(Cipher.ENCRYPT_MODE, key);
				byte input[] = plainText.getBytes();
				System.out.println("Our Input is: "+ new String(input));
				byte encrypted[] = c.doFinal(input);
				bufferedWriter.write(new String(encrypted));
				byte iv[] = c.getIV();

				IvParameterSpec dps = new IvParameterSpec(iv);
				c.init(Cipher.DECRYPT_MODE, key, dps);
				byte output[] = c.doFinal(encrypted);
				System.out.println("After decryption ");
				System.out.println(new String(output));
				long time2 = System.nanoTime();
				long time = time2 - time1;
				System.out.println("Time take : " + time + "ns");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		
		case 2:
			try {
				long time1 = System.nanoTime();
				while((line = bufferedReader.readLine()) != null) {
	            	sb = new StringBuffer (line);
	            	plainText = sb.toString();
				}
				
				KeyGenerator kg = KeyGenerator.getInstance("DES");
				Cipher c = Cipher.getInstance("DES/OFB/PKCS5Padding");
				Key key = kg.generateKey();
				
				c.init(Cipher.ENCRYPT_MODE, key);
				byte input[] = "Jaymin Shethwala".getBytes();
				System.out.println("Our Input is: "+ new String(input));
				byte encrypted[] = c.doFinal(input);
				bufferedWriter.write(new String(input));
				byte iv[] = c.getIV();

				IvParameterSpec dps = new IvParameterSpec(iv);
				c.init(Cipher.DECRYPT_MODE, key, dps);
				byte output[] = c.doFinal(encrypted);
				System.out.println("After decryption ");
				System.out.println(new String(output));
				long time2 = System.nanoTime();
				long time = time2 - time1;
				System.out.println("Time take : " + time + "ns");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		default:
			System.out.println("You have entered a wrong option.");
			break;
		}
		
		bufferedReader.close();
		bufferedWriter.close();
		sc.close();
	}
}