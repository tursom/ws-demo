package cn.tursom

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelOutboundHandlerAdapter
import io.netty.channel.ChannelPromise
import io.netty.handler.codec.http.HttpObject

class HttpOutboundLogger : ChannelOutboundHandlerAdapter() {
  override fun write(ctx: ChannelHandlerContext?, msg: Any?, promise: ChannelPromise?) {
    if(msg is HttpObject){
      log("send http message: \n$msg")
    }
    super.write(ctx, msg, promise)
  }
}