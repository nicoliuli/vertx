package com.nico.vertx02;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.EventBusOptions;

public class Vertx02 {
    public static void main(String[] args) {

        VertxOptions vertxOptions = new VertxOptions();
        vertxOptions.setEventBusOptions(new EventBusOptions().setClusterPublicHost("localhost").setClusterPublicPort(8888));
        Vertx.clusteredVertx(vertxOptions, res -> {
            if (res.succeeded()) {
                Vertx result = res.result();
                EventBus eventBus = result.eventBus();
                System.out.println("We now have a clustered event bus: " + eventBus);
            } else {
                System.out.println("Failed: " + res.cause());
            }
        });
    }
}
