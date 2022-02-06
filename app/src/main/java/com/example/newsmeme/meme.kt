package com.example.newsmeme

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class meme : AppCompatActivity() {
    var currentimage:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meme)
        loadmeme()

    }
    private fun loadmeme(){
        val j=findViewById<ProgressBar>(R.id.progress)
        j.visibility=View.VISIBLE

        val url = "https://meme-api.herokuapp.com/gimme"
        val k=findViewById<ImageView>(R.id.imageView2)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
              currentimage=response.getString("url")
                Glide.with(this).load(currentimage).listener(object :RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        j.visibility=View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                       j.visibility=View.GONE
                        return false
                    }
                }).into(k)
            },
            Response.ErrorListener { error ->

            }
        )



        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    fun nextmeme(view: android.view.View) {
        loadmeme()
    }
    fun sharememe(view: android.view.View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.type="image/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Lord Pritam choosed this meme for you $currentimage")
        val chooser=Intent.createChooser(intent, "Share this meme using...")
        startActivity(chooser)
    }
}