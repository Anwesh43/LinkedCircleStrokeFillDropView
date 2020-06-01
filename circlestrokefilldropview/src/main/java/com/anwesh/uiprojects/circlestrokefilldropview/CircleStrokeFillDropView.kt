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
val parts : Int = 3
val scGap : Float = 0.02f / parts
val sizeFactor : Float = 3f
val backColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 20
val startDeg : Float = 270f
val sweepDeg : Float = 180f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawStrokeFillDropCircle(scale : Float, w : Float, h : Float, paint : Paint) {
    val r : Float = Math.min(w, h) / sizeFactor
    val sf : Float = scale.sinify()
    val sf1 : Float = sf.divideScale(0, 3)
    val sf2 : Float = sf.divideScale(1, 3)
    val sf3 : Float = sf.divideScale(2, 3)
    val sd : Float = sweepDeg * sf1
    save()
    translate(0f, (h * 0.5f + r) * sf3)
    drawArc(RectF(-r, -r, r, r), startDeg - sd, 2 * sd, false, paint)
    drawCircle(0f, 0f, r * sf2, paint)
    restore()
}

fun Canvas.drawSFDNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    save()
    translate(w / 2, h / 2)
    drawStrokeFillDropCircle(scale, w, h, paint)
    restore()
}

class CircleStrokeFillDropView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }

    data class State(var scale : Float = 0f, var dir : Float = 0f, var prevScale : Float = 0f) {

        fun update(cb : (Float) -> Unit) {
            scale += scGap * scale
            if (Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                cb(prevScale)
            }
        }

        fun startUpdating(cb : () -> Unit) {
            if (dir == 0f) {
                dir = 1f - 2 * prevScale
                cb()
            }
        }
    }
}