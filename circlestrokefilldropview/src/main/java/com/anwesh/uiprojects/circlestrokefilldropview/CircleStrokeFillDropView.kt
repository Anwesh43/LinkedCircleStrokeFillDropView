package com.anwesh.uiprojects.circlestrokefilldropview

/**
 * Created by anweshmishra on 01/06/20.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF
import android.content.Context
import android.app.Activity

val colors : Array<String> = arrayOf("#3F51B5", "#4CAF50", "#03A9F4", "#009688", "#F44336")
val scGap : Float = 0.02f
val sizeFactor : Float = 3f
val backColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 20
val startDeg : Float = 270f
val sweepDeg : Float = 180f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()
