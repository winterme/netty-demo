package src.zzq.neety02;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Base64;
import java.util.Date;

public class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 出现异常
        System.out.println( cause.toString() );
        ctx.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("netty is register...");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("netty is active...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String request = (String)msg;
        System.out.println("客户端发来消息："+ request );
        String resp = "服务端响应：收到了你的消息==>" + new Date() + "$end$";

        ctx.writeAndFlush( Unpooled.copiedBuffer(resp.getBytes()) );
        System.out.println("返回数据：" + resp );
     }


}
