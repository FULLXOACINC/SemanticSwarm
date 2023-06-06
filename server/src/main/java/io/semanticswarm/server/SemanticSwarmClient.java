package io.semanticswarm.server;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class SemanticSwarmClient {
    private final ManagedChannel channel;
    private final SemanticSwarmMemoryGrpc.SemanticSwarmMemoryBlockingStub blockingStub;

    public SemanticSwarmClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.blockingStub = SemanticSwarmMemoryGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void findRelationship(String name) {
        SemanticSwarmMemoryRelationshipRequest request = SemanticSwarmMemoryRelationshipRequest.newBuilder()
                .setName(name)
                .build();
        SemanticSwarmMemoryRelationshipResponse response = blockingStub.semanticSwarmMemorySearchRelationship(request);
        System.out.println("Server response: " + response.getMessage());
    }

    public static void main(String[] args) throws InterruptedException {
        SemanticSwarmClient client = new SemanticSwarmClient("localhost", 8080);
        client.findRelationship("friend");
        client.shutdown();
    }
}
