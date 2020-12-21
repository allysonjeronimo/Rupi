package com.allysonjeronimo.rupi.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.addFragment(containerId:Int, fragment: Fragment, tag:String = ""){
    this.supportFragmentManager
        .beginTransaction()
        .add(containerId, fragment, tag)
        .commit()
}