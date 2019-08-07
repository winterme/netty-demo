package src.zzq.netty01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class ClientHandler extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println( cause.toString() );
        System.out.println("出错了...");
        ctx.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println( "注册成功..." );
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println( "激活成功..." );
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte [] data = new byte[buf.readableBytes()];
        buf.readBytes(data);

        System.out.println(  "收到了消息：" + new String(data).trim() );
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("我读完了...");
        /*String reps = "客户端收到了服务端发来的消息..." + new Date();
        ctx.writeAndFlush(Unpooled.copiedBuffer(reps.getBytes()));*/
    }
}
