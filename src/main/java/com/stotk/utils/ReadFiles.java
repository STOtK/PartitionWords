package com.stotk.utils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class ReadFiles {

    public static String charset = "UTF-8";

    /**
     * 若数据是行级别的，可用这种方式来读取数据，字符流操作
     *
     * @param filename
     * @return
     */
    public static List<String> readLineFiles(String filename) {
        List<String> result = new LinkedList<String>();
        FileInputStream fis = null;
        InputStreamReader in = null;
        BufferedReader br = null;
        String line = null;
        try {
            fis = new FileInputStream(filename);
            in = new InputStreamReader(fis, charset);
            //使得输入流更加快速
            br = new BufferedReader(in);
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 读出整个文件的数据，字符流操作
     *
     * @param file
     * @return
     */
    public static String readAllFiles(String file) {
        File f = new File(file);
        byte[] fileBuff = new byte[(int) f.length()];
        FileInputStream in = null;
        try {
            in = new FileInputStream(f);
            in.read(fileBuff);
            return new String(fileBuff, charset);
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 若文件夹中有很多数据文件
     * 可使用这种方式来输出数据
     *
     * @param path
     * @return
     */
    public static List<String> readCataListFiles(String path) {
        List<String> result = new LinkedList<String>();
        File file = new File(path);
        for (File item : file.listFiles()) {
            result.add(readAllFiles(item.getAbsolutePath()));
        }
        return result;
    }
}
