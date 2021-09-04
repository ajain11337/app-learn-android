package com.ajain11337.base.lifecycle

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * Link : https://stackoverflow.com/questions/13856180/usage-of-forcelayout-requestlayout-and-invalidate
 * Custom view example : https://medium.com/@sahoosunilkumar/understanding-view-lifecycle-in-android-e42890aab16
 */
class CustomView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
    }

    /**
     * On invalidate() -> goes to dispatchToDraw()
     * On requestLayout() -> goes to measure()
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}