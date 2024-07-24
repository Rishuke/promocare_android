package com.esgi.promocare_android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.esgi.promocare_android.R


class SearchBarFragment : Fragment() {

    private lateinit var searchBar : SearchView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_bar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.searchBar = view.findViewById(R.id.fragment_search_bar)

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                (activity as SearchBarHandler).textChange(newText!!)
                return true
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SearchBarFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}

interface SearchBarHandler{
    fun textChange(newText:String)
}