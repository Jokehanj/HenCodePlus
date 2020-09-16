package com.joker.a08_animation

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.graphics.Point
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val targetPoint = Point(500, 500)

        val objectAnimator =
            ObjectAnimator.ofObject(mAnimationView, "point", PointEvaluator(), targetPoint)

        objectAnimator.duration = 2000
        objectAnimator.startDelay = 1000
        objectAnimator.start()

    }

    class PointEvaluator : TypeEvaluator<Point> {
        override fun evaluate(fraction: Float, startValue: Point, endValue: Point): Point {

            return Point(
                (startValue.x + (endValue.x - startValue.x) * fraction).toInt(),
                (startValue.y + (endValue.y - startValue.y) * fraction).toInt()
            )
        }
    }
}
