package com.marcel.mycompany


import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.marcel.mycompany.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    /** fragments var to check which fragment is displayed**/
    var home: Boolean = true
    var finances = false
    var workers = false
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
         val view = binding.root
        setContentView(view)
        changeFragment(Mainfragment())
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            when(item.itemId){
                R.id.home -> {
                    if(!home){
                    changeFragment(Mainfragment())
                        finances = false
                        home = true
                        workers=false
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.finances ->{

                    if(!finances) {
                    changeFragment(FinancesFragment())
                    }
                    finances=true
                    home=false
                    workers=false
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.workers ->{

                    if(!workers) {
                        changeFragment(WorkersFragment())
                    }
                    finances=false
                    home=false
                    workers=true
                    return@setOnNavigationItemSelectedListener true
                }

                else -> return@setOnNavigationItemSelectedListener true
            }
        }

    }

    override fun onBackPressed() {
       val index= supportFragmentManager.backStackEntryCount-2
        val tag=supportFragmentManager.getBackStackEntryAt(index).name
        when(tag){
            Mainfragment().javaClass.name -> {
                home =true
                finances=false
                workers=false
                binding.bottomNavigation.selectedItemId=R.id.home
            }
            WorkersFragment().javaClass.name -> {
                home =false
                finances=false
                workers=true
                binding.bottomNavigation.selectedItemId=R.id.workers
            }
            FinancesFragment().javaClass.name -> {
                home = false
                finances=true
                workers=false
                binding.bottomNavigation.selectedItemId=R.id.finances
            }
        }
        super.onBackPressed()
    }

    fun changeFragment( className:Fragment){
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            replace(R.id.host_fragment, className)
            addToBackStack(className.javaClass.name)
        }
    }
}