package com.markkolenbrander.capstonenewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.markkolenbrander.capstonenewsapp.databinding.ActivityMainBinding
import com.markkolenbrander.capstonenewsapp.databinding.ArticleViewBinding
import com.markkolenbrander.capstonenewsapp.models.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val articles = NewsService().getArticles()
        makingTextViews(articles)
    }


    private fun makingTextViews(articles: ArrayList<Article>){

        articles.forEach { article ->
            ArticleViewBinding.inflate(
                layoutInflater, binding.llMain, true).apply {
                tvSourceId.text = article.source.id ?: "empty"
                tvSourceName.text = article.source.name
                tvSourceDescription.text = article.source.description
                tvSourceUrl.text = article.source.url
                tvArticleAuthor.text = article.author ?: "empty"
                tvArticleTitle.text = article.title
                tvArticleDescription.text = article.description ?: "empty"
                tvArticleUrl.text = article.url
                tvArticleUrlImage.text = article.urlToImage ?: "empty"
                tvArticlePublishedAt.text = article.publishedAt
                tvArticleContent.text = article.content
            }
        }
    }

}