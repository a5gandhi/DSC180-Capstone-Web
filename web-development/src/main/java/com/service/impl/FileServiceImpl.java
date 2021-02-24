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
//            file.transferTo(new File("Users/gandh/DSC180-Capstone-Web/phrase-mining/data/raw/input.txt"))

            // LATEST FOR DOCKER 02/22 7PM PST:
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
        String multi = "";
        String tf = "";
        List<Map<String,Object>> wordCloudList = new ArrayList<>();
        Map<String, Object> data = null;

//        String autoPhrase = "/Users/gandh/DSC180-Capstone-Web/phrase-mining/data/outputs/AutoPhrase.txt";
//        String autoPhraseSingle = "/Users/gandh/DSC180-Capstone-Web/phrase-mining/data/outputs/AutoPhrase_single-word.txt";
//        String autoPhraseMulti = "/Users/gandh/DSC180-Capstone-Web/phrase-mining/data/outputs/AutoPhrase_multi-words.txt";

        // LATEST FOR DOCKER 02/22 7PM PST:
        String autoPhrase = "/app/phrase-mining/data/outputs/AutoPhrase.txt";
        String autoPhraseTf = "/app/phrase-mining/data/outputs/multiplication.txt";
        String Tfidf = "/app/phrase-mining/data/outputs/tfidf.txt";
//        String autoPhraseSingle = "/app/phrase-mining/data/outputs/AutoPhrase_single-word.txt";
//        String autoPhraseMulti = "/app/phrase-mining/data/outputs/AutoPhrase_multi-words.txt";

        try {
            /*
             ** read autophrase results
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



            /*
             ** read multiplication results
             */
            textFile = new File(autoPhraseTf);
            read = new InputStreamReader(new FileInputStream(textFile),"utf-8");
            bufferedReader = new BufferedReader(read);
            while((lineTxt = bufferedReader.readLine()) != null){
                multi += lineTxt + "\n";
            }
            bufferedReader.close();
            read.close();
            System.out.println(multi);




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





//            String srcFile = "/Users/gandh/DSC180-Capstone-Web/phrase-mining/data/outputs/multi_value_distribution.png";
//            String destFile = "/Users/gandh/DSC180-Capstone-Web/web-development/src/main/webapp/img/multi_value_distribution.png";

            // LATEST FOR DOCKER 02/22 7PM PST:
            String srcFile = "/app/phrase-mining/data/outputs/multi_quality_score.png";
            String destFile = "/usr/local/tomcat/webapps/python/img/multi_quality_score.png";

            String tempFile = path + "img/multi_quality_score.png";
//            System.out.println(srcFile);
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





//            srcFile = "/Users/gandh/DSC180-Capstone-Web/phrase-mining/data/outputs/single_value_distribution.png";
//            destFile = "/Users/gandh/DSC180-Capstone-Web/web-development/src/main/webapp/img/single_value_distribution.png";

            // LATEST FOR DOCKER 02/22 7PM PST:
            srcFile = "/app/phrase-mining/data/outputs/comparison_quality_score.png";
            destFile = "/usr/local/tomcat/webapps/python/img/comparison_quality_score.png";
            tempFile = path + "img/comparison_quality_score.png";
            System.out.println(tempFile);

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
            return new Result(1,"error",null);
        }
        finally {
            data = new HashMap<>();
            data.put("all", all);
            data.put("multi",multi);
            data.put("tf",tf);
            data.put("wordCloud",wordCloudList);
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
