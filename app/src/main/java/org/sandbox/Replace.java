package org.sandbox;

import software.amazon.awssdk.services.ssm.*;
import software.amazon.awssdk.services.ssm.endpoints.internal.Value;
import software.amazon.awssdk.services.ssm.model.GetParametersRequest;
import software.amazon.awssdk.services.ssm.model.GetParametersResponse;

public class Replace {

    public static String replacePlaceholders(String string) {

        SsmClient ssmClient = SsmClient
                .builder()
                .credentialsProvider(Connection.createConnection())
                .build();

        int index_end = string.indexOf("=");


        GetParametersRequest getParametersRequest = GetParametersRequest
                .builder()
                .names("/NONPROD/" + string.substring(0, index_end))
                .withDecryption(true)
                .build();

        GetParametersResponse getParametersResponse = ssmClient.getParameters(getParametersRequest);

        System.out.println(getParametersResponse.parameters().get(0).name());



        return getParametersResponse.parameters().get(0).value();


    }

}
