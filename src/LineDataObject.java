import java.util.Map;
import java.util.TreeMap;

// This class is used as a package of unique words and number of unique words for each tweet
public class LineDataObject {
	
	/*lineMap for collecting the unique words and uniWords to collect the number of unique words in each tweet*/
	Map<String, Integer> lineMap = new TreeMap<String, Integer>();
	double uniqueWords;
}
