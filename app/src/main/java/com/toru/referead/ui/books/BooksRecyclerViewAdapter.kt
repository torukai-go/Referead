package com.toru.referead.ui.books

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.toru.referead.R
import com.toru.referead.databinding.FragmentBookBinding

import com.toru.referead.placeholder.PlaceholderContent.PlaceholderItem
import com.toru.referead.model.books.BooksInfo

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class BooksRecyclerViewAdapter(private val listener: OnBookClickListener) : PagingDataAdapter<BooksInfo, BooksRecyclerViewAdapter.BooksViewHolder>(UserComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val binding = FragmentBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BooksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val item = getItem(position)

        if(item!=null){
            holder.bind(item)
        }
    }

    inner class BooksViewHolder(private val binding: FragmentBookBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if(position!=RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if (item != null){
                        listener.onBookClick(item)
                    }
                }
            }
        }

        fun bind (book: BooksInfo){
            binding.apply{
                bookNameTv.text = book.volumeInfo.title
                bookPagesTv.text = "стр. ${book.volumeInfo.pageCount.toString()}"

                Glide.with(itemView)
                    .load(book.volumeInfo.imageLinks?.smallThumbnail)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.book_icon)
                    .error(R.drawable.book_icon)
                    .into(bookIv)
            }
        }
    }

    interface OnBookClickListener {
        fun onBookClick(book: BooksInfo)
    }
}

object UserComparator : DiffUtil.ItemCallback<BooksInfo>() {
    override fun areItemsTheSame(oldItem: BooksInfo, newItem: BooksInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BooksInfo, newItem: BooksInfo): Boolean {
        return oldItem == newItem
    }
}
