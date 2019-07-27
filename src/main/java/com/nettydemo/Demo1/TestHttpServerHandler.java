package com.nettydemo.Demo1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/***
 *@className TestHttpServerHandler
 *@author Cheung
 *@descriptiion TODO
 *@date 2019-07-23 0023
 *@version 1.0
 *@
 ***/
@ChannelHandler.Sharable
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    //channelRead也调用的channelRead0方法
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {

        System.out.println("请求类对象Class："+httpObject.getClass());

        System.out.println("远程请求地址："+channelHandlerContext.channel().remoteAddress());

        if(httpObject instanceof HttpRequest){

            HttpRequest httpRequest=(HttpRequest) httpObject;

            System.out.println("请求方法名："+httpRequest.method().name());

            URI uri=new URI(httpRequest.getUri());


            //浏览器发出请求会追加一个网站Icon请求
            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("网站Icon 请求："+uri.getPath());
                return;
            }

            ByteBuf content= Unpooled.copiedBuffer("hello netty\r\n", CharsetUtil.UTF_8);

            FullHttpResponse response=new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            channelHandlerContext.writeAndFlush(response);
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive"+"执行了");
        super.channelActive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete"+"执行了");
        super.channelReadComplete(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded"+"执行了");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved"+"执行了");
        super.handlerRemoved(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered"+"执行了");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered"+"执行了");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive"+"执行了");
        super.channelInactive(ctx);
    }

}
