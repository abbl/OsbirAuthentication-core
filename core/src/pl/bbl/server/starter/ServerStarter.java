package pl.bbl.server.starter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import pl.bbl.server.handlers.SimpleHandler;

public class ServerStarter {
    private int serverPort;

    public ServerStarter(int serverPort){
        this.serverPort = serverPort;
    }

    public void run() throws Exception{
        //Receive new connections and handles them to connectionHandler
        EventLoopGroup connectionReceiver = new NioEventLoopGroup();
        EventLoopGroup connectionHandler = new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(connectionReceiver, connectionHandler)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new SimpleHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture channelFuture = bootstrap.bind(serverPort).sync();

            channelFuture.channel().closeFuture().sync();
        }finally {
            connectionReceiver.shutdownGracefully();
            connectionHandler.shutdownGracefully();
        }
    }
}
