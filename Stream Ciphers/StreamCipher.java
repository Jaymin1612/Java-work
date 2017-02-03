

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;
import java.util.Scanner;


public class StreamCipher {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int seed = 10;

		String plain_text = "PlainText.txt";
		String cipher_text = "CipherText.txt";
		Scanner sc = new Scanner(System.in);
		System.out.println("The available operations:");
		System.out.println("1. Encryption");
		System.out.println("2. Decryption");
		int option;
		System.out.println("Select the operation to perform: ");
		option = sc.nextInt();
		switch (option) {
		case 1:
			enrypt(seed,plain_text,cipher_text);
			break;
		case 2:
			decrypt(seed,cipher_text,plain_text);
			break;
		default:
			System.out.println("You have selected the wrong option");
			break;
		}
		sc.close();

	}
	
	public static void enrypt(int seed, String file, String file2) throws IOException{
		
		File plain_text = new File(file);
		if(!plain_text.exists()){
			plain_text.createNewFile();
		}
		File cipher_text = new File(file2);
		if(!cipher_text.exists()){
			cipher_text.createNewFile();
		}
		
		//Logic to generate random number
		Random rndnum = new Random(seed);
		int random = rndnum.nextInt();
		StringBuffer sb;
		String str = null;
		String str1= null;
		//Logic to read file
		
		String line = null;
		try {
            // FileReader reads text files in the default encoding.
			FileInputStream fip = new FileInputStream(plain_text); 

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(new InputStreamReader(fip));
            
            FileOutputStream fout = new FileOutputStream(cipher_text);

                // Always wrap FileWriter in BufferedWriter.
                BufferedWriter bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(fout));


            while((line = bufferedReader.readLine()) != null) {
            	sb = new StringBuffer (line);

                int lenStr = line.length();
                int lenKey = String.valueOf(random).length();
          	   
                //
                // For each character in our string, encrypt it...
                for ( int i = 0, j = 0; i < lenStr; i++, j++ ) 
                {
                   if ( j >= lenKey ) j = 0;  // Wrap 'round to beginning of key string.

                   //
                   // XOR the chars together. Must cast back to char to avoid compile error. 
                   //
                   String key = random + "";
                   sb.setCharAt(i, (char)(line.charAt(i) ^ key.charAt(j))); 
                   str = sb.toString();
                   str1 = URLEncoder.encode(str,"UTF-8");
                   
                }
                //System.out.println(sb.toString());
              bufferedWriter.write(str1);
            }   

            // Always close files.
            bufferedReader.close();  
            bufferedWriter.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                file + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + file + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
		
		
    }
	
	
	public static void decrypt(int seed, String file, String file2) throws IOException{
		

		File plain_text = new File(file2);
		if(!plain_text.exists()){
			plain_text.createNewFile();
		}
		File cipher_text = new File(file);
		if(!cipher_text.exists()){
			cipher_text.createNewFile();
		}
		
		//Logic to generate random number
				Random rndnum = new Random(seed);
				int random = rndnum.nextInt();
				StringBuffer sb;
				String str = null;
				String str1=null;
				//Logic to read file
				
				String line = null;
				try {
					// FileReader reads text files in the default encoding.
					FileInputStream fip = new FileInputStream(cipher_text); 

		            // Always wrap FileReader in BufferedReader.
		            BufferedReader bufferedReader = 
		                new BufferedReader(new InputStreamReader(fip));
		            
		            FileOutputStream fout = new FileOutputStream(plain_text);

		                // Always wrap FileWriter in BufferedWriter.
		                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fout));


		            while((line = bufferedReader.readLine()) != null) {
		            	
		            	str1=URLDecoder.decode(line,"UTF-8");
		            	sb = new StringBuffer (str1);
		                int lenStr = str1.length();
		                int lenKey = String.valueOf(random).length();
		          	   
		                //
		                // For each character in our string, encrypt it...
		                for ( int i = 0, j = 0; i < lenStr; i++, j++ ) 
		                {
		                   if ( j >= lenKey ) j = 0;  // Wrap 'round to beginning of key string.

		                   //
		                   // XOR the chars together. Must cast back to char to avoid compile error. 
		                   //
		                   String key = random + "";
		                   sb.setCharAt(i, (char)(sb.charAt(i) ^ key.charAt(j))); 
		                   str = sb.toString();
		                   
		                   
		                }
		                //System.out.println(sb.toString());
		              bufferedWriter.write(str);
		            }   

		            // Always close files.
		            bufferedReader.close();  
		            bufferedWriter.close();
		        }
		        catch(FileNotFoundException ex) {
		            System.out.println(
		                "Unable to open file '" + 
		                file + "'");                
		        }
		        catch(IOException ex) {
		            System.out.println(
		                "Error reading file '" 
		                + file + "'");                  
		            // Or we could just do this: 
		            // ex.printStackTrace();
		        }
	}
}