package com.markkolenbrander.capstonenewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.markkolenbrander.capstonenewsapp.databinding.ActivityMainBinding
import com.markkolenbrander.capstonenewsapp.models.*
import com.markkolenbrander.capstonenewsapp.views.ArticleView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val articles = InMemoryNewsServiceImpl().getArticles()
        val notNullFilter = articles.filterNotNull() as ArrayList<Article>
        makingTextViews(notNullFilter)
    }

    private fun makingTextViews(articles: ArrayList<Article>) {

        articles.forEach { article ->
            val articleView = ArticleView(this)
            articleView.setArticleData(article)
            binding.llMain.addView(articleView)
        }
    }
}