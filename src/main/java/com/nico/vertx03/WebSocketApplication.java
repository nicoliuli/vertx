package com.nico.vertx03;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class WebSocketApplication {
    public static void main(String[] args) {

        VertxOptions vertxOptions = new VertxOptions();
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(WebSocketVerticle.class.getName(),res -> {
            if(res.succeeded()){
                System.out.println(res.result());
            }else {
                System.out.println(res.cause());
            }
        });
    }
}
