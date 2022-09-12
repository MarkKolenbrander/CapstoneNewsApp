package com.markkolenbrander.capstonenewsapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.markkolenbrander.capstonenewsapp.models.Article
import com.markkolenbrander.capstonenewsapp.views.ArticleView
import com.markkolenbrander.capstonenewsapp.views.ArticleViewHolder
import com.markkolenbrander.capstonenewsapp.views.DetailView

class ArticleAdapter(private val articlesList: ArrayList<Article?>) : RecyclerView.Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val articleView = ArticleView(parent.context)
        articleView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )

        val detailView = DetailView(parent.context)
        detailView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
        )
        return ArticleViewHolder(articleView, detailView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        articlesList[position]?.let {
            holder.bindData(it){
                removeArticleIndex(holder.absoluteAdapterPosition)
            }
        }
        articlesList[position]?.let { holder.setDetailData(it) }
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    private fun removeArticleIndex(index: Int) {
        articlesList.removeAt(index)
        notifyItemRemoved(index)
    }
}