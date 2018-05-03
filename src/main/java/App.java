import com.stotk.utils.ReadFiles;
import com.stotk.partition.WordsPartition;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 */
public class App {
    public static void main(String[] args) {
        String list = ReadFiles.readAllFiles("document/prose.txt");
        Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
        WordsPartition.chineseWordsPartition(wordCountMap, list);
        for (Map.Entry<String, Integer> item : wordCountMap.entrySet()) {
            System.out.println("key:" + item.getKey() + " " + "value:" + item.getValue());
        }
    }
}
