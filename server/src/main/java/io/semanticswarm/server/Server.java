package io.semanticswarm.server;

import io.semanticswarm.model.MemberMemory;

public class Server {
    public static void main(String[] args) {
        MemberMemory memberMemory = MemberMemory
                .builder().build();

        System.out.println("Start server");
    }
}
