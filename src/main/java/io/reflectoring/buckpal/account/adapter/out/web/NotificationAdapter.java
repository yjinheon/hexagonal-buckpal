package io.reflectoring.buckpal.account.adapter.out.web;


import io.reflectoring.buckpal.account.application.port.out.Notification;
import io.reflectoring.buckpal.account.application.port.out.SendNotificationPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationAdapter implements SendNotificationPort {


    private final WebClient webClient;


    @Override
    public void sendNotification(Notification notification) {

    }
}
