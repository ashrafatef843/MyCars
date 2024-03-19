package com.example.carly.cars.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.carly.R
import com.example.carly.base.presentation.main.ui.theme.Gray
import com.example.carly.base.presentation.main.ui.theme.LightGray
import com.example.carly.base.presentation.main.ui.theme.White
import com.example.carly.cars.destinations.CarSelectionScreenDestination
import com.example.carly.cars.destinations.CarsScreenDestination
import com.example.carly.cars.listing.presentation.model.CarView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    with(dashboardViewModel.state) {
        Column(
            Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.ic_background),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            Image(
                painterResource(R.drawable.ic_logo),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            when (selectedCar) {
                is CarStatus.EmptyDashboard -> AddCarItem(navigator)
                is CarStatus.SelectedCar -> CarDetails(selectedCar.carView, navigator)
                is CarStatus.Uninitialized -> {
                    // do nothing
                }
            }
        }
    }
}

@Composable
private fun AddCarItem(navigator: DestinationsNavigator) {
    Box(Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = {
                navigator.navigate(CarSelectionScreenDestination())
            }
        ) {
            Image(
                painterResource(R.drawable.ic_add_car),
                contentDescription = ""
            )
        }
    }
}

@Composable
private fun CarDetails(carView: CarView, navigator: DestinationsNavigator) {
    Column {
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            Modifier
                .fillMaxWidth()
        ) {

            Column(
                Modifier.padding(16.dp)
            ) {
                Text(
                    text = "${carView.brand} - ${carView.series}",
                    color = White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "${carView.buildYear} - ${carView.powerType}",
                    color = LightGray,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                modifier = Modifier
                    .size(70.dp)
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
                    .clickable {
                        navigator.navigate(CarsScreenDestination())
                    },
                painter = painterResource(R.drawable.ic_switch_car),
                contentDescription = ""
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painterResource(R.drawable.ic_car),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            shape = RoundedCornerShape(8.dp),
            colors =  CardDefaults.cardColors(
                containerColor = Gray
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            LazyColumn {
               items(carView.supportedFeatures.size) {
                   FeatureItem(carView.supportedFeatures[it])
               }
            }
        }
    }
}

@Composable
fun FeatureItem(text: String) {
    Column {
        Spacer(modifier = Modifier.height(15.dp))
        Row(
           Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = text,
                color = LightGray,
                fontSize = 14.sp

            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "",
                tint = White
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Divider(
            color = Color.DarkGray,
            modifier = Modifier
                .fillMaxWidth()
                .width(1.dp)
        )
    }
}