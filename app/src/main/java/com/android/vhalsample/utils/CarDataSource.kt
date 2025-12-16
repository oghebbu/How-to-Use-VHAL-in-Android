package com.android.vhalsample.utils

import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import android.util.Log
import com.android.vhalsample.TAG
import com.android.vhalsample.data.VehicleGearData
import com.android.vhalsample.data.VehicleSpeedData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CarDataSource(private val mCarPropertyManager: CarPropertyManager) {
    private val _mVehicleSpeed: MutableStateFlow<VehicleSpeedData> = MutableStateFlow(
        VehicleSpeedData(0f)
    )
    val mVehicleSpeed: StateFlow<VehicleSpeedData> = _mVehicleSpeed.asStateFlow()

    private val _mVehicleGear: MutableStateFlow<VehicleGearData> = MutableStateFlow(
        VehicleGearData(0)
    )
    val mVehicleGear: StateFlow<VehicleGearData> = _mVehicleGear.asStateFlow()

    private val mCarPropertyEventCallback = object : CarPropertyManager.CarPropertyEventCallback {
        override fun onChangeEvent(p0: CarPropertyValue<*>?) {
            CoroutineScope(Dispatchers.IO).launch {
                p0?.let {
                    when (it.propertyId) {
                        VehiclePropertyIds.PERF_VEHICLE_SPEED -> {
                            _mVehicleSpeed.emit(VehicleSpeedData(it.value as Float))
                            Log.d(TAG, "Current VehicleSpeed ${mVehicleSpeed.value}")
                        }

                        VehiclePropertyIds.GEAR_SELECTION -> {
                            _mVehicleGear.emit(VehicleGearData(it.value as Int))
                            Log.d(TAG, "Current VehicleGear ${mVehicleGear.value}")
                        }

                        else -> {
                            Log.e(TAG, "onChangeEvent: ${it.propertyId}")
                        }
                    }
                }
            }
        }

        override fun onErrorEvent(p0: Int, p1: Int) {
            Log.e(TAG, "onErrorEvent: $p0, $p1")
        }
    }

    fun registerListeners() {
        /**
         * In case of error with the deprecated API, use
         *
         *  mCarPropertyManager.subscribePropertyEvents(
         *             VehiclePropertyIds.PERF_VEHICLE_SPEED,
         *             mCarPropertyEventCallback
         *         )
         */
        mCarPropertyManager.registerCallback(
            mCarPropertyEventCallback,
            VehiclePropertyIds.PERF_VEHICLE_SPEED,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )

        /**
         * In case of error with the deprecated API, use
         *
         *  mCarPropertyManager.subscribePropertyEvents(
         *             VehiclePropertyIds.GEAR_SELECTION,
         *             mCarPropertyEventCallback
         *         )
         */
        mCarPropertyManager.registerCallback(
            mCarPropertyEventCallback,
            VehiclePropertyIds.GEAR_SELECTION,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

    fun unRegisterListeners() {
        /**
         * Incase of error with the deprecated API, use
         *
         *  mCarPropertyManager.unsubscribePropertyEvents(
         *             VehiclePropertyIds.PERF_VEHICLE_SPEED,
         *             mCarPropertyEventCallback
         *         )
         */
        mCarPropertyManager.unregisterCallback(
            mCarPropertyEventCallback,
            VehiclePropertyIds.PERF_VEHICLE_SPEED,
        )
        /**
         * Incase of error with the deprecated API, use
         *
         *  mCarPropertyManager.unsubscribePropertyEvents(
         *             VehiclePropertyIds.GEAR_SELECTION,
         *             mCarPropertyEventCallback
         *         )
         */
        mCarPropertyManager.unregisterCallback(
            mCarPropertyEventCallback,
            VehiclePropertyIds.GEAR_SELECTION,
        )
    }
}