package com.naren.databinding.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.naren.databinding.R
import com.naren.databinding.adapter.ListAdapter
import com.naren.databinding.data.DataModel
import com.naren.databinding.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private var listAdapter = ListAdapter(arrayListOf<DataModel>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.fetchData()

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = listAdapter

        fetchData()

    }

    private fun fetchData() {

        viewModel.dogList.observe(this, Observer { result ->
            result?.let {
                recycler_view.visibility = View.VISIBLE
                listAdapter.updateList(it as ArrayList<DataModel>)
            }
        })

        viewModel.isLoading.observe(this, Observer { result ->
            result?.let {
                progress_bar.visibility = if(it) View.VISIBLE else View.GONE
                if (it){
                    recycler_view.visibility = View.GONE
                    tvError.visibility = View.GONE
                }
            }
        })

        viewModel.error.observe(this, Observer { result ->
            result?.let {
                tvError.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

    }

}