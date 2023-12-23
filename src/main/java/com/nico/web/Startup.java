package com.nico.web;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class Startup {
    public static void main(String[] args) {
        DeploymentOptions options = new DeploymentOptions().setInstances(1);
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(SingleApplicationVerticle.class.getName(),options,res -> {
            if(res.succeeded()) {
                System.out.println("startup ok");
            }else {
                res.cause().printStackTrace();
                System.out.println("start up fail; " + res.cause());
            }
        });
    }
}
