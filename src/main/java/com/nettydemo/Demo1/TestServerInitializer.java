package com.nettydemo.Demo1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/***
 *@className TestServerInitializer
 *@author Cheung
 *@descriptiion TODO
 *@date 2019-07-23 0023
 *@version 1.0
 *@
 ***/
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline=socketChannel.pipeline();
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        pipeline.addLast("myServer",new TestHttpServerHandler());
    }
}
