package com.marcel.mycompany.screens.workers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.marcel.mycompany.R
import com.marcel.mycompany.databinding.WorkersFragmentBinding

class WorkersFragment : Fragment() {

    companion object {
        fun newInstance() =
            WorkersFragment()
    }
    private lateinit var binding: WorkersFragmentBinding
    private lateinit var viewModel: WorkersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding =
            DataBindingUtil.inflate(inflater,
                R.layout.workers_fragment,container,false)
        val dialog = AddWorkersDialog()

            viewModel = ViewModelProvider(this).get(WorkersViewModel::class.java)
            binding.workersViewModel=viewModel


            viewModel.navigateToDialog.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let{
                    dialog.show(parentFragmentManager,"Dialog")
                }
            })

        //switch
        viewModel.checked.observe(viewLifecycleOwner, Observer {
            switch(it)
            viewModel.saveSwitchState()
        })
        //check if dialog closed and pass data to viewmodel
        dialog.workerInfo.observe(viewLifecycleOwner, Observer {
            Log.i("passed",it.name)
            viewModel.getDataFromDialog(it)
        })

        //get all workers from room database
        viewModel.getAllWorkers().observe(viewLifecycleOwner, Observer {

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

}