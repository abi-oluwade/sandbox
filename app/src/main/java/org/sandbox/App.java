package org.sandbox;

import java.io.*;

public class App {

    public static void main(String[] args) throws IOException {

//        readFile(args[0]);
        readFile("/home/abi/java-proj/sandbox/app/src/main/resources/test_file");
        replacePlaceholder();



    }

    public static void readFile(String filePath) throws IOException {

        BufferedReader input = new BufferedReader(new FileReader(filePath));
        String str;

        while((str = input.readLine()) != null) {
            PushParameters.pushParameters(str);
            Replace.replacePlaceholders(str);
        }

    }

    public static void replacePlaceholder() throws IOException {

        BufferedReader file_read = new BufferedReader(new FileReader("/home/abi/java-proj/sandbox/app/src/main/resources/replace_file"));
        String line;

        BufferedWriter file_write = new BufferedWriter(new FileWriter("/home/abi/java-proj/sandbox/app/src/main/resources/replace_file2"));
        String updated_file;


        StringBuilder file_builder = new StringBuilder();

        while((line = file_read.readLine()) != null) {
            int index_start = line.indexOf("=");
            int index_end = line.lastIndexOf("@");

            file_builder.insert(0,line);
            file_builder.replace(index_start + 1,index_end + 1,"VALUE\n");



        }

        updated_file = file_builder.toString();
        System.out.println(updated_file);

        file_write.write(updated_file);
        file_write.flush();
        file_write.close();


    }
}


