package org.sandbox;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;

public class Connection {

    public static DefaultCredentialsProvider createConnection() {

        return DefaultCredentialsProvider
                .builder()
                .build();

    }

}

