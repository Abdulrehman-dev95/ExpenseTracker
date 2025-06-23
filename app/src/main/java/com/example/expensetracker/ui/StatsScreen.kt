package com.example.expensetracker.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensetracker.ui.theme.ExpenseTrackerTheme
import com.example.expensetracker.ui.widgets.CustomText
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry

@Composable
fun StatsScreen(
    modifier: Modifier = Modifier,
    statsScreenViewModel: StatsScreenViewModel = viewModel(factory = ViewModelInitializer.factory)
) {

    val chartData = statsScreenViewModel.chartEntries.value
    StatsLayout(
        modifier = Modifier.padding(top = 36.dp),
        chartData = chartData
    )


}

@Composable
fun StatsLayout(modifier: Modifier = Modifier, chartData: List<Entry>) {

    Column(modifier = Modifier.fillMaxSize()) {

        CustomText(
            text = "Statistics",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LineChart(
            entries = chartData
        )


    }
}

@Composable
fun LineChart(modifier: Modifier = Modifier, entries: List<Entry>) {
    val context = LocalContext.current
    AndroidView(
        factory = {
            LineChart(context)
        },
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        it.apply {
// General Setting
            description.isEnabled = false


        }


    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StatScreenPreview() {
    val data = listOf(
        Entry(0f, 400f), // Mar
        Entry(1f, 700f), // Apr
        Entry(2f, 900f),  // May
        Entry(3f, 1230f), // Jun (peak)
        Entry(4f, 800f),  // Jul
        Entry(5f, 600f)   // Aug
    )
    ExpenseTrackerTheme {
        StatsLayout(
            modifier = Modifier.padding(top = 36.dp),
            chartData = data
        )
    }
}
