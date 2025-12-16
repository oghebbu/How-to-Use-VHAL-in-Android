package com.android.vhalsample.di

import android.car.Car
import android.car.hardware.property.CarPropertyManager
import com.android.vhalsample.ui.views.home.vm.HomeViewModel
import com.android.vhalsample.utils.CarDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val applicationModule = module {
    single<Car> { Car.createCar(androidContext()) }
    single<CarPropertyManager> {
        val car: Car = get()
        car.getCarManager(Car.PROPERTY_SERVICE) as CarPropertyManager
    }
    single { CarDataSource(get()) }
    singleOf(::HomeViewModel)
}