package com.nico.cluster;

import com.hazelcast.config.Config;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

/**
 * 可以启动多个消费者实例或进程，模拟多消费者
 */
public class ClusterConsumerStartup {
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
                DeploymentOptions option = new DeploymentOptions().setInstances(2); // 连个实例
                vertx.deployVerticle(ConsumerVerticle.class.getName(),option,ar -> {
                    if(ar.succeeded()) {
                        System.out.println("consumer deploy success");
                    }else {
                        System.out.println("consumer deploy fail," + ar.cause());
                    }
                });

            }else {

            }
        });

    }
}
