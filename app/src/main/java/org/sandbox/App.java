package org.sandbox;

import java.io.*;

public class App {

    public static void main(String[] args) throws Exception {

//        PushParameters.readFileAndPushParameters(args[0]);
//        Replace.updateConfigFile(args[0], args[1]);
        Replace.replacePlaceholders(args[0]);



    }

}


