import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map.Entry;

public class Analyser {

	private final static String INPUTFILEPATH = "tweet_input/tweets.txt";
	private final static String OUTPUTFILEPATH1 = "tweet_output/ft1.txt";
	private final static String OUTPUTFILEPATH2 = "tweet_output/ft2.txt";

	public static void main(String[] args) {
		try {	
			
			OutputFileHandler outputFileHandler = new OutputFileHandler();
			LineDataObject lineDataObject = new LineDataObject();
			
			FileReader fileReader = new FileReader(INPUTFILEPATH);
			FileWriter fileWriter1 = new FileWriter(OUTPUTFILEPATH1);
			FileWriter fileWriter2 = new FileWriter(OUTPUTFILEPATH2);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);
			BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
			
			String line = null;
			
			while((line=bufferedReader.readLine())!=null){
				outputFileHandler.setInputLine(line);
				lineDataObject = outputFileHandler.processLine();
				outputFileHandler.storeWordsInOutputFile(bufferedWriter1, lineDataObject.lineMap);
				outputFileHandler.storeMedianInOutputFile(bufferedWriter2, lineDataObject.uniqueWords);
			}
			for(Entry<String, Integer> entry: outputFileHandler.mainTreeMap.entrySet()){
				bufferedWriter1.write(entry.getKey() + "\t" + entry.getValue() + "\n");
			}
			
			bufferedReader.close();
			bufferedWriter1.close();
			bufferedWriter2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
