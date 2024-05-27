package com.duodinamika.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duodinamika.advweek4.R
import com.duodinamika.advweek4.viewmodel.MotogpViewModel

class MotogpListFragment : Fragment() {

    private lateinit var viewModel: MotogpViewModel
    private val motogpListAdapter = MotogpListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_motogp_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MotogpViewModel::class.java)
        viewModel.refresh()
        val recViewMotogp = view.findViewById<RecyclerView>(R.id.recViewMotogp)
        recViewMotogp.layoutManager = LinearLayoutManager(context)
        recViewMotogp.adapter = motogpListAdapter
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.motogpLD.observe(viewLifecycleOwner, Observer {
            motogpListAdapter.updateMotogpList(it)
        })
    }
}