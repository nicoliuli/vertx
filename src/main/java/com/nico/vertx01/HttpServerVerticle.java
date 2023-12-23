package com.nico.vertx01;

import com.alibaba.fastjson.JSON;
import com.nico.web.bean.Bean;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import jdk.nashorn.api.scripting.JSObject;

public class HttpServerVerticle extends AbstractVerticle {


    @Override
    public void start() {
        Context context = vertx.getOrCreateContext();
        context.runOnContext((handler) -> {

        });

        // curl -X POST http://localhost:8081 -d 'hello=world'
        vertx.createHttpServer().requestHandler(req -> {
            req.handler(buffer -> {
                        System.out.println(buffer.length());
            }).response()
                    .putHeader("content-type","application/json")
                    .end(JSON.toJSONString(new Bean("123","三方服务返回")));
        }).listen(8081);
    }

    @Override
    public void stop() {

    }
}
