package com.markkolenbrander.capstonenewsapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.markkolenbrander.capstonenewsapp.models.Article
import com.markkolenbrander.capstonenewsapp.views.ArticleView
import com.markkolenbrander.capstonenewsapp.views.ArticleViewHolder

//I changed this for an LazyColumn with Compose
class ArticleAdapter(
    private val articlesList: List<Article?>,
    private val onArticleTapped: (Article) -> Unit,
) : RecyclerView.Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val articleView = ArticleView(parent.context)
        articleView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        return ArticleViewHolder(articleView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            articlesList[position]?.let { it1 -> onArticleTapped(it1) }
        }
        articlesList[position]?.let { holder.bindData(it) }
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }
}