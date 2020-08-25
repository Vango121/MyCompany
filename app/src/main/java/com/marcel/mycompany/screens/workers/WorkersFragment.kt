package com.marcel.mycompany.screens.workers

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.marcel.mycompany.R
import com.marcel.mycompany.databinding.WorkersFragmentBinding
import com.marcel.mycompany.screens.workers.dialog.*
import es.dmoral.toasty.Toasty
import java.util.*


class WorkersFragment : Fragment() {

    companion object {
        fun newInstance() =
            WorkersFragment()
    }
    private lateinit var binding: WorkersFragmentBinding
    private lateinit var viewModel: WorkersViewModel
    private var workersHrs: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding =
            DataBindingUtil.inflate(inflater,
                R.layout.workers_fragment,container,false)
        val dialog = AddWorkersDialog()
        val removeWorkerDialog = RemoveWorkerDialog() // remove dialog class
        val addButtonDialog = AddButtonDialog() // dialog on add button click
        val payrollDialog = PayrollDialog() // payroll dialog class
        val paymentDialog = PaymentDialog()
            viewModel = ViewModelProvider(this).get(WorkersViewModel::class.java)
            binding.workersViewModel=viewModel


            viewModel.navigateToDialog.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let{
                    dialog.show(parentFragmentManager,"Dialog")
                }
            })
        viewModel.navigateToRemoveDialog.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                removeWorkerDialog.show(parentFragmentManager,"RemoveDialog")
            }
        })

        //switch
        viewModel.checked.observe(viewLifecycleOwner, Observer {
            switch(it)
            viewModel.saveSwitchState()
        })
        //check if dialog closed and pass data to viewmodel
        dialog.workerInfo.observe(viewLifecycleOwner, Observer {
            viewModel.getDataFromDialog(it)
        })

        //get all workers from room database
        viewModel.getAllWorkers().observe(viewLifecycleOwner, Observer {
        removeWorkerDialog.addData(it)
            addButtonDialog.addData(it)
            payrollDialog.addData(it)
            var total_amount=0.0
            for (person:Worker in it){
               total_amount+= (person.hours*person.money)-person.advance
            }
            val currrency = getString(R.string.currency)
            binding.textView2.setText("$total_amount$currrency")
        })
        removeWorkerDialog.workerToRemove.observe(viewLifecycleOwner, Observer {
            viewModel.deleteWorker(it)
            removeWorkerDialog.addData(viewModel.getAllWorkers().value!!)
        })

        viewModel.addButton.observe(viewLifecycleOwner, Observer { //add button click observer
            it.getContentIfNotHandled()?.let {

                val time1 = binding.editTextTime.text.toString()
                val time2 = binding.editTextTime2.text.toString()
                if (time1!="" && time2!=""){
                workersHrs=viewModel.calcHours(time1,time2)
                    if (workersHrs>0){
                        addButtonDialog.clearListInAdapter()
                        addButtonDialog.show(parentFragmentManager,it)
                       // binding.button2.setBackgroundResource(R.drawable.rounded_button_clicked)
                    }else{
                        context?.let { it1 -> Toasty.error(it1, getString(R.string.time),Toast.LENGTH_SHORT,true).show() } //
                    }


                }else{
                    context?.let { it1 -> Toasty.error(it1, getString(R.string.empty_text),Toast.LENGTH_SHORT,true).show() } //dodac strings
                }
            }
        })

        addButtonDialog.workertoUpdate.observe(viewLifecycleOwner, Observer {
            for (person : Worker in it){
                person.hours+=workersHrs
                viewModel.updateWorker(person)
                //binding.button2.setBackgroundResource(R.drawable.rounded_button)
            }
        })

        viewModel.payroll.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                payrollDialog.show(parentFragmentManager,it)

            }

        })
        viewModel.payment.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                paymentDialog.show(parentFragmentManager,it)
            }
        })
        return binding.root
    }
    fun switch(isChecked:Boolean){
        if(isChecked){
        binding.editTextTime.isEnabled=true
            binding.editTextTime.setOnClickListener {
                val calendar: Calendar = Calendar.getInstance()
                val currentHour: Int = calendar.get(Calendar.HOUR_OF_DAY)
                val currentMinute: Int = calendar.get(Calendar.MINUTE)
                val timePickerDialog =
                    TimePickerDialog(
                        context,R.style.DialogTheme,
                        TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minutes ->
                            binding.editTextTime.setText(String.format("%02d:%02d", hourOfDay, minutes))
                        },
                        currentHour,
                        currentMinute,
                        true
                    )
                timePickerDialog.show()
            }

        binding.editTextTime2.isEnabled=true
            binding.editTextTime2.setOnClickListener {
                val calendar: Calendar = Calendar.getInstance()
                val currentHour: Int = calendar.get(Calendar.HOUR_OF_DAY)
                val currentMinute: Int = calendar.get(Calendar.MINUTE)
                val timePickerDialog =
                    TimePickerDialog(
                        context,R.style.DialogTheme,
                        TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minutes ->
                            binding.editTextTime2.setText(String.format("%02d:%02d", hourOfDay, minutes))

                        },
                        currentHour,
                        currentMinute,
                        true
                    )
                timePickerDialog.show()
            }
        binding.button2.isEnabled=true
            //binding.button2.setOnClickListener {
               // val time1 = binding.editTextTime.text.toString()
               // val time2 = binding.editTextTime2.text.toString()
                //viewModel.calcHours(time1,time2)

            //}
        }
        else{
            binding.editTextTime.isEnabled=false
            binding.editTextTime2.isEnabled=false
            binding.button2.isEnabled=false
        }
    }

}