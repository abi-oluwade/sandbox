package org.sandbox;

import software.amazon.awssdk.services.ssm.*;
import software.amazon.awssdk.services.ssm.model.GetParametersRequest;
import software.amazon.awssdk.services.ssm.model.GetParametersResponse;

import java.io.*;

public class Replace {

    public static void updateConfigFile(String readFile, String writeFile) throws IOException {

        BufferedReader file_read = new BufferedReader(new FileReader(readFile));
        String line;

        BufferedWriter file_write = new BufferedWriter(new FileWriter(writeFile));
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

    public static void replacePlaceholders(String readFile) throws IOException {

        SsmClient ssmClient = SsmClient
                .builder()
                .credentialsProvider(Connection.createConnection())
                .build();

        BufferedReader file_read = new BufferedReader(new FileReader(readFile));
        String line;

        while((line = file_read.readLine()) != null) {

            int index_end = line.indexOf("=");

            GetParametersRequest getParametersRequest = GetParametersRequest
                    .builder()
                    .names("/NONPROD/" + line.substring(0, index_end))
                    .withDecryption(true)
                    .build();

            GetParametersResponse getParametersResponse = ssmClient.getParameters(getParametersRequest);

            String name = (getParametersResponse.parameters().get(0).name().substring(9));
            String value = getParametersResponse.parameters().get(0).value();

        }



    }

}
