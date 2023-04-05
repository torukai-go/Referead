package com.toru.referead.ui.books

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_foreground)
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
                        authorTv.isVisible=true
                        return false
                    }

                })
                .into(bookCoverImage)

            descriptionTv.text = book.volumeInfo.description


        }
    }
}