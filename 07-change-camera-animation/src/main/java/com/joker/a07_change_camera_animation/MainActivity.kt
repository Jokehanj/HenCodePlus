package com.joker.a07_change_camera_animation

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mStartAnimation.setOnClickListener {
            startFlipAnimation()
        }
    }

    private fun startFlipAnimation(delay: Long = 0) {
        val bottomRotateAnimator = ObjectAnimator.ofFloat(mCameraView, "bottomRotateX", 45f)
        bottomRotateAnimator.duration = 2000

        val rotateAnimator = ObjectAnimator.ofFloat(mCameraView, "rotate", 270f)
        rotateAnimator.duration = 2000

        val topRotateAnimator = ObjectAnimator.ofFloat(mCameraView, "topRotateX", -45f)
        topRotateAnimator.duration = 2000

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(bottomRotateAnimator, rotateAnimator, topRotateAnimator)
        animatorSet.startDelay = delay
        animatorSet.start()

        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                mCameraView.bottomRotateX = 0f
                mCameraView.topRotateX = 0f
                mCameraView.rotate = 0f
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }
        })
    }
}
