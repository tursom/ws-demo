package cn.tursom

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.http.HttpObjectAggregator
import io.netty.handler.codec.http.HttpServerCodec
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler
import io.netty.handler.timeout.ReadTimeoutHandler

fun main() {
  val port = 8086
  val group = NioEventLoopGroup()
  val b = ServerBootstrap().group(group)
    .channel(NioServerSocketChannel::class.java)
    .childHandler(object : ChannelInitializer<SocketChannel>() {
      override fun initChannel(ch: SocketChannel) {
        ch.pipeline()
          .addLast(ReadTimeoutHandler(60 * 1000))
          .addLast("codec", HttpServerCodec())
          .addLast("aggregator", HttpObjectAggregator(512 * 1024))
          .addLast("ws", WebSocketServerProtocolHandler("/ws"))
          .addLast(BinaryWSFrameHandler())
          .addLast(TextWSFrameHandler())
      }
    })
    .option(ChannelOption.SO_BACKLOG, 1024) // determining the number of connections queued
    .option(ChannelOption.SO_REUSEADDR, true)
    .childOption(ChannelOption.SO_KEEPALIVE, java.lang.Boolean.TRUE)
  val future: ChannelFuture = b.bind(port)
  future.sync()
  log("web socket server started on port: $port")
}