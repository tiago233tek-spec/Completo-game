package com.demenor.gameturbo

import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.IBinder
import android.provider.Settings
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class OverlayService : Service() {

    private lateinit var windowManager: WindowManager
    private var overlayView: View? = null

    override fun onCreate() {
        super.onCreate()

        if (!Settings.canDrawOverlays(this)) {
            stopSelf()
            return
        }

        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(25, 20, 25, 20)
            setBackgroundColor(Color.rgb(18, 23, 29))
        }

        val title = TextView(this).apply {
            text = "🎮 DEMENOR GAME TURBO"
            textSize = 18f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 15)
        }

        layout.addView(title)

        val fps = TextView(this).apply {
            text = "FPS: monitoramento ativo"
            textSize = 15f
            setTextColor(Color.WHITE)
            setPadding(0, 8, 0, 8)
        }

        layout.addView(fps)

        val network = TextView(this).apply {
            text = "REDE: monitoramento ativo"
            textSize = 15f
            setTextColor(Color.WHITE)
            setPadding(0, 8, 0, 8)
        }

        layout.addView(network)

        val closeButton = Button(this).apply {
            text = "FECHAR PAINEL"

            setOnClickListener {
                stopSelf()
            }
        }

        layout.addView(closeButton)

        overlayView = layout

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        params.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        params.y = 100

        windowManager.addView(overlayView, params)
    }

    override fun onDestroy() {
        super.onDestroy()

        overlayView?.let {
            try {
                windowManager.removeView(it)
            } catch (_: Exception) {
            }
        }

        overlayView = null
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
