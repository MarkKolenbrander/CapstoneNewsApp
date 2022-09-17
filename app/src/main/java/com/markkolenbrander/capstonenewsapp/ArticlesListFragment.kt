package com.markkolenbrander.capstonenewsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.markkolenbrander.capstonenewsapp.adapters.ArticleAdapter
import com.markkolenbrander.capstonenewsapp.databinding.FragmentArticlesListBinding

class ArticlesListFragment : Fragment() {

    private lateinit var binding : FragmentArticlesListBinding
    private val newsService: NewsService = InMemoryNewsServiceImpl()
    private lateinit var articleDataManager: ArticleDataManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlesListBinding.inflate(layoutInflater)

        activity?.let {
            articleDataManager = ViewModelProvider(this)[ArticleDataManager::class.java]
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storedArticles = articleDataManager.fetchArticles()
        val articlesToUse = storedArticles.ifEmpty {
            articleDataManager.saveArticles(newsService.getArticles())
            articleDataManager.fetchArticles()
        }

        val articleAdapter = ArticleAdapter(articlesToUse) {
            val direction = ArticlesListFragmentDirections.actionArticlesListFragmentToDetailFragment(it)
            findNavController().navigate(direction)
        }
        binding.rvArticles.run {
            adapter = articleAdapter
        }
    }
}