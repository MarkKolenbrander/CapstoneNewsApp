package com.markkolenbrander.capstonenewsapp

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.markkolenbrander.capstonenewsapp.adapters.ArticleAdapter
import com.markkolenbrander.capstonenewsapp.databinding.FragmentArticlesListBinding
import com.markkolenbrander.capstonenewsapp.models.Article
import com.markkolenbrander.capstonenewsapp.networking.NetworkStatusChecker
import com.markkolenbrander.capstonenewsapp.utils.CustomResult

class ArticlesListFragment : Fragment() {

    private lateinit var binding : FragmentArticlesListBinding
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }
    private val viewModel: ArticleViewModel by viewModels{
        ArticleViewModel.Factory(newsRepo = App.newsRepo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlesListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchArticles()
        swipeToRefresh()

        val queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let{ searchQuery ->
                    viewModel.searchArticles(searchQuery)
                }
                return true
            }
        }

        binding.svSearchView.setOnQueryTextListener(queryTextListener)
    }

    private fun fetchArticles(){
        binding.srLayout.isRefreshing = true
        viewModel.articles.observe(viewLifecycleOwner) { articleResult ->
            when(articleResult){
                is CustomResult.Success -> {
                    setArticles(articleResult.value)
                }
                is CustomResult.Failure -> {
                    failureDialog()
                }
            }
            binding.srLayout.isRefreshing = false
        }

    }

    private fun setArticles(articles: List<Article?>){

        val articleAdapter = ArticleAdapter(articles) { article ->
            val direction =
                ArticlesListFragmentDirections.actionArticlesListFragmentToDetailFragment(
                    article
                )
            findNavController().navigate(direction)
        }
        binding.rvArticles.run {
            adapter = articleAdapter
        }
    }

    private fun failureDialog(){
        val dialogTitle = "We are sorry!"
        val dialogMessage = CustomResult.Failure(Exception("No data")).toString()
        val builder = activity?.let { AlertDialog.Builder(it) }

        builder?.setTitle(dialogTitle)
        builder?.setMessage(dialogMessage)
        builder?.setIcon(R.drawable.ic_waiting)
        builder?.setPositiveButton("Ok") {dialog, _ ->
            dialog.dismiss()
        }
        builder?.create()?.show()
        binding.ivNoInternet.visibility = View.VISIBLE
        binding.ivNoInternet.setImageResource(R.drawable.ic_waiting)
    }

    private fun noInternet(){
        Snackbar.make(binding.root, "There is no Internet!", Toast.LENGTH_SHORT).show()

        binding.rvArticles.visibility = View.GONE
        binding.ivNoInternet.visibility = View.VISIBLE
        binding.tvNoInternet.visibility = View.VISIBLE
        binding.ivNoInternet.setImageResource(R.drawable.ic_no_wifi)
    }

    private fun swipeToRefresh(){
        val swipe : SwipeRefreshLayout = binding.srLayout
        swipe.setOnRefreshListener {
            fetchArticles()
            swipe.isRefreshing = false
        }
    }

    //With Internet check

    //    private fun fetchArticles(){
//        networkStatusChecker.performIfConnectedToInternet {
//            binding.srLayout.isRefreshing = true
//            viewModel.articles.observe(viewLifecycleOwner) { articleResult ->
//                when(articleResult){
//                    is CustomResult.Success -> {
//                        setArticles(articleResult.value)
//                    }
//                    is CustomResult.Failure -> {
//                        failureDialog()
//                    }
//                }
//                binding.srLayout.isRefreshing = false
//            }
//        }
//
//        if (!networkStatusChecker.hasInternetConnection()){
//            noInternet()
//
//        }else{
//            binding.rvArticles.visibility = View.VISIBLE
//            binding.ivNoInternet.visibility = View.GONE
//            binding.tvNoInternet.visibility = View.GONE
//
//        }
//    }

}