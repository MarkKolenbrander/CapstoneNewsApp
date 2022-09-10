package com.markkolenbrander.capstonenewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.markkolenbrander.capstonenewsapp.adapters.ArticleAdapter
import com.markkolenbrander.capstonenewsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val newsService: NewsService = InMemoryNewsServiceImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val articles = newsService.getArticles()

        binding.rvArticles.adapter = ArticleAdapter(articles)
    }
}