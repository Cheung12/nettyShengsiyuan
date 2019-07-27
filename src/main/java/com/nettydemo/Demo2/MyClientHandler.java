package com.nettydemo.Demo2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;


/**
 * @ClassName MyClientHandler
 * @Description
 * @Author zhangzhang
 * @DATE 2019-07-27 13:56
 * @VERSION 1.0
 ***/
public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("客户端接收到服务器地址"+ctx.channel().remoteAddress()+"    的消息：  "+msg);
        System.out.println("clent output ："+msg);

        ctx.channel().writeAndFlush("from client "+ LocalDateTime.now());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ctx.writeAndFlush("客户端发消息啦！");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
