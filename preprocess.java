import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Preprocess {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java PreprocessData amazon_reviews_us_Camera_v1_00.tsv cleaned_data.tsv");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];
        
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFile));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {

            // Skip header line without writing it to the output file
            reader.readLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\t");
                
                // Ensure exactly 15 fields and skip rows with missing values
                if (fields.length == 15 && !line.contains("\t\t")) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            System.out.println("Preprocessing complete. Cleaned data saved to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    
