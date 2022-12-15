package com.example.llogaritesirryms

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.llogaritesirryms.databinding.ActivityMainBinding
import com.example.llogaritesirryms.databinding.RatatDialogBinding

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
        binding.navView.setupWithNavController(navHostFragment.navController)
        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ratatFragment -> showDialog()
                R.id.helpFragment -> navigateCheck()
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
        dialog.setContentView(dialogBinding.root,parameters)
        dialogBinding.closeDialog.setOnClickListener {
            dialog.dismiss()
        }
        dialogBinding.closeDialogButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun navigateCheck(){
        try {
            findNavController(R.id.fragmentContainerView).navigate(R.id.action_calcFragment_to_helpFragment)
        }catch (e : java.lang.IllegalArgumentException){
            Toast.makeText(this,"Please login first before navigating to the help section", Toast.LENGTH_LONG).show()
        }
    }
}
