package com.nico.vertx01;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.eventbus.EventBus;

public class EventBusVerticle extends AbstractVerticle {


    @Override
    public void start() {
        Context context = vertx.getOrCreateContext();
        context.runOnContext((handler) -> {

        });

        EventBus eventBus = vertx.eventBus();

        vertx.createHttpServer().requestHandler(req -> {

            eventBus.consumer("demo", message -> {
                System.out.println("consumer0 ==== " + message.body());
                message.reply("ok0");
            });

            eventBus.consumer("demo", message -> {
                System.out.println("consumer1 ==== " + message.body());
                message.reply("ok1");
            });

            eventBus.send("demo", "hahaha", reply -> {
                if (reply.succeeded()) {
                    System.out.println("consumer reply === " + reply.result().body());
                } else {
                    // 没有消费者handler,会失败
                    System.out.println("consumer fail");
                }
            });


            req.response()
                    .putHeader("text/plain", "text/plain")
                    .end("Hello World");
        }).listen(8081);
    }

    @Override
    public void stop() {

    }
}
