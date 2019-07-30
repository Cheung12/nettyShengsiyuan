package com.nettydemo.Demo4_readandwrite;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @ClassName MyHeartBeatsHandler
 * @Description
 * @Author zhangzhang
 * @DATE 2019-07-30 22:31
 * @VERSION 1.0
 ***/
public class MyHeartBeatsHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){

            IdleStateEvent event=(IdleStateEvent) evt;

            String EventType=null;

            switch (event.state()){
                case READER_IDLE:
                    EventType="读空闲";
                    break;
                case WRITER_IDLE:
                    EventType="写空闲";
                    break;
                case ALL_IDLE:
                    EventType="读写空闲";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress()+"【超时事件】"+EventType);

            //关闭次句话可看到出发的 所有事件
            //ctx.channel().close();

        }
    }
}
