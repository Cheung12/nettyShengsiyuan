package com.nettydemo.Demo3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @ClassName MyChatServerHandler
 * @Description
 * @Author zhangzhang
 * @DATE 2019-07-28 21:31
 * @VERSION 1.0
 ***/
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    //保存客户端的连接 channel对象
    private static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel=ctx.channel();
        System.out.println(channel.remoteAddress()+" 发送的消息:"+msg+"\r\n");
        channelGroup.forEach(ch->{
            if(channel!=ch){
                ch.writeAndFlush(channel.remoteAddress()+" 发送的消息:"+msg+"\r\n");
            }else {
               // ch.writeAndFlush("【自己】: "+msg+"\r\n");
            }
        });
    }
    /*
     * @Author Cheung12
     * @Description //与客户端建立好链接时触发
     * @Date 21:36 2019-07-28
     * @Param [ctx]
     * @return void
     **/
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel ch=ctx.channel();
        System.out.println("【服务器接收】: "+ch.remoteAddress()+" 加入\r\n");
        channelGroup.add(ch);

    }
    /*
     * @Author Cheung12
     * @Description //与客户端断开连接时触发
     * @Date 21:53 2019-07-28
     * @Param [ctx]
     * @return void
     **/
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel ch=ctx.channel();
        //channelGroup.remove(ch);   可以不写此句，因为当channel与服务器断开时，netty会从channelgroup中自动移除已断开的channel
        System.out.println("【服务器接收】："+ch.remoteAddress()+" 离开\r\n");
    }
    /*
     * @Author Cheung12
     * @Description //连接处于活动状态时触发
     * @Date 21:55 2019-07-28
     * @Param [ctx]
     * @return void
     **/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel ch=ctx.channel();
        channelGroup.forEach(channel -> {
            if(channel!=ch){
                channel.writeAndFlush("【服务器发来消息】："+ch.remoteAddress()+"上线了\r\n");
            }
        });
        //channelGroup.writeAndFlush("【服务器广播】："+ch.remoteAddress()+" 上线了\r\n");
    }
    /*
     * @Author Cheung12
     * @Description //连接处于非活动状态时触发
     * @Date 21:57 2019-07-28
     * @Param [ctx]
     * @return void
     **/
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel ch=ctx.channel();
        channelGroup.forEach(channel -> {
            if(channel!=ch){
                channel.writeAndFlush("【服务器发来消息】："+ch.remoteAddress()+"下线了\r\n");
            }
        });
        //channelGroup.writeAndFlush("【服务器广播】："+ch.remoteAddress()+" 下线了\r\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

}
