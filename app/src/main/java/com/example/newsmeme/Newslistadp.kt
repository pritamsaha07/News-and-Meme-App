package com.example.newsmeme

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewslistAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items:ArrayList<News_m> =ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder=NewsViewHolder(view)
        view.setOnClickListener{
            listener.onitemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
       val currentItem=items[position]
        holder.titleView.text=currentItem.title
        holder.author.text=currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updatedNews:ArrayList<News_m>){
        items.clear()
        items.addAll(updatedNews)
      notifyDataSetChanged()
    }
}
class NewsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    val titleView:TextView=itemView.findViewById(R.id.title)
    val image:ImageView=itemView.findViewById(R.id.image)
    val author:TextView=itemView.findViewById(R.id.author)


}

interface NewsItemClicked{
    fun onitemClicked(item:News_m)
}