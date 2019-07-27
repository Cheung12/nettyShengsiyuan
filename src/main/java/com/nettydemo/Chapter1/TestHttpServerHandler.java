package com.nettydemo.Chapter1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

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
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {

        if(httpObject instanceof HttpRequest){

            ByteBuf content= Unpooled.copiedBuffer("hello netty", CharsetUtil.UTF_8);

            FullHttpResponse response=new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            channelHandlerContext.writeAndFlush(response);
        }

    }
}
