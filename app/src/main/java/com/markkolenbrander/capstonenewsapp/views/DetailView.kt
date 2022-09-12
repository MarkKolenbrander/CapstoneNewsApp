package com.markkolenbrander.capstonenewsapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.markkolenbrander.capstonenewsapp.databinding.DetailViewBinding
import com.markkolenbrander.capstonenewsapp.models.Article

class DetailView @JvmOverloads constructor  (
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding = DetailViewBinding.inflate(LayoutInflater.from(context), this )


    fun setArticleDetails(article: Article){
        binding.tvSourceId.text = article.source.id
        binding.tvSourceDescription.text = article.source.description
        binding.tvSourceUrl.text = article.source.url
        binding.tvArticleDescription.text = article.description
        binding.tvArticleUrl.text = article.url
        binding.tvArticleUrlImage.text = article.urlToImage
        binding.tvArticleContent.text = article.content
    }
}