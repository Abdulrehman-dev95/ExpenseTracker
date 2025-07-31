package com.example.expensetracker.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.toColorInt
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensetracker.R
import com.example.expensetracker.ui.theme.ExpenseTrackerTheme
import com.example.expensetracker.ui.widgets.CustomText
import com.example.expensetracker.utils.CustomMarkerView
import com.example.expensetracker.utils.Utils
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter

@Composable
fun StatsScreen(
    statsScreenViewModel: StatsScreenViewModel = viewModel(factory = ViewModelInitializer.factory)
) {
    val chartData by statsScreenViewModel.chartEntries.collectAsStateWithLifecycle()


    StatsLayout(
        chartData = chartData,
        funCall = {
            statsScreenViewModel.updateChartData(it)
        }
    )
}


@Composable
fun StatsLayout(modifier: Modifier = Modifier, chartData: List<Entry>, funCall: (String) -> Unit) {
    val expenseType = rememberSaveable {
        mutableStateOf(
            ExpenseDetails().type
        )
    }
    Scaffold(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp)
        ) {
            CustomText(
                text = "Statistics",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ExpenseDropDownMenu(
                onItemSelected = { type ->
                    expenseType.value = type
                    funCall(type)


                },
                options = listOf("Income", "Expense"),
                selectedOption = expenseType.value

            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )


            if (chartData.isNotEmpty()) {
                LineChart(entries = chartData)
            } else {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}


@Composable
fun LineChart(modifier: Modifier = Modifier, entries: List<Entry>) {
    val context = LocalContext.current

    AndroidView(
        factory = {
            LineChart(context).apply {
                description.isEnabled = false
                legend.isEnabled = true
                setDrawGridBackground(false)
                setTouchEnabled(true)
                setPinchZoom(false)
                isDoubleTapToZoomEnabled = false
                isHighlightPerTapEnabled = true


                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(false)
                    setDrawAxisLine(true)
                    axisLineColor = 0xFFAAAAAA.toInt()
                    textColor = 0xFF444444.toInt()
                    textSize = 11f
                    valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return Utils.dateFormatterForChart(value.toLong())
                        }
                    }
                }

                axisLeft.apply {
                    setDrawAxisLine(false)
                    setDrawGridLines(false)
                    setDrawLabels(false)
                    axisMinimum = 0f
                }
                axisRight.isEnabled = false
            }
        },
        update = { chart ->
            val dataSet = LineDataSet(entries, "Spending Graph").apply {
                color = Color(0xff438883).toArgb()
                lineWidth = 3f
                mode = LineDataSet.Mode.CUBIC_BEZIER
                setDrawCircles(true)
                circleRadius = 4f
                circleColors = listOf("#438883".toColorInt())
                fillDrawable = context.getDrawable(R.drawable.gradient)
                setDrawFilled(true)
                fillAlpha = 100
                highLightColor = Color.Gray.toArgb()
                setDrawHorizontalHighlightIndicator(false)
                enableDashedHighlightLine(10f, 5f, 0f) // Dashed vertical line
                setDrawValues(true)
                valueTextSize = 11f
                valueTextColor = Color.DarkGray.toArgb()
            }
            val marker = CustomMarkerView(context, R.layout.marker_view)
            marker.chartView = chart
            chart.marker = marker
            chart.data = LineData(dataSet)
            chart.invalidate()
        },
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
    )
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
            chartData = data,
            funCall = {}
        )
    }
}
