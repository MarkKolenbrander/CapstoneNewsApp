package com.markkolenbrander.capstonenewsapp.views

import androidx.recyclerview.widget.RecyclerView
import com.markkolenbrander.capstonenewsapp.models.Article

class ArticleViewHolder(private val articleView: ArticleView) : RecyclerView.ViewHolder(articleView) {

    fun bindData(article: Article, onDeleteTapped: () -> Unit){
        articleView.setArticleData(article, onDeleteTapped)
    }
}
