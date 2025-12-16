package com.android.vhalsample.ui.views.home.vm

import androidx.lifecycle.ViewModel
import com.android.vhalsample.utils.CarDataSource

class HomeViewModel(mCarDataSource: CarDataSource) : ViewModel() {
    val mVehicleSpeed by lazy { mCarDataSource.mVehicleSpeed }
    val mVehicleGear by lazy { mCarDataSource.mVehicleGear }
}