package com.emrp.client

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import com.emrp.client.engine.EMControlsView
import com.emrp.client.engine.EMInput
import com.emrp.client.engine.EMSampGameView

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val input = EMInput()
        val gameView = EMSampGameView(this)
        val controls = EMControlsView(this, input)

        val root = FrameLayout(this)

        root.addView(
            gameView,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )

        root.addView(
            controls,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )

        setContentView(root)
    }
}
