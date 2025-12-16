package com.android.vhalsample.ui.views.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.vhalsample.data.VehicleGearData
import com.android.vhalsample.data.VehicleSpeedData
import com.android.vhalsample.ui.views.home.vm.HomeViewModel
import org.koin.compose.koinInject
import kotlin.math.roundToInt

// Main entry point for the screen
@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    mHomeViewModel: HomeViewModel = koinInject<HomeViewModel>()
) {
    val speedData by mHomeViewModel.mVehicleSpeed.collectAsStateWithLifecycle()
    val gearData by  mHomeViewModel.mVehicleGear.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpeedIndicator(speedData = speedData)

        Spacer(modifier = Modifier.height(32.dp))

        GearIndicator(gearData = gearData)
    }
}

@Composable
fun SpeedIndicator(speedData: VehicleSpeedData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SPEED",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = (speedData.speed*3.6).roundToInt().toString(),
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "km/h",
                fontSize = 24.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Composable
fun GearIndicator(gearData: VehicleGearData) {
    // A mapping from gear integer value to a displayable string
    val gearText = when (gearData.currentGear) {
        1 -> "N"
        2 -> "R"
        4 -> "P"
        8 -> "D"
        16 -> "D1"
        32 -> "D2"
        64 -> "D3"
        128 -> "D4"
        256 -> "D5"
        512 -> "D6"
        1024 -> "D7"
        2048 -> "D8"
        else -> "GEAR_UNKNOWN"
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "GEAR",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Card(
            modifier = Modifier.size(70.dp),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = gearText,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}
