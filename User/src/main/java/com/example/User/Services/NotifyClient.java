package com.example.User.Services;

import com.example.Notification.Entities.AccountCreatedNotif;
import com.example.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

public class NotifyClient {
    private final ManagedChannel channel;
    private final NotifyGrpc.NotifyBlockingStub blockingStub;
    private final NotifyGrpc.NotifyStub asyncStub;

    public NotifyClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build());
    }

    NotifyClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = NotifyGrpc.newBlockingStub(channel);
        asyncStub=NotifyGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void notifyUnary(AccountCreatedNotif request) {
        EmailRequest emailRequest =  EmailRequest.newBuilder().setEmail(request.getEmail())
                .setFirstname(request.getFirstName())
                .setLastname(request.getLastname())
                .build();

        sent response = blockingStub.notifyUnary(emailRequest);
        System.out.println("Email sent ? : " + response);
    }
    public void notifyUnaryAsync(AccountCreatedNotif request) {
        EmailRequest emailRequest =  EmailRequest.newBuilder().setEmail(request.getEmail())
                .setFirstname(request.getFirstName())
                .setLastname(request.getLastname())
                .build();

        asyncStub.notifyUnary(emailRequest, new StreamObserver<sent>() {
            @Override
            public void onNext(sent sent) {
                System.out.println("Email sent ? : " + sent.getSent());

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error in notification server");


            }

            @Override
            public void onCompleted() {
                System.out.println("stream completed");

            }
        });
    }

    /*public static void main(String[] args) throws Exception {
      NotifyClient client = new NotifyClient("localhost", 9090);
       try {
            AccountCreatedNotif request =new AccountCreatedNotif("aouinakhmoussi716@gmail.com","khmoussi","aouina");

            client.notifyUnary(request);
        } finally {
            client.shutdown();
        }


    }
*/
}
