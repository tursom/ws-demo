package cn.tursom

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.FullHttpMessage

class HttpInboundLogger : SimpleChannelInboundHandler<FullHttpMessage>(false) {
  override fun channelRead0(ctx: ChannelHandlerContext, msg: FullHttpMessage) {
    log("recv http message: \n$msg")
    ctx.fireChannelRead(msg)
  }
}