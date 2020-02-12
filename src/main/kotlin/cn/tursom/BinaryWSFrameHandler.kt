package cn.tursom

import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame

class BinaryWSFrameHandler : SimpleChannelInboundHandler<BinaryWebSocketFrame>() {
  override fun channelRead0(ctx: ChannelHandlerContext, msg: BinaryWebSocketFrame) {
    val buf = msg.content()
    val bytes = buf.copyArray()
    log(bytes.toHexString())
    log(bytes.toUTF8String())
    ctx.writeAndFlush(BinaryWebSocketFrame(Unpooled.wrappedBuffer(bytes)))
  }
}