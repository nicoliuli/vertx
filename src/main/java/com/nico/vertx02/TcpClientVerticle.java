package com.nico.vertx02;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;

public class TcpClientVerticle extends AbstractVerticle {


    @Override
    public void start() {
        vertx.createNetClient().connect(8888, "127.0.0.1", handler -> {
            if (handler.succeeded()) {
                NetSocket socket = handler.result();
                socket.write("client -->" + System.currentTimeMillis() + "\r\n");
            }else {
                System.out.println(handler.cause());
            }
        });

    }

    @Override
    public void stop() {

    }
}
