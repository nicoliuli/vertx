package com.nico.cluster;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class SenderVerticle extends AbstractVerticle {

    public void start() throws Exception {
        EventBus eventBus = vertx.eventBus();
        vertx.setPeriodic(1000,handler -> {
            eventBus.publish("cluster-topic","aaaaa");
        });
    }
}
