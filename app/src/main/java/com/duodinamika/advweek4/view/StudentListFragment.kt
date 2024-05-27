package com.duodinamika.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.duodinamika.advweek4.R
import com.duodinamika.advweek4.viewmodel.DetailViewModel
import com.duodinamika.advweek4.viewmodel.ListViewModel

class StudentListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val studentListAdapter = StudentListAdapter(arrayListOf())

    var txtError:TextView ?=null
    var refreshLayout:SwipeRefreshLayout ?=null
    var progressLoad:ProgressBar ?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtError = view?.findViewById(R.id.txtError)
        refreshLayout = view?.findViewById(R.id.refreshLayout)
        progressLoad = view?.findViewById(R.id.progressLoad)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)


        viewModel.refresh()

        val recView = view.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = studentListAdapter
        observeViewModel()

        refreshLayout?.setOnRefreshListener {
//            recView?.visibility = View.GONE
//            txtError?.visibility = View.GONE
//            progressLoad?.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout!!.isRefreshing = false
        }

    }

    fun observeViewModel() {
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer {
            studentListAdapter.updateStudentList(it)
        })

        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoad)
            val recView = view?.findViewById<RecyclerView>(R.id.recView)
            if(it == true) {
                if (recView != null) {
                    recView.visibility = View.GONE
                }
                if (progressLoad != null) {
                    progressLoad.visibility = View.VISIBLE
                }
            } else {
                if (recView != null) {
                    recView.visibility = View.VISIBLE
                }
                if (progressLoad != null) {
                    progressLoad.visibility = View.GONE
                }
            }
        })

    }

    fun studentDetailObserver(){

    }
}