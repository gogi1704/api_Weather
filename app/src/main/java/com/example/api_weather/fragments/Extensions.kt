package com.example.api_weather.fragments

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.isPermissionsGranted(permission:String):Boolean {
    return ContextCompat.checkSelfPermission(activity as AppCompatActivity ,
        permission) == PackageManager.PERMISSION_GRANTED
}