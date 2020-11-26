package com.example.kotlin_hw_lab7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var rabprogress = 0
    private var turprogress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_start.setOnClickListener(View.OnClickListener {
        btn_start.setEnabled(false)
        rabprogress = 0
        turprogress = 0
        seekBar.setProgress(0)
        seekBar2.setProgress(0)
        runThread()
        runCoroutines()
        })
    }

private fun runThread() {
    Thread {
        while (rabprogress <= 100 && turprogress <= 100){
            try {
                Thread.sleep(100)
                rabprogress += (Math.random() * 3).toInt()
                val msg = Message()
                msg.what = 1
                mHandler.sendMessage(msg)
            }catch (e:InterruptedException) {
                e.printStackTrace()
            }
        }
    }.start()
}
private val mHandler = Handler{msg ->
    when(msg.what) {
        1 -> seekBar!!.progress = rabprogress
    }
    if (rabprogress >= 100 && turprogress < 100) {
        Toast.makeText(this@MainActivity,"兔子勝利",
        Toast.LENGTH_SHORT).show()
        btn_start!!.isEnabled = true
    }
        false
}
    private fun runCoroutines()
    {
        GlobalScope.launch{
            while (turprogress <= 100 && rabprogress < 100) {
                try {
                    delay(100L)
                    val msg = Message()
                    msg.what = 1
                    turprogress += (Math.random()*3).toInt()
                    mHandler2.sendMessage(msg)
                }catch (e:InterruptedException){
                    e.printStackTrace()
                }
            }
        }
    }
    private val mHandler2 = Handler {msg ->
        when (msg.what) {
        1 -> seekBar2!!.progress = turprogress
        }
        if (turprogress >= 100 && rabprogress < 100) {
        Toast.makeText(this@MainActivity,"烏龜勝利",Toast.LENGTH_SHORT).show()
        btn_start!!.isEnabled = true
        }
    false
    }
}