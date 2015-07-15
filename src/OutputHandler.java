import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

// This is the main class that processes tweets and writes the output to output files.
public class OutputHandler {
	
	/* This map collects all the unique words in the tweets. And it ranges upto 2^30 which can easily 
	 * do the required job in this problem. For more complex problem(bigger data size), customized data 
	 * structures can be made to optimize insertion and sorting of the data.*/
	TreeMap<String, Integer> mainTreeMap = new TreeMap<String, Integer>();
	
	/* This List is used to collect all the unique number of words from each tweet and median is continuously 
	 * written to the output file (unlike feature 1 which is written at the end, once all the tweets have been
	 * processed)*/
	ArrayList<Double> mainUniqueList = new ArrayList<Double>();
	
	LineDataObject lineDataObject = new LineDataObject();
	String inputLine = null;

	public String getInputLine() {
		return inputLine;
	}

	public void setInputLine(String inputLine) {
		this.inputLine = inputLine;
	}
	
	/* Method that processes each tweet and adds all the unique words to a map*/
	public LineDataObject processLine(){
		TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
		String[] words = inputLine.split(" ");
		for(int i=0; i<words.length; i++ ){
			if(treeMap.containsKey(words[i])){
				int num = treeMap.get(words[i]);
				treeMap.put(words[i], ++num);
			}
			else{
				treeMap.put(words[i], 1);
			}
		}
		lineDataObject.lineMap = treeMap;
		lineDataObject.uniqueWords = treeMap.size();
		return lineDataObject;
	}
	
	/* Method that collects each word from each tweet and adds it to the main map structure
	 * accordingly (depending on whether it is a new word or repeated word)*/
	public void storeWordsInOutputFile(BufferedWriter bufferWriter, Map<String, Integer> lineMap){
		
		for(Entry<String, Integer> entry : lineMap.entrySet()){
			if(mainTreeMap.containsKey(entry.getKey())){
				int value = mainTreeMap.get(entry.getKey()) + entry.getValue();
				mainTreeMap.put(entry.getKey(), value);
			}
			else{
				mainTreeMap.put(entry.getKey(), entry.getValue());
			}
		}
	}
	
	/* Method that collects the number of unique words from each tweet and adds it to the main list
	 * structure (which is being used while calculating the median) */
	public void storeMedianInOutputFile(BufferedWriter bufferWriter, double uniqueWords){
		double median = 000.00f;
		mainUniqueList.add(uniqueWords);
		Collections.sort(mainUniqueList);   // Uses merge sort 
		if((mainUniqueList.size()%2)==0){
			median = (mainUniqueList.get((mainUniqueList.size()/2)-1) + mainUniqueList.get(mainUniqueList.size()/2))/2;
		}
		else{
			median = mainUniqueList.get(mainUniqueList.size()/2);
		}
		writeMedianOutput(bufferWriter, median);
	}
	
	/* Method that writes the feature 2 output to the output file ft2 
	 * (regularly with each tweet being processed)*/
	public void writeMedianOutput(BufferedWriter bufferWriter, double number){
		try {
			bufferWriter.write(Double.toString(number) + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* Method that writes the feature 1 output to the output file ft1 
	 * (at the end once all the tweets have been processed)*/
	public void writeWordsOutput(BufferedWriter bufferedWriter){
		for(Entry<String, Integer> entry: mainTreeMap.entrySet()){
			try {
				bufferedWriter.write(entry.getKey() + "\t" + entry.getValue() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
