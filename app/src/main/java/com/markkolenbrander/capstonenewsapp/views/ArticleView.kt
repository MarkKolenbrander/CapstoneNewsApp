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


    fun setArticleData(article: Article, onDeleteTapped: () -> Unit){
        binding.tvSourceName.text = article.source.name
        binding.tvArticleTitle.text = article.title
        binding.tvArticleAuthor.text = article.author
        binding.tvArticlePublishedAt.text = article.publishedAt
        setOnDeleteTapped(onDeleteTapped)
    }

    //Todo: The rest of the info will go on the detail screen later

//    fun setDetailScreenData(article: Article){
//        binding.tvSourceId.text = article.source.id
//        binding.tvSourceDescription.text = article.source.description
//        binding.tvSourceUrl.text = article.source.url
//        binding.tvArticleDescription.text = article.description
//        binding.tvArticleUrl.text = article.url
//        binding.tvArticleUrlImage.text = article.urlToImage
//        binding.tvArticleContent.text = article.content
//    }

    private fun setOnDeleteTapped(onDeleteTapped: () -> Unit){
        binding.ibDelete.setOnClickListener {
            onDeleteTapped()
        }
    }
}