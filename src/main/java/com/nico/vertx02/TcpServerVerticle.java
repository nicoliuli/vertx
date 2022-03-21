package com.nico.vertx02;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

public class TcpServerVerticle extends AbstractVerticle {


    @Override
    public void start() {
        NetServerOptions options = new NetServerOptions();

        NetServer netServer = vertx.createNetServer(options);
        netServer.connectHandler((socket) -> {
            System.out.println("connect...");
            socket.handler(buffer -> {
                System.out.println(buffer.length());
                socket.write(System.currentTimeMillis() + "\r\n");
            }).closeHandler(res -> {
                System.out.println("close...");
            }).exceptionHandler(event -> {
                event.printStackTrace();
            });
        }).listen(8888);


    }

    @Override
    public void stop() {

    }
}
