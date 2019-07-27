package com.nettydemo.Demo2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName MyServerHandler
 * @Description
 * @Author zhangzhang
 * @DATE 2019-07-27 13:36
 * @VERSION 1.0
 ***/
public class MyServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务器接收到远程客户端地址"+ctx.channel().remoteAddress()+"    的消息：  "+msg);
        ctx.channel().writeAndFlush("from server:"+msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
