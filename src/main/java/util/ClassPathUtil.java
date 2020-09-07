package util;

import cn.hutool.core.io.resource.ClassPathResource;
import dto.OpenHistory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ClassPathUtil {
    public static String load(String path){
        BufferedReader br = new  ClassPathResource(path).getReader(Charset.defaultCharset());
        StringBuilder sb = new StringBuilder();
        String line = "";
        try {
            while((line = br.readLine()) != null){
                sb.append(line + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

    public static List<OpenHistory> loadFileToOpenHistories(String path){
        List<OpenHistory> openHistories = new ArrayList<OpenHistory>();
        BufferedReader br = new  ClassPathResource(path).getReader(Charset.defaultCharset());
        StringBuilder sb = new StringBuilder();
        String line = "";
        String spliter = "\t";
        try {
            while((line = br.readLine()) != null){
                String[] lotteryAndCode = line.split(spliter);
                if(lotteryAndCode[1] == null || lotteryAndCode[1].equals("")){
                    continue;
                }

                String lotteryNumber = lotteryAndCode[0].replace("-","");
                String lotteryCode = lotteryAndCode[1];
                openHistories.add(OpenHistory.builder().lotteryNumber(lotteryNumber).openNumber(lotteryCode).build());
            }
            return openHistories;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return openHistories;
    }
}
