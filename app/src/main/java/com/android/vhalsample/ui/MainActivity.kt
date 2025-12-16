package com.android.vhalsample.ui

import android.car.Car
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.vhalsample.TAG
import com.android.vhalsample.ui.theme.VHALSampleTheme
import com.android.vhalsample.ui.views.navigation.NavigationBuilder
import com.android.vhalsample.utils.CarDataSource
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val mCarDataSource by inject<CarDataSource>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionForVehicleSpeed()
        enableEdgeToEdge()
        setContent {
            VHALSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationBuilder(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        requestPermissionForVehicleSpeed()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCarDataSource.unRegisterListeners()
    }

    fun requestPermissionForVehicleSpeed() {
        if(ContextCompat.checkSelfPermission(this, Car.PERMISSION_SPEED) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Car.PERMISSION_SPEED), 10)
        } else {
           mCarDataSource.registerListeners()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        when(requestCode) {
            10 -> {
                if(grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    mCarDataSource.registerListeners()
                } else {
                    Log.e(TAG, "User denied speed permission")
                }
            }
            else -> {}
            }
        }
    }