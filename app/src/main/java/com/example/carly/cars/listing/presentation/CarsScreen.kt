package com.example.carly.cars.listing.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.carly.R
import com.example.carly.base.presentation.GradientButton
import com.example.carly.cars.listing.presentation.model.CarView
import com.example.carly.base.presentation.main.ui.theme.DarkGray
import com.example.carly.base.presentation.main.ui.theme.Gray
import com.example.carly.base.presentation.main.ui.theme.LightGray
import com.example.carly.base.presentation.main.ui.theme.Orange
import com.example.carly.base.presentation.main.ui.theme.White
import com.example.carly.base.presentation.main.ui.theme.Yellow
import com.example.carly.cars.destinations.CarSelectionScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun CarsScreen(
    carsViewModel: CarsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    with(carsViewModel.state) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray)
        ) {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Gray
                ),
                title = {
                    Text(
                        text = stringResource(R.string.title_your_cars),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigator.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.title_back),
                            tint = Color.White
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                items(cars.size) {
                    CarItem(carView = cars[it], carsViewModel)
                }
            }

            GradientButton(
                text = stringResource(R.string.title_add_car),
                gradient = Brush.horizontalGradient(listOf(Orange, Yellow)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 16.dp)
            ) {
                navigator.navigate(CarSelectionScreenDestination())
            }
        }
    }
}

@Composable
fun CarItem(carView: CarView, carsViewModel: CarsViewModel) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = DarkGray)
            .clickable {
                carsViewModel.selectCar(carView)
            }
            .then(
                if (carView.isSelected)
                    Modifier.border(
                        BorderStroke(
                            2.dp,
                            SolidColor(Orange)
                        )
                    )
                else
                    Modifier
            )
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "${carView.brand} - ${carView.series}",
                color = White,
                fontSize = 16.sp

            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "${carView.buildYear} - ${carView.powerType}",
                color = LightGray,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        if (!carView.isSelected)
            Button(
                modifier = Modifier.wrapContentSize(),
                onClick = {
                    carsViewModel.deleteCar(carView)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(16.dp),
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.title_delete),
                    tint = White
                )
            }
    }
    Spacer(modifier = Modifier.height(16.dp))
}
