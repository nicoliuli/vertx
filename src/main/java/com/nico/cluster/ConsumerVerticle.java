
package com.nico.cluster;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class ConsumerVerticle extends AbstractVerticle {

    public void start() throws Exception {
        EventBus eventBus = vertx.eventBus();
        eventBus.consumer("cluster-topic").handler(msg -> {
            System.out.println(msg.body());
        });
    }
}
