package com.markkolenbrander.capstonenewsapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.markkolenbrander.capstonenewsapp.databinding.ArticleViewBinding
import com.markkolenbrander.capstonenewsapp.models.Article

class ArticleView @JvmOverloads constructor  (
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding = ArticleViewBinding.inflate(LayoutInflater.from(context), this )

    // Todo: (set data fun) Make 2 functions - 1. With Title Source - 2. Rest of details
    fun setArticleData(article: Article, onDeleteTapped: () -> Unit){

        binding.tvSourceId.text = article.source.id
        binding.tvSourceName.text = article.source.name
        binding.tvSourceDescription.text = article.source.description
        binding.tvSourceUrl.text = article.source.url
        binding.tvArticleAuthor.text = article.author
        binding.tvArticleTitle.text = article.title
        binding.tvArticleDescription.text = article.description
        binding.tvArticleUrl.text = article.url
        binding.tvArticleUrlImage.text = article.urlToImage
        binding.tvArticlePublishedAt.text = article.publishedAt
        binding.tvArticleContent.text = article.content
        setOnDeleteTapped(onDeleteTapped)
    }

    private fun setOnDeleteTapped(onDeleteTapped: () -> Unit){
        binding.ibDelete.setOnClickListener {
            onDeleteTapped()
        }
    }

}