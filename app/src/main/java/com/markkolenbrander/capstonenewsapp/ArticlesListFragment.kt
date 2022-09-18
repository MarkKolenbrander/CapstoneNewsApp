package com.markkolenbrander.capstonenewsapp

import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.markkolenbrander.capstonenewsapp.adapters.ArticleAdapter
import com.markkolenbrander.capstonenewsapp.databinding.FragmentArticlesListBinding
import com.markkolenbrander.capstonenewsapp.models.Success
import com.markkolenbrander.capstonenewsapp.networking.NetworkStatusChecker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.M)
class ArticlesListFragment : Fragment() {

    private lateinit var binding : FragmentArticlesListBinding
    private val remoteApi = App.remoteApi
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlesListBinding.inflate(layoutInflater)
        return binding.root
    }

    //TODO: Make function under, and include failure function

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        networkStatusChecker.performIfConnectedToInternet {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO){
                val result = remoteApi.getArticles()
                withContext(Dispatchers.Main){
                    if (result is Success){
                        val articleAdapter = ArticleAdapter(result.data.articles) {
                            val direction = ArticlesListFragmentDirections.actionArticlesListFragmentToDetailFragment(it)
                            findNavController().navigate(direction)
                        }
                        binding.rvArticles.run {
                            adapter = articleAdapter
                        }
                    }else{
                        // TODO: No success block
                    }
                }
            }
        }
    }
}