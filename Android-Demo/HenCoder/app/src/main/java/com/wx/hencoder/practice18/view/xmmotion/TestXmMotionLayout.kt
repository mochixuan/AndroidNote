package com.wx.hencoder.practice18.view.xmmotion

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.Button
import com.wx.hencoder.R

class TestXmMotionLayout : ConstraintLayout {

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val xmView = findViewById<XmMotionLayoutView>(R.id.xm_view)
        val xmView1 = findViewById<XmMotionLayoutView>(R.id.xm_view1)

        findViewById<Button>(R.id.btn_change).setOnClickListener {
            if (xmView.getConnectState() == ConnectState.CONNECT) {
                xmView.setConnectState(ConnectState.NOCONNECT)
            } else {
                xmView.setConnectState(ConnectState.CONNECT)
            }
            if (xmView1.getConnectState() == ConnectState.CONNECT) {
                xmView1.setConnectState(ConnectState.NOCONNECT)
            } else {
                xmView1.setConnectState(ConnectState.CONNECT)
            }
        }

    }

}
