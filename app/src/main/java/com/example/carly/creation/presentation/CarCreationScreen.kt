package com.example.carly.creation.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.carly.main.ui.theme.DarkGray
import com.example.carly.main.ui.theme.Gray
import com.example.carly.main.ui.theme.LightGray
import com.example.carly.main.ui.theme.Orange
import com.example.carly.main.ui.theme.White
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun CarSelectionScreen(
    carCreationViewModel: CarCreationViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    with(carCreationViewModel.state) {

        BackPress(carCreationViewModel, navigator)

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
                        text = "Car Selection",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        carCreationViewModel.onBackPress()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            FilterTextField(carCreationViewModel)

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier.padding(16.dp),
                text = buildString {
                    if (selectedBrand.isNotBlank())
                        append(selectedBrand)
                    if (selectedSeries.isNotBlank())
                        append(", $selectedSeries")
                    if (selectedYear.isNotBlank())
                        append(", $selectedYear")
                    if (selectedPowerType.isNotBlank())
                        append(", $selectedPowerType")
                },
                color = Orange,
                fontSize = 16.sp
            )

            LazyColumn(Modifier.padding(16.dp)) {
                items(filteredSelectionItems.size) {
                    SelectionItem(text = filteredSelectionItems[it]) {
                        carCreationViewModel.onItemSelected(filteredSelectionItems[it], it)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterTextField(carCreationViewModel: CarCreationViewModel) {
    val state = carCreationViewModel.state
    var text by remember { mutableStateOf("") }
    text = state.filteredText
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        value = text,
        placeholder = {
            Text(
                when (state.creationStep) {
                    is CarCreationStep.BrandStep -> "Search for brand"
                    is CarCreationStep.SeriesStep -> "Search for series"
                    is CarCreationStep.YearStep -> "Search for builder year"
                    is CarCreationStep.PowerStep -> "Search for power type"
                    else -> ""
                },
                color = White
            )
        },
        onValueChange = {
            text = it
            carCreationViewModel.searchFor(text)
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search",
                tint = White
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = DarkGray,
            focusedTextColor = White
        )
    )
}

@Composable
fun SelectionItem(text: String, onClick: () -> Unit) {
    Column {
        Divider(
            color = Color.DarkGray,
            modifier = Modifier
                .fillMaxWidth()
                .width(1.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.clickable(onClick = onClick),
        ) {
            Text(
                text = text,
                color = LightGray,
                fontSize = 14.sp

            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "search",
                tint = White
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun BackPress(
    carCreationViewModel: CarCreationViewModel,
    navigator: DestinationsNavigator
) {
    val isCloseScreen = carCreationViewModel.state.creationStep is CarCreationStep.CloseScreen

    if (isCloseScreen)
        navigator.popBackStack()

    BackHandler(true) {
        carCreationViewModel.onBackPress()
    }
}


