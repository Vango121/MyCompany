package com.marcel.mycompany

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.marcel.mycompany.databinding.WorkersFragmentBinding
import kotlinx.android.synthetic.main.workers_fragment.*
import kotlinx.android.synthetic.main.workers_fragment.view.*

class WorkersFragment : Fragment() {

    companion object {
        fun newInstance() = WorkersFragment()
    }
    private lateinit var binding: WorkersFragmentBinding
    private lateinit var viewModel: WorkersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding =
            DataBindingUtil.inflate(inflater,R.layout.workers_fragment,container,false)
            viewModel = ViewModelProvider(this).get(WorkersViewModel::class.java)
            binding.workersViewModel=viewModel
        var dialog: AddWorkersDialog = AddWorkersDialog()
            viewModel.navigateToDetails.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let{
                    dialog.show(parentFragmentManager,"Dialog")

                }
            })

        viewModel.checked.observe(viewLifecycleOwner, Observer {
            switch(it)
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