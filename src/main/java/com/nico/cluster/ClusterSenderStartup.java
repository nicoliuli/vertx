package com.nico.cluster;

import com.hazelcast.config.Config;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class ClusterSenderStartup {
    public static void main(String[] args) {
        // 设置集群相关信息
        Config config = new Config();
        HazelcastClusterManager mgr = new HazelcastClusterManager(config);
        VertxOptions options = new VertxOptions().setClusterManager(mgr);
        options//.setEventBusOptions(new EventBusOptions().setClustered(true))
                .setClustered(true)
                .setClusterHost("127.0.0.1");

        Vertx.clusteredVertx(options,res -> {
            if(res.succeeded()) {
                Vertx vertx = res.result();
                DeploymentOptions option = new DeploymentOptions();
                vertx.deployVerticle(SenderVerticle.class.getName(),option,ar -> {
                    if(ar.succeeded()) {
                        System.out.println("sender deploy success");
                    }else {
                        System.out.println("sender deploy fail");
                    }
                });

            }else {

            }
        });

    }
}
