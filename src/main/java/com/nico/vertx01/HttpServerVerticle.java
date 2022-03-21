package com.nico.vertx01;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;

public class HttpServerVerticle extends AbstractVerticle {


    @Override
    public void start() {
        Context context = vertx.getOrCreateContext();
        context.runOnContext((handler) -> {

        });

        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                    .putHeader("text/plain", "text/plain")
                    .end("Hello World");
        }).listen(8080);
    }

    @Override
    public void stop() {

    }
}
