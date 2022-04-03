package com.example.kiotapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.kiotapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var _navHostController : NavController
    private lateinit var _navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        _navHostController = _navHostFragment.navController
    }

    fun refreshCurrentFragment() {
        val id = _navHostController.currentDestination?.id
        _navHostController.popBackStack(id!!, true)
        _navHostController.navigate(id)
    }

    fun onNavigate(actionId : Int, bundle : Bundle? = null){
        _navHostFragment.findNavController().navigate(actionId,bundle)
    }

    fun onBackStack(){
        if(_navHostController.currentDestination?.id != R.id.splashFragment){
            _navHostController.popBackStack()
        }
    }
}