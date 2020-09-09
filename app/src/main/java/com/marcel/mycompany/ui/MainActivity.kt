package com.marcel.mycompany.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.marcel.mycompany.R
import com.marcel.mycompany.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    /** fragments var to check which fragment is displayed**/
    var home: Boolean = true
    var finances = false
    var workers = false

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var workersFragment : WorkersFragment

    @Inject
    lateinit var mainfragment : Mainfragment

    @Inject
    lateinit var financesFragment : FinancesFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
         val view = binding.root
        setContentView(view)
        if(savedInstanceState==null){
            changeFragment(workersFragment)
        }

        

//        binding.bottomNavigation.setOnNavigationItemSelectedListener { item: MenuItem ->
//            when(item.itemId){
//                R.id.home -> {
//                    if(!home){
//                    changeFragment(mainfragment)
//                        finances = false
//                        home = true
//                        workers=false
//                    }
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.finances ->{
//
//                    if(!finances) {
//                        changeFragment(financesFragment)
//                    }
//                    finances=true
//                    home=false
//                    workers=false
//                    return@setOnNavigationItemSelectedListener true
//                }
//
//                R.id.workers ->{
//
//                    if(!workers) {
//                        changeFragment(workersFragment)
//                    }
//                    finances=false
//                    home=false
//                    workers=true
//                    return@setOnNavigationItemSelectedListener true
//                }
//
//                else -> return@setOnNavigationItemSelectedListener true
//            }
//        }

    }

    override fun onBackPressed() {
       val index= supportFragmentManager.backStackEntryCount-2
        if(index>=0) {
            val tag = supportFragmentManager.getBackStackEntryAt(index).name
            when (tag) {
                Mainfragment().javaClass.name -> {
                    home = true
                    finances = false
                    workers = false
                    binding.bottomNavigation.selectedItemId = R.id.home
                }
                WorkersFragment().javaClass.name -> {
                    home = false
                    finances = false
                    workers = true
                    binding.bottomNavigation.selectedItemId = R.id.workers
                }
                FinancesFragment().javaClass.name -> {
                    home = false
                    finances = true
                    workers = false
                    binding.bottomNavigation.selectedItemId = R.id.finances
                }
            }
        } else{
            finish()
        }
        super.onBackPressed()
    }
    fun changeFragment( className:Fragment){
//        supportFragmentManager.commit {
//            setCustomAnimations(
//                R.anim.slide_in,
//                R.anim.fade_out,
//                R.anim.fade_in,
//                R.anim.slide_out
//            )
//            replace(R.id.host_fragment, className,className.javaClass.name)
//            addToBackStack(className.javaClass.name)
//        }
        val fragmentpop : Boolean = supportFragmentManager.popBackStackImmediate(className.javaClass.name,0)
        if(!fragmentpop){
            supportFragmentManager.commit {
                setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                replace(R.id.host_fragment, className,className.javaClass.name)
                addToBackStack(className.javaClass.name)
            }
        }
    }
}