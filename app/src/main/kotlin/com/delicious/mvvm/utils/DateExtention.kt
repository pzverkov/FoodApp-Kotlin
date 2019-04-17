package com.delicious.mvvm.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.simpleDateFormat(): String {
  return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(this)
}

fun Date.simpleTimeFormat(): String {
  return SimpleDateFormat("HH:mm", Locale.getDefault()).format(this)
}