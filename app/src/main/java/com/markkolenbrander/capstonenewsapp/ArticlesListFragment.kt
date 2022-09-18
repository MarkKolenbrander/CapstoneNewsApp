package com.markkolenbrander.capstonenewsapp

import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.markkolenbrander.capstonenewsapp.adapters.ArticleAdapter
import com.markkolenbrander.capstonenewsapp.databinding.FragmentArticlesListBinding
import com.markkolenbrander.capstonenewsapp.models.Result
import com.markkolenbrander.capstonenewsapp.networking.NetworkStatusChecker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setArticles()
    }

    private fun setArticles(){
        networkStatusChecker.performIfConnectedToInternet {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO){
                val result = remoteApi.getArticles()
                withContext(Dispatchers.Main){
                    when (result) {
                        is Result.Success -> {
                            val articleAdapter = ArticleAdapter(result.data.articles) {
                                val direction =
                                    ArticlesListFragmentDirections.actionArticlesListFragmentToDetailFragment(
                                        it
                                    )
                                findNavController().navigate(direction)
                            }
                            binding.rvArticles.run {
                                adapter = articleAdapter
                                swipeToRefresh()
                            }
                        }
                        is Result.Failure -> {
                            failureDialog()
                        }
                    }
                }
            }
        }

        if (!networkStatusChecker.hasInternetConnection()){
            noInternet()
            binding.btnRefresh.setOnClickListener {
                setArticles()
            }
        }else{
            binding.rvArticles.visibility = View.VISIBLE
            binding.ivNoInternet.visibility = View.GONE
            binding.tvNoInternet.visibility = View.GONE
            binding.btnRefresh.visibility = View.GONE
        }
    }

    private fun failureDialog(){
        val dialogTitle = "We are sorry!"
        val dialogMessage = Result.Failure(Exception("The news articles could not be loaded")).toString()
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

        binding.rvArticles.visibility = View.GONE

        binding.ivNoInternet.visibility = View.VISIBLE
        binding.tvNoInternet.visibility = View.VISIBLE
        binding.btnRefresh.visibility = View.VISIBLE
        binding.ivNoInternet.setImageResource(R.drawable.ic_no_wifi)
    }

    private fun swipeToRefresh(){
        val swipe : SwipeRefreshLayout = binding.srLayout
        swipe.setOnRefreshListener {
            setArticles()
            swipe.isRefreshing = false
        }
    }
}