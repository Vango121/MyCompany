package com.marcel.mycompany.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.marcel.mycompany.R
import com.marcel.mycompany.databinding.FinancesFragmentBinding
import javax.inject.Inject

class FinancesFragment @Inject constructor() : Fragment() {

    companion object {
        fun newInstance() = FinancesFragment()
    }


    private val viewModel: FinancesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FinancesFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.finances_fragment,container,false)
      viewModel.e()
        return binding.root
    }


}