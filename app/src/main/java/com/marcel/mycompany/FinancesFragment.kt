package com.marcel.mycompany

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.marcel.mycompany.databinding.FinancesFragmentBinding
import com.marcel.mycompany.databinding.MainFragmentBinding

class FinancesFragment : Fragment() {

    companion object {
        fun newInstance() = FinancesFragment()
    }


    private val viewModel: FinancesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FinancesFragmentBinding =
            DataBindingUtil.inflate(inflater,R.layout.finances_fragment,container,false)
      viewModel.e()
        return binding.root
    }


}