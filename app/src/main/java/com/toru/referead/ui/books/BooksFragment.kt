package com.toru.referead.ui.books

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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

    private val args by navArgs<BooksFragmentArgs>()

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

        val query = args.query
        val inTitle = if (args.inTitle.isNullOrEmpty()) null else args.inTitle
        val inAuthor = if (args.inAunthor.isNullOrEmpty()) null else args.inAunthor
        val subject = if (args.subject.isNullOrEmpty()) null else args.subject
        val filter = if (args.filter.isNullOrEmpty()) null else args.filter

        mainViewModel.searchBooks(query = query, inTitle = inTitle, inAuthor = inAuthor, subject = subject, filter = filter)

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

    override fun onBookClick(book: BooksInfo) {
        val action = BooksFragmentDirections.actionBookFragmentToBookDetailsFragment(book)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}