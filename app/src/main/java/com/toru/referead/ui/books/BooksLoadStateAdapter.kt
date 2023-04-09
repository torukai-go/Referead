package com.toru.referead.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.toru.referead.databinding.BooksLoadStateFooterBinding

class BooksLoadStateAdapter(private val retry:()->Unit):
    LoadStateAdapter<BooksLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = BooksLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false
        )
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(private val binding: BooksLoadStateFooterBinding):
        RecyclerView.ViewHolder(binding.root){

            init {
                binding.retryBtn.setOnClickListener {
                    retry.invoke()
                }
            }

            fun bind(loadState: LoadState){
                binding.apply {
                    progressBar.isVisible = loadState is LoadState.Loading
                    retryBtn.isVisible = loadState !is LoadState.Loading
                    errorTv.isVisible = loadState !is LoadState.Loading
                }
            }
    }
}
