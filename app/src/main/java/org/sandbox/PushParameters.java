package org.sandbox;

import software.amazon.awssdk.services.ssm.*;
import software.amazon.awssdk.services.ssm.model.PutParameterRequest;

import java.io.BufferedReader;
import java.io.FileReader;


public class PushParameters {

    public static void readFileAndPushParameters(String filePath) throws Exception {

        BufferedReader input = new BufferedReader(new FileReader(filePath));
        String file_string_line;

        while((file_string_line = input.readLine()) != null) {
            PushParameters.pushParameters(file_string_line);
        }

    }

    public static void pushParameters(String string) throws Exception {

        SsmClient ssmClient = SsmClient.builder()
                .credentialsProvider(Connection.createConnection())
                .build();

        int index_start = string.indexOf(".");
        int index_end = string.indexOf("=");

        if (index_end != -1) {
            PutParameterRequest putParameterRequest = PutParameterRequest.builder()
                    .name("/NONPROD/" + string.substring(index_start + 1, index_end))
                    .value(string.substring(string.lastIndexOf("=") + 1))
                    .overwrite(true)
                    .type("SecureString")
                    .build();

            ssmClient.putParameter(putParameterRequest);
        }

    }
}
