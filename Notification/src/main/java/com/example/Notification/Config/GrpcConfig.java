package com.example.Notification.Config;

import com.example.Notification.Services.GreeterService;
import com.example.Notification.Services.NotificationService;
import com.example.grpc.GreeterGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.annotations.GrpcGenerated;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GrpcConfig {
    @Value("${grpc.server.port}")
    private int grpcServerPort;

    @Bean
    public Server grpcServer(GreeterService greeterService, NotificationService notificationService) throws IOException {
        Server server = ServerBuilder.forPort(grpcServerPort)
                .addService(greeterService)
                .addService(notificationService)
                .build();
        server.start();
        System.out.println("Server started, listening on " + grpcServerPort);

        return server;
    }

}