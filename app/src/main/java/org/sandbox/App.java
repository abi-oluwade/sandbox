package org.sandbox;

public class App {

    public static void main(String[] args) throws Exception {

        PushParameters.readFileAndPushParameters(args[0]);
        Replace.updateConfigFile(args[0], args[1]);

    }

}


