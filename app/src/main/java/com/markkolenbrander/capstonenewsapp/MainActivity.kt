package com.markkolenbrander.capstonenewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.children
import com.markkolenbrander.capstonenewsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val articles = NewsService()
        makingTextViews(articles = articles)

    }


    private fun makingTextViews(articles: NewsService){

        val mainGroup = binding.llMain
        var count = 0
        for (text in mainGroup.children){
            if (text is TextView){

                val content = (articles.articles[count].source.id  ?: "...") + "\n"+
                        articles.articles[count].source.name + "\n" +
                        (articles.articles[count].author ?: "...") + "\n"+
                        articles.articles[count].title + "\n"+
                        (articles.articles[count].description ?: "...") + "\n"+
                        articles.articles[count].url + "\n"+
                        (articles.articles[count].urlToImage ?: "...") + "\n"+
                        articles.articles[count].publishedAt + "\n"+
                        articles.articles[count].content + "\n"
                text.text = content
                count ++
            }
        }
    }
}