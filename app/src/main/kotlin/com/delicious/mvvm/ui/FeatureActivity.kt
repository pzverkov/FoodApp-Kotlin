package com.delicious.mvvm.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.delicious.mvvm.R
import com.delicious.mvvm.data.model.FeatureModel
import com.delicious.mvvm.utils.Constants
import com.delicious.mvvm.utils.toast
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_features.*
import javax.inject.Inject

class FeatureActivity : AppCompatActivity() {

    @Inject
    lateinit var featureViewModelFactory: FeatureViewModelFactory
    private var featureAdapter = FeatureAdapter(ArrayList())
    private lateinit var featureViewModel: FeatureViewModel
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_features)
        AndroidInjection.inject(this)

        initializeRecycler()

        featureViewModel = ViewModelProviders.of(this, featureViewModelFactory).get(
            FeatureViewModel::class.java
        )

        progressBar.visibility = View.VISIBLE
        loadData()

        featureViewModel.featuresResult().observe(this,
            Observer<List<FeatureModel>> {
                if (it != null) {
                    val position = featureAdapter.itemCount
                    featureAdapter.addFeatures(it)
                    recycler.adapter = featureAdapter
                    recycler.scrollToPosition(position - Constants.LIST_SCROLLING)
                }
            })

        featureViewModel.featuresError().observe(this, Observer<String> {
            if (it != null) {
                toast(resources.getString(R.string.feature_error_message) + it)

            }
        })

        featureViewModel.featuresLoader().observe(this, Observer<Boolean> {
            if (it == false) progressBar.visibility = View.GONE
        })

        fab.setOnClickListener { view ->
            val list = featureAdapter.getFeatures()
            for (i in 0 until list.size) {
                list[i].ordered = false
            }
            featureAdapter.notifyDataSetChanged()
            Toast.makeText(this, "You basket has been cleaned ((", Toast.LENGTH_SHORT).show()
//      Observable
//        .just(featureAdapter.getFeatures())
//        .forEach(System.out::println)
        }
    }

    private fun initializeRecycler() {
        val gridLayoutManager = GridLayoutManager(this, 1)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
        }
    }

    private fun loadData() {
        featureViewModel.loadFeatures(Constants.LIMIT, currentPage * Constants.OFFSET)
        currentPage++
    }

    override fun onDestroy() {
        featureViewModel.disposeElements()
        super.onDestroy()
    }
}