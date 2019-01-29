package com.tomfevang.tictactoe

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playAI: Button = findViewById(R.id.btnPlayAI)
        playAI.setOnClickListener { v: View? ->
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("aiMode", true)
            startActivity(intent)
        }

        val playFriend: Button = findViewById(R.id.btnPlayFriend)
        playFriend.setOnClickListener{
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(intent)
        }
    }
}
