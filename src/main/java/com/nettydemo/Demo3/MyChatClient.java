package com.nettydemo.Demo3;

import com.nettydemo.Demo2.MyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @ClassName MyChatClient
 * @Description
 * @Author zhangzhang
 * @DATE 2019-07-28 22:04
 * @VERSION 1.0
 ***/
public class MyChatClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();

        try {
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyChatInitializer());


            Channel channel=bootstrap.connect("localhost",8899).sync().channel();
            //channelFuture.channel().closeFuture().sync();
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

            while (true){
                channel.writeAndFlush(br.readLine()+"\r\n");
            }

        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
