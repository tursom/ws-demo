package cn.tursom

import java.text.SimpleDateFormat
import java.util.*

class ThreadLocalSimpleDateFormat(val format: String) {
  private val threadLocal = ThreadLocal<SimpleDateFormat>()

  fun get(): SimpleDateFormat {
    var simpleDateFormat: SimpleDateFormat? = threadLocal.get()
    if (simpleDateFormat == null) {
      simpleDateFormat = SimpleDateFormat(format)
      threadLocal.set(simpleDateFormat)
    }
    return simpleDateFormat
  }

  fun format(obj: Any): String {
    return get().format(obj)
  }

  fun format(date: Date): String {
    return get().format(date)
  }
}