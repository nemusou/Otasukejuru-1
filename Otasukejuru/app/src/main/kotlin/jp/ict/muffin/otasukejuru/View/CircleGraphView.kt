package jp.ict.muffin.otasukejuru

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Handler
import android.view.View
import java.util.*


class CircleGraphView(context: Context, private var param: Int, private var time: Long, isInit: Boolean) : View(context) {
    private var startAngleTmp: Float = (60 - time) * 6f
    internal var endAngleTmp: Float = 0.0f
    internal lateinit var timer: Timer
    
    init {
        if (isInit) {
            time = 1
        } else {
            time *= 60 * 100L
        }
        startAngleTmp -= 90
        endAngleTmp = startAngleTmp
    }
    
    public override fun onDraw(c: Canvas) {
        val width = c.width
        val radius = (width / 2f - 30f)
        val startAngle: Float = startAngleTmp % 360
        val x = radius + 15f
        val y = radius + 15f
        val endAngle = Math.min(startAngle + 360, endAngleTmp)
        this.createPieSlice(c, param, startAngle, endAngle, x, y, radius)
    }
    
    private fun createPieSlice(c: Canvas, color: Int, startAngle: Float, endAngle: Float, x: Float, y: Float, r: Float) {
        var paint = Paint()
        paint.isAntiAlias = false
        paint.color = color
        val oval1 = RectF(x - r, y - r, x + r, y + r)
        c.drawArc(oval1, startAngle, endAngle - startAngle, true, paint)
        
        //外枠
        paint = Paint()
        paint.color = Color.argb(255, 251, 251, 240)
        paint.style = Paint.Style.STROKE
        c.drawArc(oval1, startAngle, endAngle - startAngle, true, paint)
    }
    
    fun startAnimation() {
        val handler = Handler()
        val task = object : TimerTask() {
            override fun run() {
                val angle = (360 / 100f)
                endAngleTmp += angle
                if (endAngleTmp > 270f) {
                    endAngleTmp = 270f
                    timer.cancel()
                }
                handler.post { invalidate() }
            }
        }
        
        timer = Timer()
        //アニメーションのスピード調整できるようにしたいところ
        timer.schedule(task, 0, time)
    }
    
    fun changeParam(param: Int) {
        this.param = param
        this.invalidate()
    }
}
