package com.zxdmjr.readmoretextviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zxdmjr.readmoretextview.internal.ReadMoreListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ReadMoreListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvRead.listener = this
    }

    override fun onReadMore(text: String) {
        Log.d("MainActivity", "onReadMore: $text")
    }
}
