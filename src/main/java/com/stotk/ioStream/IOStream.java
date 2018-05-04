package com.stotk.ioStream;

import java.io.*;

/**
 *
 */
public class IOStream {

    /**
     * 字节流读写操作
     */
    public static void ByteStreamExample() {
        File file = new File("document/prose.txt");
        File fileCopy = new File("document/copy.txt");
        FileInputStream fip = null;
        FileOutputStream fop = null;
        int hasRead = 0;
        try {
            fip = new FileInputStream(file);
            fop = new FileOutputStream(fileCopy);
            byte[] buf = new byte[1024];
            while ((hasRead = fip.read(buf)) > 0) {
                fop.write(buf, 0, hasRead);///每读取一次就写一次，读多少就写多少
                //fop.write(buf);//这种方法会造成读写不安全的问题！因为buf是变化的；
            }
            System.out.println("Copy完成");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件不存在！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fip != null) {
                try {
                    fip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fop != null) {
                try {
                    fop.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 字符流读写操作
     */
    public static void CharStreamExample() {
        FileReader file = null;
        FileWriter fileWriter = null;
        try {
            file = new FileReader("document/prose.txt");
            fileWriter = new FileWriter("document/copy.txt");
            char[] buf = new char[32];
            int hasRead = 0;
            // 每个char都占两个字节，每个字符或者汉字都是占2个字节，因此无论buf长度为多少，总是能读取中文字符长度的整数倍,不会乱码
            while ((hasRead = file.read(buf)) > 0) {
                fileWriter.write(new String(buf, 0, hasRead));
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 缓冲区操作：简单高效，减少开销
     */
    public static void BufferedReaderExample() {
        FileReader reader = null;
        FileWriter writer = null;
        String line = null;
        BufferedReader br = null;
        try {
            reader = new FileReader("document/prose.txt");
            writer = new FileWriter("document/copy.txt");
            br = new BufferedReader(reader);
            while ((line = br.readLine()) != null) {
                writer.write(line + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * byte转换成char
     * InputStreamReader & OutputStreamWriter 很关键；
     */
    public static void ByteTransferCharExample() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            //BufferedX缓冲流包装，使得流处理更加快速
            br = new BufferedReader(new InputStreamReader(new FileInputStream("document/prose.txt")));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("document/copy.txt")));
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
                bw.write(s);
                bw.write("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bw) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

