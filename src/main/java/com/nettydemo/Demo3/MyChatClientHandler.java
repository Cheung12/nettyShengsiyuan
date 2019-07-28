package com.nettydemo.Demo3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName MyChatClientHandler
 * @Description
 * @Author zhangzhang
 * @DATE 2019-07-28 22:06
 * @VERSION 1.0
 ***/
public class MyChatClientHandler extends SimpleChannelInboundHandler<String> {
    /*
     * @Author Cheung12
     * @Description //客户端只在控制台中输出服务器发过来得消息
     * @Date 22:27 2019-07-28
     * @Param [ctx, msg]
     * @return void
     **/
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg+"\r\n");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel ch=ctx.channel();
        ch.writeAndFlush("【客户端】："+ch.remoteAddress()+"已加入\r\n");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel ch=ctx.channel();
        ch.writeAndFlush("【客户端】：" + ch.remoteAddress()+"已离开\r\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel ch=ctx.channel();
        //ch.writeAndFlush("");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
