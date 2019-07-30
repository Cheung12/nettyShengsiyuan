package com.nettydemo.Demo5_websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * @ClassName MyServerHandler
 * @Description
 * @Author zhangzhang
 * @DATE 2019-07-30 20:57
 * @VERSION 1.0
 ***/
public class MyServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //用于记录和管理所有客户端的channle
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        System.out.println("【服务器收到消息】:"+msg.text());

        ctx.writeAndFlush(new TextWebSocketFrame("服务器当前时间"+ LocalDateTime.now()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("【有客户端加入】"+ctx.channel().id().asLongText());
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("【有客户端离开】"+ctx.channel().id().asLongText());
        //clients.remove(ctx.channel());    netty自动移除
        System.out.println("【客户端断开，channel对应的长id为】：" + ctx.channel().id().asLongText());
        System.out.println("【客户端断开，channel对应的短id为】：" + ctx.channel().id().asShortText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
