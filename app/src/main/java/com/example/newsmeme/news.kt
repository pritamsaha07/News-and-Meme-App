package com.example.newsmeme

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class news : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter: NewslistAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val a=findViewById<RecyclerView>(R.id.recycle)
        a.layoutManager=LinearLayoutManager(this)
        fetchData()
        mAdapter= NewslistAdapter( this)
        a.adapter=mAdapter
    }

    private  fun fetchData(){
        val queue = Volley.newRequestQueue(this)
        val url= "https://saurav.tech/NewsAPI/top-headlines/category/sports/in.json"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {
              val newsJsonArray= it.getJSONArray("articles")
                val newsArray=ArrayList<News_m>()
                for(i in 0 until newsJsonArray.length()){
                    val newsJsonObject=newsJsonArray.getJSONObject(i)
                    val news1=News_m(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")

                    )
                    newsArray.add(news1)
                }
                mAdapter.updateNews(newsArray)

            },
            Response.ErrorListener { error ->

            }
        )

        queue.add(jsonObjectRequest)




    }

    override fun onitemClicked(item: News_m) {
       val builder= CustomTabsIntent.Builder()
       val  customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url));

    }
}