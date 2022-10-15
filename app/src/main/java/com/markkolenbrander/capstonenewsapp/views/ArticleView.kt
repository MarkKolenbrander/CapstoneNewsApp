package com.markkolenbrander.capstonenewsapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.markkolenbrander.capstonenewsapp.R
import com.markkolenbrander.capstonenewsapp.databinding.ArticleViewBinding
import com.markkolenbrander.capstonenewsapp.models.Article

class ArticleView @JvmOverloads constructor  (
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding = ArticleViewBinding.inflate(LayoutInflater.from(context), this )

    fun setArticleData(article: Article){
        binding.tvSourceName.text = article.source.name
        binding.tvArticleTitle.text = article.title
//        binding.tvArticleAuthor.text = article.author ?: ""
        binding.tvArticlePublishedAt.text = article.publishedAt ?: ""
//        setOnDeleteTapped(onDeleteTapped)

        val imgView = binding.ivImgUrl
        val item = article.urlToImage
        if (item.isNullOrBlank()){
            binding.ivImgUrl.setImageResource(R.drawable.ic_no_pictures)
        }else{
            context?.let { Glide.with(it).load(item).into(imgView) }
        }

    }

//    private fun setOnDeleteTapped(onDeleteTapped: () -> Unit){
//        binding.ibDelete.setOnClickListener {
//            onDeleteTapped()
//        }
//    }
}