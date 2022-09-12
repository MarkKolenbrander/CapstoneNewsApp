package com.markkolenbrander.capstonenewsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.markkolenbrander.capstonenewsapp.adapters.ArticleAdapter
import com.markkolenbrander.capstonenewsapp.databinding.FragmentArticlesListBinding

class ArticlesListFragment : Fragment() {

    private val newsService: NewsService = InMemoryNewsServiceImpl()
//    private lateinit var articleDataManager: ArticleDataManager

//    private lateinit var adapter: ArticleAdapter
//    private lateinit var articleList: Article

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        arguments?.let {
//            articleList = it.getParcelable(ARG_LIST)!!
//        }
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArticlesListBinding.inflate(inflater)

//        activity?.let {
//            articleDataManager = ViewModelProvider(this)[ArticleDataManager::class.java]
//        }

        val articlesHardCoded = newsService.getArticles()
        binding.rvArticles.layoutManager = LinearLayoutManager(activity)
        binding.rvArticles.adapter = ArticleAdapter(articlesHardCoded)

        return binding.root
    }

//    companion object{
//
//        private const val ARG_LIST = "list"
//
//        fun newInstance(article: Article) : DetailFragment {
//            val bundle = Bundle()
//            bundle.putParcelable(ARG_LIST, article)
//            val fragment = DetailFragment()
//            fragment.arguments = bundle
//            return fragment
//        }
//    }

}