package org.sandbox;

import software.amazon.awssdk.services.ssm.*;
import software.amazon.awssdk.services.ssm.model.PutParameterRequest;


public class PushParameters {

    public static void pushParameters(String string) {

        SsmClient ssmClient = SsmClient.builder()
                .credentialsProvider(Connection.createConnection())
                .build();

        int index_end = string.indexOf("=");

        if (index_end != -1) {
            PutParameterRequest putParameterRequest = PutParameterRequest.builder()
                    .name("/NONPROD/" + string.substring(0, index_end))
                    .value(string.substring(string.lastIndexOf("=") + 1))
                    .overwrite(true)
                    .type("SecureString")
                    .build();

            ssmClient.putParameter(putParameterRequest);
        }

    }
}
