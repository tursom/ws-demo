package cn.tursom

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame

class TextWSFrameHandler : SimpleChannelInboundHandler<TextWebSocketFrame>() {
  override fun channelRead0(ctx: ChannelHandlerContext, msg: TextWebSocketFrame) {
    val text = msg.text()!!
    log(text)
    ctx.writeAndFlush(msg.copy())
  }
}