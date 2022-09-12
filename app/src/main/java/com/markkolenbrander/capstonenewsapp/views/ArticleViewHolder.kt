package com.markkolenbrander.capstonenewsapp.views


import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.markkolenbrander.capstonenewsapp.R
import com.markkolenbrander.capstonenewsapp.models.Article

class ArticleViewHolder(private val articleView: ArticleView, val detailView: DetailView) : View.OnClickListener ,RecyclerView.ViewHolder(articleView) {

    init {
       articleView.setOnClickListener(this)
    }

    fun bindData(article: Article, onDeleteTapped: () -> Unit){
        articleView.setArticleData(article, onDeleteTapped)
    }

    fun setDetailData(article: Article){
        detailView.setArticleDetails(article)
    }

    override fun onClick(view: View?) {
        view?.findNavController()?.navigate(R.id.action_articlesListFragment_to_detailFragment)
    }
}
