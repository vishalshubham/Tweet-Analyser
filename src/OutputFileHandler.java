import java.io.BufferedWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


public class OutputFileHandler {
	
	LineDataObject lineDataObject = new LineDataObject();
	TreeMap<String, Integer> mainTreeMap = new TreeMap<String, Integer>();
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
		
	}
}
