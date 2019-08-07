package src.zzq.neety02;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.Buffer;

public class Server {
    public static void main(String[] args) throws InterruptedException {
        //1 创建线两个程组
        //一个是用于处理服务器端接收客户端连接的
        //一个是进行网络通信的（网络读写的）
        NioEventLoopGroup pGroup = new NioEventLoopGroup();
        NioEventLoopGroup cGroup = new NioEventLoopGroup();

        //2.创建辅助工具类，用于服务器通信的一系列配置
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(pGroup,cGroup) // 绑定两个线程组
        .channel(NioServerSocketChannel.class) // 指定NIO模式
        .option(ChannelOption.SO_KEEPALIVE , true) // 设置保持连接
        .childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel sc) throws Exception {
                // 设置特殊分隔符
                ByteBuf buf = Unpooled.copiedBuffer("$end$".getBytes());
                sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));

                // 设置字符串形式的解码
                sc.pipeline().addLast(new StringDecoder());

                // 3. 在这里配置具体的数据处理方法
                sc.pipeline().addLast(new ServerHandler());
            }
        });

        //4 进行绑定
        ChannelFuture cf1 = bootstrap.bind(8888).sync();
        //ChannelFuture cf2 = b.bind(8764).sync();
        //5 等待关闭
        cf1.channel().closeFuture().sync();
        //cf2.channel().closeFuture().sync();
        pGroup.shutdownGracefully();
        cGroup.shutdownGracefully();
    }
}
