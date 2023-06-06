package io.semanticswarm.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;


public class SemanticSwarmServer {
    private final int port;
    private final Server server;

    public SemanticSwarmServer(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
                .addService(new SemanticSwarmImpl())
                .build();
    }

    public void start() throws IOException {
        server.start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            SemanticSwarmServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private static class SemanticSwarmImpl extends SemanticSwarmMemoryGrpc.SemanticSwarmMemoryImplBase {
        @Override
        public void semanticSwarmMemorySearchRelationship(SemanticSwarmMemoryRelationshipRequest request, StreamObserver<SemanticSwarmMemoryRelationshipResponse> responseObserver) {
            String name = request.getName();
            String message = "Not found relationship " + name + "";
            SemanticSwarmMemoryRelationshipResponse response = SemanticSwarmMemoryRelationshipResponse.newBuilder()
                    .setMessage(message)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        SemanticSwarmServer server = new SemanticSwarmServer(8080);
        server.start();
        server.awaitTermination();
    }

    private void awaitTermination() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}