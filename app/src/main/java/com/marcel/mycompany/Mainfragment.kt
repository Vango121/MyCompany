package com.marcel.mycompany

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.marcel.mycompany.databinding.MainFragmentBinding

class Mainfragment : Fragment() {

    companion object {
        fun newInstance() = Mainfragment()
    }

    private lateinit var viewModel: MainfragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: MainFragmentBinding =DataBindingUtil.inflate(inflater,R.layout.main_fragment,container,false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainfragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}