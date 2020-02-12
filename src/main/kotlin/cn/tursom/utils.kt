package cn.tursom

import io.netty.buffer.ByteBuf


private val HEX_ARRAY = "0123456789abcdef".toCharArray()
fun ByteArray.toHexString(): String {
  val hexChars = CharArray(size * 2)
  for (i in indices) {
    val b = this[i].toInt()
    hexChars[i shl 1] = HEX_ARRAY[b ushr 4 and 0x0F]
    hexChars[(i shl 1) + 1] = HEX_ARRAY[b and 0x0F]
  }
  return String(hexChars)
}

fun ByteArray.toUTF8String() = toString(Charsets.UTF_8)

fun ByteBuf.copyArray(): ByteArray {
  return if (hasArray()) {
    array().copyOfRange(readerIndex(), writerIndex())
  } else {
    val buffer = ByteArray(readableBytes())
    this.readBytes(buffer)
    buffer
  }
}


private val logDateFormat = ThreadLocalSimpleDateFormat("yyyy-MM-dd HH:mm:ss")
fun log(str: String) {
  println("${System.currentTimeMillis()}: ${logDateFormat.format(System.currentTimeMillis())}: $str")
}

fun log(obj: Any?) = log(obj.toString())

fun logE(str: String) {
  System.err.println("${System.currentTimeMillis()}: ${logDateFormat.format(System.currentTimeMillis())}: $str")
}

fun logE(obj: Any?) = logE(obj.toString())