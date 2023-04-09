package com.toru.referead.ui.books

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgument
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.toru.referead.R
import com.toru.referead.databinding.FragmentBookDetailsBinding

class BookDetailsFragment : Fragment(R.layout.fragment_book_details) {

    private val args by navArgs<BookDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentBookDetailsBinding.bind(view)

        binding.apply {
            val book = args.book

            Glide.with(this@BookDetailsFragment)
                .load(book.volumeInfo.imageLinks?.thumbnail)
                .error(R.drawable.book_icon)
                .placeholder(R.drawable.book_icon)
                .listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible=false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible=false
                        descriptionTv.isVisible=true
                        return false
                    }
                })
                .into(bookCoverImage)

            titleTv.text = book.volumeInfo.title
            subtitleTv.text = book.volumeInfo.subtitle
            descriptionTv.text = book.volumeInfo.description
            idTVpublisher.text= book.volumeInfo.publisher
            idTVNoOfPages.text = "${book.volumeInfo.pageCount} pages"
            idTVPublishDate.text = book.volumeInfo.publishedDate

            idBtnPreview.setOnClickListener {
                val previewLink = book.volumeInfo.previewLink

                try {
                    val uri = Uri.parse(previewLink)
                    val i = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(i)
                }
                catch (e: Exception){
                    Toast.makeText(
                        context,
                        "No preview page present for this book",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            idBtnBuy.setOnClickListener {
                val buyLink = book.saleInfo.buyLink

                try {
                    val uri = Uri.parse(buyLink)
                    val i = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(i)
                }
                catch (e: Exception){
                    Toast.makeText(
                        context,
                        "No buy page present for this book",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}