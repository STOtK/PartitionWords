import com.stotk.utils.ReadFiles;

import java.io.*;
import java.util.List;

/**
 *
 */
public class App {
    public static void main(String[] args) {
        String list = ReadFiles.readAllFiles("document/prose.txt");
        System.out.println(list);
    }
}
