// We Will Write Data to the file with    
//PrintWriter, BufferedWriter, OutputStreamWriter 
//and then read the entirety of what we have written first by using 
//Scanner second using BufferedReader + InputStreamReader

import java.io.File;  
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextIO {
    public static void main(String[] args) {
        String fileName = "TextIO.txt";  // Read and write to TextIO.txt file for example
        
     // Check if this particular file exists before writing 
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                // If the file does not exist, create it and print the creation message to the console else, print out the File already exists and the fileName.
                boolean fileCreated = file.createNewFile();
                if (fileCreated) {
                    System.out.println("File did not exist. A new file was created: " + fileName);
                } else {
                    System.out.println("File already exists");
                }
            } catch (IOException e) { //IOException handling for IO errors
                System.out.println("Error creating the file: " + e.getMessage()); //error message
            }
        } else {
            System.out.println("File already exists: " + fileName);
        }

        // Writing to the file using PrintWriter, BufferedWriter, OutputStreamWriter with try-catch block to handle IOException e
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            PrintWriter pw = new PrintWriter(bw);

            // Writing using PrintWriter, BufferedWriter, and OutputStreamWriter 
            pw.println("This was written to the file using PrintWriter");
            bw.write("This was written to the file using BufferedWriter\n");
            osw.write("This was written to the file using OutputStreamWriter\n");
            
            // Close the writer resources 
            pw.close();
            bw.close();
            osw.close();
            fos.close();

            System.out.println("Data written to file successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        // Reading data using Scanner 
        System.out.println("Reading Data using Scanner:");
        try {
            Scanner scanner = new Scanner(new File(fileName));	
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }										//try catch block FileNotFoundException handling will get error message
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        // Reading using BufferedReader and InputStreamReader
        System.out.println("Reading Data using BufferedReader + InputStreamReader:");
        try {
            FileInputStream fis = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
        } catch (Exception e) { //exception handling in case there is an error reading from the file will get error message
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
} 
