package com.markkolenbrander.capstonenewsapp

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.work.*
import com.bumptech.glide.Glide
import com.markkolenbrander.capstonenewsapp.databinding.FragmentDetailBinding
import com.markkolenbrander.capstonenewsapp.worker.DownloadImageWorker
import com.markkolenbrander.capstonenewsapp.worker.DownloadWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()
    private val item = args.article.urlToImage

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
        binding.tvArticlePublishedAt.text = args.article.publishedAt
        binding.tvArticleContent.text = args.article.content
        binding.tvArticleTitle.text = args.article.title

        //Todo: Check here to go further
        args.article.urlToImage?.let { onImageDownload(it) }

//        val imgView = binding.ivImgUrl
//        val item = args.article.urlToImage
//        context?.let { Glide.with(it).load(item).into(imgView) }
    }


    private fun onImageDownload(imageUrl: String) {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .build()

        val downloadImageWorker = OneTimeWorkRequestBuilder<DownloadImageWorker>()
            .setInputData(workDataOf("image_path" to imageUrl))
            .setConstraints(constraints)
            .build()

        val workManager = context?.let { WorkManager.getInstance(it) }
        workManager?.enqueue(downloadImageWorker)

        workManager?.getWorkInfoByIdLiveData(downloadImageWorker.id)
            ?.observe(viewLifecycleOwner, Observer { info ->
                if (info.state.isFinished) {
                    val imageFile =
                        activity?.let { Glide.with(it).asFile().load(item).submit().get() }
                    if (imageFile != null) {
                        displayImage(imageFile.path)
                    }
                }
            })
    }

    private fun displayImage(imagePath: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val bitmap = loadImageFromFile(imagePath)
            binding.ivImgUrl.setImageBitmap(bitmap)
        }
    }

    private suspend fun loadImageFromFile(imagePath: String) = withContext(Dispatchers.IO) {
        BitmapFactory.decodeFile(imagePath)
    }
}