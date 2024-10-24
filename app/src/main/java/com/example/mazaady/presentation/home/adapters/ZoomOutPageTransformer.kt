package com.example.mazaady.presentation.home.adapters

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class ZoomOutPageTransformer: ViewPager2.PageTransformer {

    private val minScale = 0.75f // Minimum scale for non-selected items

    override fun transformPage(page: View, position: Float) {
        when {
            position < -1 -> { // [-Infinity,-1)
                page.alpha = 0f
            }
            position <= 0 -> { // [-1,0]
                page.alpha = 1 + position

                val scale = minScale + (1 - minScale) * (1 + position)
                page.scaleX = scale
                page.scaleY = scale
            }
            position <= 1 -> { // (0,1]
                page.alpha = 1 - position
                val scale = minScale + (1 - minScale) * (1 - position)
                page.scaleX = scale
                page.scaleY = scale
            }
            else -> { // (1,+Infinity]
                // This page is way off-screen to the right
                page.alpha = 0f
            }
        }
    }
}