package com.marcel.mycompany.screens.workers

import android.app.TimePickerDialog
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.marcel.mycompany.R
import com.marcel.mycompany.ShowCaseModel
import com.marcel.mycompany.databinding.WorkersFragmentBinding
import com.marcel.mycompany.screens.workers.dialog.*
import es.dmoral.toasty.Toasty
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType
import smartdevelop.ir.eram.showcaseviewlib.config.Gravity
import java.util.*
import kotlin.collections.ArrayList


class WorkersFragment : Fragment() {

    private lateinit var binding: WorkersFragmentBinding
    private val viewModel: WorkersViewModel by viewModels()
    private var workersHrs: Double = 0.0
   // var listprevious: List<Worker> = ArrayList()
    var withdrawAll= false
    //lateinit var payrolls: List<Payroll>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.workers_fragment, container, false
            )
        val dialog = AddWorkersDialog()
        val removeWorkerDialog = RemoveWorkerDialog() // remove dialog class
        val addButtonDialog = AddButtonDialog() // dialog on add button click
        val payrollDialog = PayrollDialog() // payroll dialog class
        val paymentDialog = PaymentDialog()
        val editDialog = EditDialog()
        val paymentListDialog = PaymentListDialog()



            binding.workersViewModel=viewModel
            viewModel.navigateToDialog.observe(viewLifecycleOwner, {
                it.getContentIfNotHandled()?.let {
                    dialog.show(parentFragmentManager, "Dialog")
                }
            })
        viewModel.navigateToRemoveDialog.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {
                removeWorkerDialog.show(parentFragmentManager, "RemoveDialog")
            }
        })
        paymentDialog.workertoUpdate.observe(viewLifecycleOwner){
            for (person in it){
                viewModel.updateWorker(person)
            }
        }
        //switch
        viewModel.checked.observe(viewLifecycleOwner, {
            switch(it)
            viewModel.saveSwitchState()
        })
        //check if dialog closed and pass data to viewmodel
        dialog.workerInfo.observe(viewLifecycleOwner, {
            viewModel.getDataFromDialog(it)
        })
        //get previous payrolls
        viewModel.getAllPayrolls().observe(viewLifecycleOwner){
            paymentListDialog.addData(it)
//            payrolls=it
        }
        //get all workers from room database
        viewModel.getAllWorkers().observe(viewLifecycleOwner, {
            removeWorkerDialog.addData(it)
            addButtonDialog.addData(it)
            payrollDialog.addData(it)
            paymentDialog.addData(it)
            editDialog.addData(it)
            getTotalAmount(it)
        })
        removeWorkerDialog.workerToRemove.observe(viewLifecycleOwner, {
            viewModel.deleteWorker(it)
            //removeWorkerDialog.addData(viewModel.getAllWorkers().value!!)
        })

        viewModel.addButton.observe(viewLifecycleOwner, { //add button click observer
            it.getContentIfNotHandled()?.let {

                val time1 = binding.editTextTime.text.toString()
                val time2 = binding.editTextTime2.text.toString()
                if (time1 != "" && time2 != "") {
                    workersHrs = viewModel.calcHours(time1, time2)
                    if (workersHrs > 0) {
                        addButtonDialog.clearListInAdapter()
                        addButtonDialog.show(parentFragmentManager, it)
                        // binding.button2.setBackgroundResource(R.drawable.rounded_button_clicked)
                    } else {
                        context?.let { it1 ->
                            Toasty.error(
                                it1,
                                getString(R.string.time),
                                Toast.LENGTH_SHORT,
                                true
                            ).show()
                        }
                    }
                } else {
                    context?.let { it1 ->
                        Toasty.error(
                            it1,
                            getString(R.string.empty_text),
                            Toast.LENGTH_SHORT,
                            true
                        ).show()
                    }
                }
            }
        })

        addButtonDialog.workertoUpdate.observe(viewLifecycleOwner, {
            for (person: Worker in it) {
                person.hours += workersHrs
                viewModel.updateWorker(person)
                binding.editTextTime.setText("")
                binding.editTextTime2.setText("")
            }
        })

        viewModel.payroll.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {
                payrollDialog.show(parentFragmentManager, it)

            }

        })
        viewModel.payment.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {
                paymentDialog.show(parentFragmentManager, it)
            }
        })
        viewModel.navigateToEditDialog.observe(viewLifecycleOwner,{
            it.getContentIfNotHandled()?.let {
                editDialog.show(parentFragmentManager,it)
            }
        })

        paymentDialog.withdrawAll.observe(viewLifecycleOwner,{
            withdrawAll=it
            if (withdrawAll) {
                viewModel.withdrawAll()
                viewModel.getRowCount().observe(viewLifecycleOwner){
                    viewModel.withdraw()
                }
                withdrawAll=false
            }
        })



        //check if showcase was already displayed
        viewModel.showCaseState.observe(viewLifecycleOwner, {
            if (!it) {
                showCaseView()
                viewModel.saveShowCaseState()
            }
        })
        //EditDialog update worker
        editDialog.workerToEdit.observe(viewLifecycleOwner,{
            viewModel.updateWorker(it)
        })
        //Payment lists dialog
        viewModel.paymentListDialog.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let {
                paymentListDialog.show(parentFragmentManager,it)
            }
        }

        return binding.root
    }
    fun getTotalAmount(workerlist:List<Worker>){
        val total_amount = viewModel.calcTotalAmount(workerlist)
        val currrency = getString(R.string.currency)
        binding.textView2.setText("$total_amount$currrency")
    }
    fun switch(isChecked: Boolean){
        if(isChecked){
        binding.editTextTime.isEnabled=true
            binding.editTextTime.setOnClickListener {
                val calendar: Calendar = Calendar.getInstance()
                val currentHour: Int = calendar.get(Calendar.HOUR_OF_DAY)
                val currentMinute: Int = calendar.get(Calendar.MINUTE)
                val timePickerDialog =
                    TimePickerDialog(
                        context, R.style.DialogTheme,
                        { timePicker, hourOfDay, minutes ->
                            binding.editTextTime.setText(
                                String.format(
                                    "%02d:%02d",
                                    hourOfDay,
                                    minutes
                                )
                            )
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
                        context, R.style.DialogTheme,
                        { timePicker, hourOfDay, minutes ->
                            binding.editTextTime2.setText(
                                String.format(
                                    "%02d:%02d",
                                    hourOfDay,
                                    minutes
                                )
                            )

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
    private fun isViewVisible(view: View): Boolean {
        val scrollBounds = Rect()
        binding.scrollView1.getDrawingRect(scrollBounds)
        val top = view.y
        val bottom = top + view.height
        return if (scrollBounds.top < top && scrollBounds.bottom > bottom) {
            true
        } else {
            false
        }
    }
    fun showCaseView(){

    var i =0
    val list : MutableList<ShowCaseModel> = ArrayList()
    val model= ShowCaseModel(
        getString(R.string.add_worker),
        getString(R.string.add_workerDescr),
        binding.imageView2
    )
    val modelRemove= ShowCaseModel(
        getString(R.string.remove_worker),
        getString(R.string.remove_workerDescr),
        binding.imageView3
    )
    val modelEdit= ShowCaseModel(
        "Work in progress",
        "Not completed",
        binding.imageView4
    )
    val modelStartTime= ShowCaseModel(
        getString(R.string.workStartTime),
        getString(R.string.workStartTimeDescr),
        binding.editTextTime
    )
    val modelEndTime= ShowCaseModel(
        getString(R.string.workEndTime),
        getString(R.string.workEndTImeDescr),
        binding.editTextTime2
    )
    val modelAddHrs= ShowCaseModel(
        getString(R.string.buttonAddHrs),
        getString(R.string.buttonAddHrsDescr),
        binding.button2
    )
    val modelMoney= ShowCaseModel(
        getString(R.string.textViewTitleMoney),
        "",
        binding.textView2
    )
    val modelPayroll= ShowCaseModel(
        getString(R.string.check_payment_List),
        getString(R.string.buttonPayrollDescr),
        binding.buttonPayroll
    )
    val modelPayment= ShowCaseModel(
        getString(R.string.check_payment_List),
        "",
        binding.buttonPayments
    )

    list.add(model)
    list.add(modelRemove)
    list.add(modelEdit)
    list.add(modelStartTime)
    list.add(modelEndTime)
    list.add(modelAddHrs)
    list.add(modelMoney)
    list.add(modelPayroll)
    list.add(modelPayment)
    val mObservable: Subject<ShowCaseModel> = PublishSubject.create()
    mObservable.subscribe{
        if(!isViewVisible(it.view)){
            binding.scrollView1.scrollTo(0, binding.scrollView1.bottom)
        }
        GuideView.Builder(context)
            .setTitle(it.title)
            .setContentText(it.content)
            .setGravity(Gravity.auto) //optional
            .setDismissType(DismissType.anywhere) //optional - default DismissType.targetView
            .setTargetView(it.view)
            .setContentTextSize(12) //optional
            .setTitleTextSize(14) //optional
            .setGuideListener{
                i++
                if(i<list.size) mObservable.onNext(list[i])
            }
            .build()
            .show()
    }
    mObservable.onNext(list[0])
    }


}