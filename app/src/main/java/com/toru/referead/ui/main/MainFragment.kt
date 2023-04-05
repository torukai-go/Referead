package com.toru.referead.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.toru.referead.R
import com.toru.referead.di.ModuleProvider
import com.toru.referead.remote.books.NetworkServiceRetrofitCoroutinesMoshiAdapter
import com.toru.referead.usecase.RequestBooksInfoUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.ext.getFullName

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }
//    private val scope = getKoin().getOrCreateScope(
//        this::class.getFullName(),
//        named(ModuleProvider.Scopes.MAIN_SCREEN.name)
//    )

//    @OptIn(FlowPreview::class)
//    private val mainViewModel: MainViewModel by scope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.open_list).setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_bookFragment)
        }
    }

}