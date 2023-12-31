package com.example.finext.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finext.R

class MonthlyinsightsFragment : Fragment() {

    companion object {
        fun newInstance() = MonthlyinsightsFragment()
    }

    private lateinit var viewModel: MonthlyinsightsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_monthlyinsights, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MonthlyinsightsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}