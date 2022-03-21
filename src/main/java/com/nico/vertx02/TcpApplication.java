package com.nico.vertx02;

import io.vertx.core.Vertx;

public class TcpApplication {
    public static void main(String[] args) throws Exception{
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(TcpServerVerticle.class.getName(), res -> {
            if (res.succeeded()) {
                System.out.println(res.result());
            } else {
                System.out.println(res.cause());
            }
        });

    }
}
