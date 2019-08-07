package src.zzq.netty01;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class Client {
    public static void main(String[] args) throws InterruptedException {

        // 创建线程组
        NioEventLoopGroup group = new NioEventLoopGroup();

        // 创建辅助工具类
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group) //绑定线程组
                .channel(NioSocketChannel.class) // 指定 NIO 模式
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ClientHandler()); // 在这里配置数据接收处理的方法(类)
                    }
                });

        // 绑定连接地址
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",6666).sync();

        // 向服务端发送消息
        channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer("你好，一加一对于多少".getBytes()));

        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
