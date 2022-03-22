package com.nico.vertx01;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;

public class HttpServerVerticle extends AbstractVerticle {


    @Override
    public void start() {
        Context context = vertx.getOrCreateContext();
        context.runOnContext((handler) -> {

        });

        // curl -X POST http://localhost:8080 -d 'hello=world'
        vertx.createHttpServer().requestHandler(req -> {
            req.handler(buffer -> {
                        System.out.println(buffer.length());
            }).response()
                    .putHeader("text/plain", "text/plain")
                    .end("Hello World");
        }).listen(8080);
    }

    @Override
    public void stop() {

    }
}
