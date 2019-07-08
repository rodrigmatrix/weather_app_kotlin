package com.rodrigmatrix.protonweather.ui.weather.future.detailt

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rodrigmatrix.protonweather.R

class FutureDetailFragment : Fragment() {

    companion object {
        fun newInstance() =
            FutureDetailFragment()
    }

    private lateinit var viewModel: FutureDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.future_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FutureDetailViewModel::class.java)
    }

}
