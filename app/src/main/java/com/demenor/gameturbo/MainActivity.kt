package com.demenor.gameturbo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val blue = Color.rgb(0, 220, 255)
    private val background = Color.rgb(7, 9, 13)
    private val card = Color.rgb(18, 23, 29)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(30, 45, 30, 30)
            setBackgroundColor(background)
        }

        val title = TextView(this).apply {
            text = "DEMENOR GAME TURBO"
            textSize = 25f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 35)
        }

        root.addView(title)

        addSection(root, "MONITORAMENTO")

        addCard(
            root,
            "FPS",
            "Monitor disponível durante a sessão"
        )

        addCard(
            root,
            "REDE",
            "Monitoramento ativado"
        )

        addCard(
            root,
            "MEMÓRIA",
            "Uso do sistema • informações permitidas pelo Android"
        )

        addSection(root, "ATALHOS DE JOGO")

        addSwitch(root, "Bloquear notificações", true)

        addSwitch(root, "Proteção contra toques acidentais", true)

        addSwitch(root, "Monitorar conexão", true)

        val overlayButton = Button(this).apply {
            text = "ABRIR PAINEL DE JOGO"
            textSize = 16f
            setTextColor(Color.rgb(10, 25, 60))
            setBackgroundColor(blue)

            setOnClickListener {
                abrirPainel()
            }
        }

        root.addView(
            overlayButton,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                65
            ).apply {
                topMargin = 25
            }
        )

        val discord = Button(this).apply {
            text = "ABRIR DISCORD"
            textSize = 15f

            setOnClickListener {
                abrirDiscord()
            }
        }

        root.addView(
            discord,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                60
            ).apply {
                topMargin = 15
            }
        )

        val info = TextView(this).apply {
            text = "Alguns recursos dependem das permissões e APIs disponíveis na versão do Android e no fabricante do aparelho."
            textSize = 13f
            setTextColor(Color.GRAY)
            setPadding(0, 30, 0, 0)
        }

        root.addView(info)

        setContentView(root)
    }

    private fun addSection(root: LinearLayout, text: String) {
        val section = TextView(this).apply {
            this.text = text
            textSize = 18f
            setTextColor(Color.WHITE)
            setPadding(0, 25, 0, 15)
        }

        root.addView(section)
    }

    private fun addCard(
        root: LinearLayout,
        title: String,
        description: String
    ) {
        val cardLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(25, 20, 25, 20)
            setBackgroundColor(card)
        }

        val titleView = TextView(this).apply {
            text = title
            textSize = 20f
            setTextColor(blue)
        }

        val descriptionView = TextView(this).apply {
            text = description
            textSize = 15f
            setTextColor(Color.LTGRAY)
            setPadding(0, 10, 0, 0)
        }

        cardLayout.addView(titleView)
        cardLayout.addView(descriptionView)

        root.addView(
            cardLayout,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                125
            ).apply {
                bottomMargin = 15
            }
