package com.nico.vertx01;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class Main {
    public static void main(String[] args) {
        DeploymentOptions options = new DeploymentOptions().setInstances(2);
        Vertx vertx = Vertx.vertx();


        vertx.deployVerticle(HttpServerVerticle.class.getName(),options,res -> {
            if (res.succeeded()) {
                System.out.println(res.result());
            } else {
                System.out.println(res.cause());
            }
        });
    }
}
