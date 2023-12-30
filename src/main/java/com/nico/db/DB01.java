package com.nico.db;

import io.vertx.core.AbstractVerticle;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlClient;

/**
 * 有问题，mysql-client的版本不对
 */
public class DB01 extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
                .setPort(3306)
                .setHost("10.12.173.64")
                .setDatabase("xsg_game_master")
                .setUser("root")
                .setPassword("root");

        PoolOptions poolOptions = new PoolOptions().setMaxSize(5);
        SqlClient client = MySQLPool.client(vertx,connectOptions, poolOptions);
        client
                .query("select * from demo_human where account='9152'")
                .execute(ar -> {
                    if(ar.succeeded()) {
                        RowSet<Row> result = ar.result();
                        for (Row row : result) {
                            String account = row.getString("account");
                            Long id = row.getLong("id");
                            System.out.println("id = " + id + ",account = " + account);
                        }
                    }else {
                        System.out.println("fail");
                    }
                    client.close();
                });
    }
}
