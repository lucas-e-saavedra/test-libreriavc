package com.example.testtokbox

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.mylibrary.TokboxHelper
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    private val LOG_TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnComenzar.setOnClickListener { comenzarVideoChat() }
    }

    fun comenzarVideoChat(){
        TokboxHelper.getSessionId(this){
            sessionId -> if (sessionId == null)
                btnComenzar.text = "REINTENTAR"
            else {
                TokboxHelper.getToken(this, sessionId){
                    token -> if (token == null)
                        btnComenzar.text = "REINTENTAR"
                    else {
                        TokboxHelper.beginVideoChat(this, sessionId, token)
                    }
                }
            }
        }
    }
}
