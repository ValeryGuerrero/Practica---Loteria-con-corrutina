package com.example.loto.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loto.viewModels.LoteriaViewModel

@Composable
fun LoteriaView(viewModel: LoteriaViewModel) {
    val lottoNumbers = viewModel.lotoNumbers.value
    val isGenerating by viewModel.isGenerating

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        if(lottoNumbers.isEmpty()) {
            Text(
                text = "Lotería Corrutinas",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
        if (isGenerating) {
            LotteryNumbers(lottoNumbers)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Generando números...",
                fontSize = 18.sp,
                color = Color.Gray
            )
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(40.dp),
                color = Color.Blue,
                strokeWidth = 3.dp
            )
        } else {
            LotteryNumbers(lottoNumbers)
        }


        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { viewModel.generateLotoNumbers() },
            enabled = !isGenerating,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = if (isGenerating) "Generando..." else "Generar",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}


@Composable
fun LotteryNumbers(lottoNumbers: List<Int>){

    LazyRow (
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ){
        items(lottoNumbers) { number ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(48.dp)
                    .background(Color.Green, CircleShape)
            ){
                Text(
                    text = number.toString(),
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }
    }
}