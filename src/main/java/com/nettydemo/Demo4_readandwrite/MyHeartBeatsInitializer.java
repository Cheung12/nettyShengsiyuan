package com.nettydemo.Demo4_readandwrite;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyHeartBeatsInitializer
 * @Description
 * @Author zhangzhang
 * @DATE 2019-07-30 22:30
 * @VERSION 1.0
 ***/
public class MyHeartBeatsInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline=ch.pipeline();
        pipeline.addLast(new IdleStateHandler(5,7,10, TimeUnit.SECONDS));
        pipeline.addLast(new MyHeartBeatsHandler());

    }
}
