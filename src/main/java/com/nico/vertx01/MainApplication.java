package com.nico.vertx01;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;

public class MainApplication {
    public static void main(String[] args) {
        DeploymentOptions options = new DeploymentOptions().setInstances(1);
        Vertx vertx = Vertx.vertx();


        vertx.deployVerticle(HttpServerVerticle.class.getName(),options,res -> {
            if (res.succeeded()) {
                System.out.println(res.result());
            } else {
                System.out.println(res.cause());
            }
        });

        vertx.deployVerticle(EventBusVerticle.class.getName(),options,res -> {
            if (res.succeeded()) {
                System.out.println(res.result());
            } else {
                System.out.println(res.cause());
            }
        });

        VertxOptions vertxOptions = new VertxOptions();
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
