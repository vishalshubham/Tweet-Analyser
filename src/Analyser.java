import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map.Entry;

public class Analyser {

	//Input and output file paths
	private final static String INPUTFILEPATH = "tweet_input/tweets.txt";
	private final static String OUTPUTFILEPATH1 = "tweet_output/ft1.txt";
	private final static String OUTPUTFILEPATH2 = "tweet_output/ft2.txt";

	public static void main(String[] args) {
		try {	
			
			OutputHandler outputHandler = new OutputHandler();
			LineDataObject lineDataObject = new LineDataObject();
			
			FileReader fileReader = new FileReader(INPUTFILEPATH);
			FileWriter fileWriter1 = new FileWriter(OUTPUTFILEPATH1);
			FileWriter fileWriter2 = new FileWriter(OUTPUTFILEPATH2);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);
			BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
			
			String line = null;
			
			long startTime = System.currentTimeMillis();
			
			// Read line by line each tweet and process it.
			while((line=bufferedReader.readLine())!=null){
				outputHandler.setInputLine(line);
				lineDataObject = outputHandler.processLine();
				outputHandler.storeWordsInOutputFile(bufferedWriter1, lineDataObject.lineMap);
				outputHandler.storeMedianInOutputFile(bufferedWriter2, lineDataObject.uniqueWords);
			}
			
			// Write the final version of feature 1 in the output file 1. 
			// Feature 2 is written to the output file regularly with processing of each tweet.
			outputHandler.writeWordsOutput(bufferedWriter1);
			
			bufferedReader.close();
			bufferedWriter1.close();
			bufferedWriter2.close();
			
			long endTime = System.currentTimeMillis();
			
			System.out.println("Feature 1 executed. Check ft1.txt file");
			System.out.println("Feature 2 executed. Check ft2.txt file");
			
			// Time taken to process the whole input file
			System.out.println("Total time taken by the program: " + (endTime-startTime) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
