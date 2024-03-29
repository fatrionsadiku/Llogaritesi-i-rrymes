package com.example.llogaritesirryms

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.llogaritesirryms.databinding.ActivityMainBinding
import com.example.llogaritesirryms.databinding.RatatDialogBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var toggle : ActionBarDrawerToggle
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDrawerUi()

    }

    private fun initDrawerUi(){
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout,R.string.open,R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.ratatFragment -> {
                    showDialog()
                    drawerLayout.closeDrawer(Gravity.LEFT,true)
                }
                R.id.helpFragment -> {
                    navController.navigate(R.id.helpFragment)
                    drawerLayout.closeDrawer(Gravity.LEFT,true)
                }
                R.id.calculationHistoryFragment -> {
                    navController.navigate(R.id.calculationHistoryFragment)
                    drawerLayout.closeDrawer(Gravity.LEFT,true)
                }
            }
            true
        }
        toggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun showDialog(){
        val dialogBinding = RatatDialogBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        val parameters = LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogBinding.root,parameters)
        dialogBinding.closeDialog.setOnClickListener {
            dialog.dismiss()
        }
        dialogBinding.closeDialogButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

//    private fun navigateCheck(){
//        try {
//            findNavController(R.id.fragmentContainerView).navigate(R.id.action_calcFragment_to_helpFragment)
//        }catch (e : java.lang.IllegalArgumentException){
//            Toast.makeText(this,"Please login first before navigating to the help section", Toast.LENGTH_LONG).show()
//        }
//    }
}
