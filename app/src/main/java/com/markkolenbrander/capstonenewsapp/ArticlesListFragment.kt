package com.markkolenbrander.capstonenewsapp

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.markkolenbrander.capstonenewsapp.composable.ArticleNewsItem
import com.markkolenbrander.capstonenewsapp.databinding.FragmentArticlesListBinding
import com.markkolenbrander.capstonenewsapp.models.Article
import com.markkolenbrander.capstonenewsapp.prefsstore.PrefsStore
import com.markkolenbrander.capstonenewsapp.repo.NewsArticleRepo
import com.markkolenbrander.capstonenewsapp.theme.AppTheme
import com.markkolenbrander.capstonenewsapp.utils.CustomResult
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticlesListFragment : Fragment() {

    private lateinit var binding : FragmentArticlesListBinding

    @Inject
    lateinit var newsArticleRepo: NewsArticleRepo

    @Inject
    lateinit var prefsStore: PrefsStore

    private val viewModel: ArticleViewModel by viewModels{
        ArticleViewModel.Factory(newsRepo = newsArticleRepo, prefsStore = prefsStore)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlesListBinding.inflate(layoutInflater)
        @Suppress("DEPRECATION")
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.articles.observe(viewLifecycleOwner) { articleResult ->
            when(articleResult){
                is CustomResult.Success -> {
                    setArticlesCompose(articleResult.value)
                }
                is CustomResult.Failure -> {
                    failureDialog()
                }
                is CustomResult.NoInternet ->{
                    noInternet()
                    setArticlesCompose(articleResult.value)
                }
            }
        }

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

        viewModel.darkThemeEnabled.observe(viewLifecycleOwner){ nightModeActive ->

            val defaultMode = if (nightModeActive){
                AppCompatDelegate.MODE_NIGHT_YES
            }else{
                AppCompatDelegate.MODE_NIGHT_NO
            }
            AppCompatDelegate.setDefaultNightMode(defaultMode)
        }

    }

    //Compose Function
    private fun setArticlesCompose(articles: List<Article>){
        binding.composeView.setContent {
            AppTheme {
                ArticleNewsItem(article = articles){ article ->
                    val direction =
                ArticlesListFragmentDirections.actionArticlesListFragmentToDetailFragment(article)
                    findNavController().navigate(direction)
                }
            }
        }
    }

    private fun  failureDialog(){
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

//        binding.rvArticles.visibility = View.GONE
//        binding.ivNoInternet.visibility = View.VISIBLE
//        binding.tvNoInternet.visibility = View.VISIBLE
//        binding.ivNoInternet.setImageResource(R.drawable.ic_no_wifi)
    }


    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.overflow_menu, menu)

        val switchView = menu
            .findItem(R.id.app_bar_switch_menu_item)
            .actionView
            ?.findViewById<SwitchCompat>(R.id.app_bar_switch)

        switchView?.isChecked = viewModel.darkThemeEnabled.value ?: false

        switchView?.setOnCheckedChangeListener { _, _ ->
            viewModel.toggleNightMode()
        }
    }
}