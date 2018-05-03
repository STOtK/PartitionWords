package com.stotk.partition;

import com.stotk.utils.CharacterEncoding;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

/**
 *
 */
public class WordsPartition {

    public static void chineseWordsPartition(Map<String, Integer> wordsMap, String textFile) {
        StringReader line = new StringReader(textFile);
        IKSegmenter segment = new IKSegmenter(line, true);
        try {
            for (Lexeme lexeme = segment.next(); lexeme != null; lexeme = segment.next()) {
                String word = lexeme.getLexemeText();
                if (!CharacterEncoding.isChinese(word)) continue;
                if (wordsMap.get(word) != null) {
                    wordsMap.put(word, wordsMap.get(word) + 1);
                } else {
                    wordsMap.put(word, 1);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
