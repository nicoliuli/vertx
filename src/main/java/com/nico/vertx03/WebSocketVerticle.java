package com.nico.vertx03;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.ServerWebSocket;

import java.util.ArrayList;
import java.util.List;

public class WebSocketVerticle extends AbstractVerticle {

    List<ServerWebSocket> channelList = new ArrayList<>();

    @Override
    public void start() {

        /*vertx.createHttpServer().requestHandler(req -> {
            if (req.path().equals("/websocket")) {
                // ws相当于netty里的channel
                ServerWebSocket ws = req.upgrade();
                System.out.println("connection：" + ws);
                ws.textMessageHandler(text -> {
                    System.out.println(text);
                    ws.writeTextMessage(System.currentTimeMillis() + "");
                }).closeHandler(close -> {
                    System.out.println("close：" + ws);
                });
            }else {

            }
        }).listen(8888);*/

        // 上面这种方法也行
        vertx.createHttpServer().websocketHandler(ws -> {
            if (ws.path().equals("/websocket")) {
                System.out.println("connection：" + ws);
                channelList.add(ws);
                ws.textMessageHandler(text -> {
                    System.out.println(text);
                    channelList.forEach(channel -> {
                        channel.writeTextMessage(System.currentTimeMillis() + "");
                    });
                }).closeHandler(close -> {
                    System.out.println("close：" + ws);
                });
            } else {
                ws.reject();
            }
        }).listen(8888);
    }

    @Override
    public void stop() {

    }
}
