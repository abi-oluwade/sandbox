package org.sandbox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

//        readFile(args[0]);
        readFile("/home/abi/java-proj/sandbox/app/src/main/resources/test_file");
        replacePlaceholder("/home/abi/java-proj/sandbox/app/src/main/resources/test_file");


    }

    public static void readFile(String filePath) throws IOException {

        BufferedReader input = new BufferedReader(new FileReader(filePath));
        String str;

        while((str = input.readLine()) != null) {
            PushParameters.pushParameters(str);
        }

    }

    public static void replacePlaceholder(String filePath) throws IOException {

        BufferedReader input = new BufferedReader(new FileReader(filePath));
        String str;

        BufferedReader placeholder_file = new BufferedReader(new FileReader("/home/abi/java-proj/sandbox/app/src/main/resources/replace_file"));

        while((str = input.readLine()) != null) {
            Replace.replacePlaceholders(str);
        }
    }
}
