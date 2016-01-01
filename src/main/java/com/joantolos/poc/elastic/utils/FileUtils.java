package com.joantolos.poc.elastic.utils;

import java.io.*;

public class FileUtils {

    public static String streamToString(InputStream inputStream) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeBufferedReader(br);
        }

        return sb.toString();
    }

    public static String streamToStringWithNewLineChar(InputStream inputStream) {
        try {
            BufferedReader br= new BufferedReader(inputStreamToReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;

            while((line= br.readLine())!=null){
                builder.append(line).append("\n");
            }

            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Reader inputStreamToReader(InputStream inputStream) {
        return new InputStreamReader(inputStream);
    }

    public static void closeBufferedReader(BufferedReader br) {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
