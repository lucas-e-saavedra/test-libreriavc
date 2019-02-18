package com.example.mylibrary

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

object TokboxHelper {
    private val API_URL_SESSION = "https://test-tokbox-assistcard.herokuapp.com/comenzar"
    private val API_URL_TOKEN = "https://test-tokbox-assistcard.herokuapp.com/token"
    private val API_KEY = "46256142"

    private fun getQueue(ctx: Context)= Volley.newRequestQueue(ctx)
    fun getSessionId(ctx: Context, listener:(String?)->Unit) {
        val stringRequest = StringRequest(
            Request.Method.GET, API_URL_SESSION,
            Response.Listener<String> { response: String? -> listener(response) },
            Response.ErrorListener { listener(null) })

        getQueue(ctx).add(stringRequest)
    }

    fun getToken(ctx: Context, sessionId: String, listener:(String?)->Unit){
        if (sessionId==null)
            listener(null)
        else{
            val stringRequest = StringRequest(Request.Method.GET, API_URL_TOKEN,
                Response.Listener<String> { response: String? -> listener(response) },
                Response.ErrorListener { listener(null) })

            getQueue(ctx).add(stringRequest)
        }
    }

    fun beginVideoChat(ctx: Context, sessionId: String, token: String){
        ctx.startActivity(VideoChatActivity.newIntent(ctx, API_KEY, sessionId, token))
    }
}