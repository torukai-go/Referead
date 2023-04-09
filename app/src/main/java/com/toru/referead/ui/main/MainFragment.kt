package com.toru.referead.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.toru.referead.R
import com.toru.referead.databinding.FragmentBooksListBinding
import com.toru.referead.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var filters : Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, filters)
        binding.filterDropdownMenu.setAdapter(arrayAdapter)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var filterString :String? = null
        filters = resources.getStringArray(R.array.Filters)

        binding.filterDropdownMenu.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id -> filterString = filters[position] }

        view.findViewById<Button>(R.id.open_list).setOnClickListener {
            val queryString = view.findViewById<TextInputEditText>(R.id.query_input_field).text.toString()
            val titleString = view.findViewById<TextInputEditText>(R.id.title_input_field).text.toString()
            val authorString = view.findViewById<TextInputEditText>(R.id.author_input_field).text.toString()
            val subjectString = view.findViewById<TextInputEditText>(R.id.subject_input_field).text.toString()
            val action = MainFragmentDirections.actionMainFragmentToBooksFragment(
                query = queryString,
                inTitle =  titleString,
                inAunthor =  authorString,
                subject = subjectString,
                filter = filterString
            )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}