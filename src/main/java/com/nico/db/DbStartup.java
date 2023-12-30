package com.nico.db;


import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class DbStartup {
    public static void main(String[] args) {
        DeploymentOptions options = new DeploymentOptions().setInstances(1);
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(DB01.class.getName(),options, res -> {
            if(res.succeeded()) {
                System.out.println("startup ok");
            }else {
                res.cause().printStackTrace();
                System.out.println("start up fail; " + res.cause());
            }
        });
    }
}
