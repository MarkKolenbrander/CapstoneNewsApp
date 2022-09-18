package com.markkolenbrander.capstonenewsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.markkolenbrander.capstonenewsapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSourceId.text = args.article.source?.name
        binding.tvSourceDescription.text = args.article.description
        binding.tvSourceUrl.text = args.article.source?.url
        binding.tvArticleAuthor.text = args.article.author
        binding.tvArticleDescription.text = args.article.url
        binding.tvArticleUrlImage.text = args.article.urlToImage
        binding.tvArticlePublishedAt.text = args.article.publishedAt
        binding.tvArticleContent.text = args.article.content
        binding.tvArticleTitle.text = args.article.title
    }
}