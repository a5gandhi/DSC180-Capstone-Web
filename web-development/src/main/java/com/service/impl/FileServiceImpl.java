package com.service.impl;

import com.entity.Result;
import com.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public Result fileTransfer(MultipartFile file) {
        String text = "";

        Map<String, Object> data = null;
        try {
            // file.transferTo(new File("/Users/johnwang/desktop/phrase-mining/data/raw/input.txt"));
           file.transferTo(new File("/app/phrase-mining/data/raw/input.txt"));

            InputStreamReader read = new InputStreamReader(
                    file.getInputStream(),"utf-8");
            BufferedReader bufferedReader = new BufferedReader(read);

            String lineTxt = null;
            while((lineTxt = bufferedReader.readLine()) != null){
                text += lineTxt + "\n";
            }
            System.out.println(text);
        } catch (IOException e) {
            return new Result(1,"error",null);
        } finally {
            data = new HashMap<>();
            data.put("text", text);
            return new Result(0, "success", data);
        }
    }



    public Result fileRead(String path) {

        String all = "";
        String autoPhrase = "/app/phrase-mining/data/outputs/AutoPhrase.txt";

        String single = "";
        String autoPhrase_single = "/app/phrase-mining/data/outputs/AutoPhrase_single-word.txt";

        String multi = "";
        String autoPhrase_multi = "/app/phrase-mining/data/outputs/AutoPhrase_multi-words.txt";

        String tf = "";
        String Tfidf = "/app/phrase-mining/data/outputs/tfidfall.txt";

        String multiplication_all = "";
        String autoPhraseTf = "/app/phrase-mining/data/outputs/multiplicationall.txt";
        
        String seg = "";
        String segmentation = "/app/phrase-mining/data/outputs/segmentation.txt";

        String description = "";
        String description1 = "/app/phrase-mining/data/outputs/eda/description.txt";


        // String all = "";
        // String autoPhrase = "/Users/johnwang/desktop/phrase-mining/data/outputs/AutoPhrase.txt";

        // String single = "";
        // String autoPhrase_single = "/Users/johnwang/desktop/phrase-mining/data/outputs/AutoPhrase_single-word.txt";

        // String multi = "";
        // String autoPhrase_multi = "/Users/johnwang/desktop/phrase-mining/data/outputs/AutoPhrase_multi-words.txt";

        // String tf = "";
        // String Tfidf = "/Users/johnwang/desktop/phrase-mining/data/outputs/tfidfall.txt";

        // String multiplication_all = "";
        // String autoPhraseTf = "/Users/johnwang/desktop/phrase-mining/data/outputs/multiplicationall.txt";

        // String seg = "";
        // String segmentation = "/Users/johnwang/desktop/phrase-mining/data/outputs/segmentation.txt";
   
        // String description = "";
        // String description1 = "/Users/johnwang/desktop/phrase-mining/data/outputs/eda/description.txt";


        List<Map<String,Object>> wordCloudList = new ArrayList<>();
        Map<String, Object> data = null;

        try {
            System.out.println("try");
            /*
             ** read all autophrase results
             */

            File textFile = new File(autoPhrase);
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(textFile),"utf-8");
            BufferedReader bufferedReader = new BufferedReader(read);

            int linNum = 0;
            String lineTxt = null;
            Map<String,Object> wordCloud = null;
            while((lineTxt = bufferedReader.readLine()) != null){
                all += lineTxt + "\n";
                if(linNum++ <20) {
                    String[] wordAndValue = lineTxt.split("\t");
                    wordCloud = new HashMap<>();
                    wordCloud.put("name", wordAndValue[1]);
                    wordCloud.put("value", new Double(wordAndValue[0]));
                    wordCloudList.add(wordCloud);
                }
                else {
                    String[] wordAndValue = lineTxt.split("\t");
                    wordCloud = new HashMap<>();
                    wordCloud.put("name", wordAndValue[1]);
                    wordCloud.put("value", new Double(wordAndValue[0]));
                    wordCloudList.add(wordCloud);
                }
            }
            bufferedReader.close();
            read.close();
            System.out.println(all);



//             File textFile = new File(autoPhraseTf);
//             InputStreamReader read = new InputStreamReader(
//                     new FileInputStream(textFile),"utf-8");
//             BufferedReader bufferedReader = new BufferedReader(read);

//             int linNum = 0;
//             String lineTxt = null;
//             Map<String,Object> wordCloud = null;
//             System.out.println("---------");
//             while((lineTxt = bufferedReader.readLine()) != null){
// //                System.out.println("1");
//                 if (linNum == 0){
//                     linNum ++;
//                     continue;
//                 }
//                 all += lineTxt + "\n";
// //                System.out.println("2");
//                 if(linNum++ <21) {
//                     String[] wordAndValue = lineTxt.split(",");
//                     wordCloud = new HashMap<>();
//                     wordCloud.put("name", wordAndValue[0]);
//                     wordCloud.put("value", new Double(wordAndValue[1]));
//                     wordCloudList.add(wordCloud);
//                 }
//                 else {
//                     String[] wordAndValue = lineTxt.split(",");
//                     wordCloud = new HashMap<>();
//                     wordCloud.put("name", wordAndValue[0]);
//                     wordCloud.put("value", new Double(wordAndValue[1]));
//                     wordCloudList.add(wordCloud);
//                 }
// //                System.out.println(all);
// //                System.out.println("11111111");
//             }
//             bufferedReader.close();
//             read.close();
// //            System.out.println(all);



            /*
             ** read autophrase all
             */
            textFile = new File(autoPhrase);
            read = new InputStreamReader(new FileInputStream(textFile),"utf-8");
            bufferedReader = new BufferedReader(read);
            while((lineTxt = bufferedReader.readLine()) != null){
                all += lineTxt + "\n";
            }
            bufferedReader.close();
            read.close();
            System.out.println(all);



            /*
             ** read description
             */
            textFile = new File(description1);
            read = new InputStreamReader(new FileInputStream(textFile),"utf-8");
            bufferedReader = new BufferedReader(read);
            while((lineTxt = bufferedReader.readLine()) != null){
                description += lineTxt + "\n";
            }
            bufferedReader.close();
            read.close();
            System.out.println(description);



            /*
             ** read tfidf results
             */
            textFile = new File(Tfidf);
            read = new InputStreamReader(new FileInputStream(textFile),"utf-8");
            bufferedReader = new BufferedReader(read);
            while((lineTxt = bufferedReader.readLine()) != null){
                tf += lineTxt + "\n";
            }
            bufferedReader.close();
            read.close();
            System.out.println(tf);



            /*
             ** read multiplication results
             */
            textFile = new File(autoPhraseTf);
            read = new InputStreamReader(new FileInputStream(textFile),"utf-8");
            bufferedReader = new BufferedReader(read);
            while((lineTxt = bufferedReader.readLine()) != null){
                multiplication_all += lineTxt + "\n";
            }
            bufferedReader.close();
            read.close();
            System.out.println(multiplication_all);


            
            /*
             ** read segmentation results
             */
            textFile = new File(segmentation);
            read = new InputStreamReader(new FileInputStream(textFile),"utf-8");
            bufferedReader = new BufferedReader(read);
            while((lineTxt = bufferedReader.readLine()) != null){
                seg += lineTxt + "\n";
            }
            bufferedReader.close();
            read.close();
            System.out.println(seg);



            /*
             ** read word distribution
             */
            String srcFile = "/app/phrase-mining/data/outputs/eda/word_distribution.png";
            String destFile = "/usr/local/tomcat/webapps/python/img/word_distribution.png";
            String tempFile = path + "img/word_distribution.png";

            // String srcFile = "/Users/johnwang/desktop/phrase-mining/data/outputs/eda/word_distribution.png";
            // String destFile = "/Users/johnwang/desktop/python/src/main/webapp/img/word_distribution.png";
            // String tempFile = path + "img/word_distribution.png";

            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(srcFile)));
            System.out.println(bufferedInputStream==null);
            BufferedOutputStream destFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(destFile)));
            BufferedOutputStream tempFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(tempFile)));
            System.out.println(path);
            byte[] buffer = new byte[1024];
            int len;
            //读写流
            while ((len = bufferedInputStream.read(buffer)) != -1)
            {
//                System.out.println("------------------------------");
                destFileBufferedOutputStream.write(buffer,0,len);
                tempFileBufferedOutputStream.write(buffer,0,len);
            }
            //关闭流
            destFileBufferedOutputStream.close();
            tempFileBufferedOutputStream.close();
            bufferedInputStream.close();



            /*
             ** read tokens_freq.png
             */
            srcFile = "/app/phrase-mining/data/outputs/eda/tokens_freq.png";
            destFile = "/usr/local/tomcat/webapps/python/img/tokens_freq.png";
            tempFile = path + "img/tokens_freq.png";

            // srcFile = "/Users/johnwang/desktop/phrase-mining/data/outputs/eda/tokens_freq.png";
            // destFile = "/Users/johnwang/desktop/python/src/main/webapp/img/tokens_freq.png";
            // tempFile = path + "img/tokens_freq.png";

            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(srcFile)));
//            System.out.println(bufferedInputStream==null);
            destFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(destFile)));
            tempFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(tempFile)));
//            System.out.println(path);
            //读写流
            while ((len = bufferedInputStream.read(buffer)) != -1)
            {
//                System.out.println("------------------------------");
                destFileBufferedOutputStream.write(buffer,0,len);
                tempFileBufferedOutputStream.write(buffer,0,len);
            }
            //关闭流
            destFileBufferedOutputStream.close();
            tempFileBufferedOutputStream.close();
            bufferedInputStream.close();



            /*
             ** read tokens_top20_words.png
             */
            srcFile = "/app/phrase-mining/data/outputs/eda/tokens_top20_words.png";
            destFile = "/usr/local/tomcat/webapps/python/img/tokens_top20_words.png";
            tempFile = path + "img/tokens_top20_words.png";

            // srcFile = "/Users/johnwang/desktop/phrase-mining/data/outputs/eda/tokens_top20_words.png";
            // destFile = "/Users/johnwang/desktop/python/src/main/webapp/img/tokens_top20_words.png";
            // tempFile = path + "img/tokens_top20_words.png";

            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(srcFile)));
//            System.out.println(bufferedInputStream==null);
            destFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(destFile)));
            tempFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(tempFile)));
//            System.out.println(path);
            //读写流
            while ((len = bufferedInputStream.read(buffer)) != -1)
            {
//                System.out.println("------------------------------");
                destFileBufferedOutputStream.write(buffer,0,len);
                tempFileBufferedOutputStream.write(buffer,0,len);
            }
            //关闭流
            destFileBufferedOutputStream.close();
            tempFileBufferedOutputStream.close();
            bufferedInputStream.close();


            /*
             ** read freq_score_plot.png
             */
            srcFile = "/app/phrase-mining/data/outputs/freq_score_plot.png";
            destFile = "/usr/local/tomcat/webapps/python/img/freq_score_plot.png";
            tempFile = path + "img/freq_score_plot.png";

            // srcFile = "/Users/johnwang/desktop/phrase-mining/data/outputs/freq_score_plot.png";
            // destFile = "/Users/johnwang/desktop/python/src/main/webapp/img/freq_score_plot.png";
            // tempFile = path + "img/freq_score_plot.png";

            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(srcFile)));
//            System.out.println(bufferedInputStream==null);
            destFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(destFile)));
            tempFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(tempFile)));
//            System.out.println(path);
            //读写流
            while ((len = bufferedInputStream.read(buffer)) != -1)
            {
//                System.out.println("------------------------------");
                destFileBufferedOutputStream.write(buffer,0,len);
                tempFileBufferedOutputStream.write(buffer,0,len);
            }
            //关闭流
            destFileBufferedOutputStream.close();
            tempFileBufferedOutputStream.close();
            bufferedInputStream.close();



            /*
             ** read comparison quality score image
             */
            srcFile = "/app/phrase-mining/data/outputs/comparison_quality_score.png";
            destFile = "/usr/local/tomcat/webapps/python/img/comparison_quality_score.png";
            tempFile = path + "img/comparison_quality_score.png";

            // srcFile = "/Users/johnwang/desktop/phrase-mining/data/outputs/comparison_quality_score.png";
            // destFile = "/Users/johnwang/desktop/python/src/main/webapp/img/comparison_quality_score.png";
            // tempFile = path + "img/comparison_quality_score.png";

            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(srcFile)));
            destFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(destFile)));
            tempFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(tempFile)));

            //读写流
            while ((len = bufferedInputStream.read(buffer)) != -1)
            {
//                System.out.println("---------------------------------");
                destFileBufferedOutputStream.write(buffer,0,len);
                tempFileBufferedOutputStream.write(buffer,0,len);
            }

            //关闭流
            destFileBufferedOutputStream.close();
            tempFileBufferedOutputStream.close();
            bufferedInputStream.close();



            
            /*
             ** read sentiment polarity distribution image
             */
            srcFile = "/app/phrase-mining/data/outputs/sentiment_polarity_distribution.png";
            destFile = "/usr/local/tomcat/webapps/python/img/sentiment_polarity_distribution.png";
            tempFile = path + "img/sentiment_polarity_distribution.png";

            // srcFile = "/Users/johnwang/desktop/phrase-mining/data/outputs/sentiment_polarity_distribution.png";
            // destFile = "/Users/johnwang/desktop/python/src/main/webapp/img/sentiment_polarity_distribution.png";
            // tempFile = path + "img/sentiment_polarity_distribution.png";

            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(srcFile)));
            destFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(destFile)));
            tempFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(tempFile)));

            //读写流
            while ((len = bufferedInputStream.read(buffer)) != -1)
            {
//                System.out.println("---------------------------------");
                destFileBufferedOutputStream.write(buffer,0,len);
                tempFileBufferedOutputStream.write(buffer,0,len);
            }

            //关闭流
            destFileBufferedOutputStream.close();
            tempFileBufferedOutputStream.close();
            bufferedInputStream.close();



            /*
             ** read sentiment subjectivity distribution image
             */
            srcFile = "/app/phrase-mining/data/outputs/sentiment_subjectivity_distribution.png";
            destFile = "/usr/local/tomcat/webapps/python/img/sentiment_subjectivity_distribution.png";
            tempFile = path + "img/sentiment_subjectivity_distribution.png";

            // srcFile = "/Users/johnwang/desktop/phrase-mining/data/outputs/sentiment_subjectivity_distribution.png";
            // destFile = "/Users/johnwang/desktop/python/src/main/webapp/img/sentiment_subjectivity_distribution.png";
            // tempFile = path + "img/sentiment_subjectivity_distribution.png";

            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(srcFile)));
            destFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(destFile)));
            tempFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(tempFile)));

            //读写流
            while ((len = bufferedInputStream.read(buffer)) != -1)
            {
//                System.out.println("---------------------------------");
                destFileBufferedOutputStream.write(buffer,0,len);
                tempFileBufferedOutputStream.write(buffer,0,len);
            }

            //关闭流
            destFileBufferedOutputStream.close();
            tempFileBufferedOutputStream.close();
            bufferedInputStream.close();


        }
        catch (IOException e) {
            System.out.println("catch");
            e.printStackTrace();
            return new Result(1,"error",null);
        }
        finally {
            System.out.println("finally");
            data = new HashMap<>();
            data.put("all", all);
            data.put("multiplication_all",multiplication_all);
            data.put("tf",tf);
            data.put("seg",seg);
            data.put("wordCloud",wordCloudList);
            data.put("description", description);
            return new Result(0, "success", data);
        }
    }



    @Override
    public Result baseAdd(MultipartFile file) {
        String text = "";

        Map<String, Object> data = null;
        try {
            //存放路径
           File addFile = new File("/app/phrase-mining/AutoPhrase/data/EN/wiki_quality.txt");
            // File addFile = new File("/Users/johnwang/desktop/phrase-mining/AutoPhrase/data/EN/wiki_quality.txt");

            InputStreamReader read = new InputStreamReader(
                    file.getInputStream(),"utf-8");
            BufferedReader bufferedReader = new BufferedReader(read);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(addFile,true)));
            String lineTxt = null;
            int result = 0;
            while((result = bufferedReader.read())!=-1)
            {
                bufferedWriter.write(result);
                //读取到的是int类型的，强制类型转换
                text += String.valueOf((char)result);
            }
            System.out.println(text);
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            return new Result(1,"error",null);
        } finally {
            data = new HashMap<>();
            data.put("text", text);
            return new Result(0, "success", data);
        }
    }
}