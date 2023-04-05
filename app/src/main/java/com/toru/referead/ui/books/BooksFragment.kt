package com.toru.referead.ui.books

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.toru.referead.R
import com.toru.referead.databinding.FragmentBooksListBinding
import com.toru.referead.model.books.BooksInfo
import com.toru.referead.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview

/**
 * A fragment representing a list of Items.
 */

@AndroidEntryPoint
class BooksFragment : Fragment(R.layout.fragment_books_list), BooksRecyclerViewAdapter.OnBookClickListener {

    @OptIn(FlowPreview::class)
    private val mainViewModel by viewModels<MainViewModel>()

    private var _binding: FragmentBooksListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentBooksListBinding.bind(view)

        val adapter = BooksRecyclerViewAdapter(this)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = BooksLoadStateAdapter{adapter.retry()},
                footer = BooksLoadStateAdapter{adapter.retry()}
            )
            retryBtn.setOnClickListener {
                adapter.retry()
            }
        }


        mainViewModel.books.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }


        adapter.addLoadStateListener { loadState->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                retryBtn.isVisible = loadState.source.refresh is LoadState.Error
                errorTv.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached && adapter.itemCount < 1){
                    recyclerView.isVisible = false
                    emptyTv.isVisible = true
                }
                else{
                    emptyTv.isVisible = false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onBookClick(book: BooksInfo) {
        val action = BooksFragmentDirections.actionBookFragmentToBookDetailsFragment(book)
        findNavController().navigate(action)
    }
}