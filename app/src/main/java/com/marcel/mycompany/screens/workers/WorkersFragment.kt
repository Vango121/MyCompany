package com.marcel.mycompany.screens.workers

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.marcel.mycompany.R
import com.marcel.mycompany.databinding.WorkersFragmentBinding
import com.marcel.mycompany.screens.ViewModelFactory

class WorkersFragment : Fragment() {

    companion object {
        fun newInstance() =
            WorkersFragment()
    }
    private lateinit var binding: WorkersFragmentBinding
    private lateinit var viewModel: WorkersViewModel
    private lateinit var viewModelFactory : ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding =
            DataBindingUtil.inflate(inflater,
                R.layout.workers_fragment,container,false)
        val activity = requireNotNull(this.activity)
        val dialog = AddWorkersDialog()
        viewModelFactory = ViewModelFactory(
            activity.application,
            dialog
        )
            viewModel = ViewModelProvider(this,viewModelFactory).get(WorkersViewModel::class.java)
            binding.workersViewModel=viewModel


            viewModel.navigateToDetails.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let{
                    dialog.show(parentFragmentManager,"Dialog")
                }
            })

        viewModel.checked.observe(viewLifecycleOwner, Observer {
            switch(it)
            viewModel.saveSwitchState()
        })

        return binding.root
    }
    fun switch(isChecked:Boolean){
        if(isChecked){
        binding.editTextTime.isEnabled=true
        binding.editTextTime2.isEnabled=true
        binding.button2.isEnabled=true
        }
        else{
            binding.editTextTime.isEnabled=false
            binding.editTextTime2.isEnabled=false
            binding.button2.isEnabled=false
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        // TODO: Use the ViewModel
    }

}