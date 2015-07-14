import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


public class OutputHandler {
	
	LineDataObject lineDataObject = new LineDataObject();
	TreeMap<String, Integer> mainTreeMap = new TreeMap<String, Integer>();
	ArrayList<Integer> mainUniqueList = new ArrayList<Integer>();
	String inputLine = null;

	public String getInputLine() {
		return inputLine;
	}

	public void setInputLine(String inputLine) {
		this.inputLine = inputLine;
	}
	
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
	
	public void storeMedianInOutputFile(BufferedWriter bufferWriter, int uniqueWords){
		float median = 0;
		mainUniqueList.add(uniqueWords);
		Collections.sort(mainUniqueList);   // Uses merge sort
		if((mainUniqueList.size()%2)==0){
			median = (mainUniqueList.get(mainUniqueList.size()/2) + mainUniqueList.get((mainUniqueList.size()/2)-1))/2;
		}
		else{
			median = mainUniqueList.get(mainUniqueList.size()/2);
		}
		writeMedianOutput(bufferWriter, median);
	}
	
	public void writeMedianOutput(BufferedWriter bufferWriter, float number){
		try {
			bufferWriter.write(Float.toString(number) + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
