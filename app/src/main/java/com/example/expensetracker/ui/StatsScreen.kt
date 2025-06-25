package com.example.expensetracker.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensetracker.R
import com.example.expensetracker.ui.theme.ExpenseTrackerTheme
import com.example.expensetracker.ui.widgets.CustomText
import com.example.expensetracker.utils.Utils
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter

@Composable
fun StatsScreen(
    modifier: Modifier = Modifier,
    statsScreenViewModel: StatsScreenViewModel = viewModel(factory = ViewModelInitializer.factory)
) {

    val chartData = statsScreenViewModel.chartEntries.value
    StatsLayout(
        chartData = chartData,
    )


}

@Composable
fun StatsLayout(modifier: Modifier = Modifier, chartData: List<Entry>) {

    Scaffold(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {

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
}

@Composable
fun LineChart(modifier: Modifier = Modifier, entries: List<Entry>) {
    val context = LocalContext.current
    AndroidView(
        factory = {
            LineChart(context).apply {
                // General Setting
                description.isEnabled = false
                legend.isEnabled = false
                setDrawGridBackground(false)
                setTouchEnabled(false)
                setPinchZoom(false)
                isDoubleTapToZoomEnabled = false

                //X axis
                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(false)
                    setDrawAxisLine(false)
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


                // Data
                data = LineData(
                    LineDataSet(entries, "").apply {
                        color = Color(0xff438883).toArgb()
                        lineWidth = 3f
                        mode = LineDataSet.Mode.CUBIC_BEZIER
                        setDrawCircles(true)
                        setDrawCircleHole(false)
                        circleRadius = 4f
                        circleColors = listOf(
                            "#438883".toColorInt()
                        )

                        fillDrawable = context.getDrawable(
                            R.drawable.gradient
                        )
                        setDrawFilled(true)
                        fillAlpha = 100

                    }
                ).apply {
                    setValueTextSize(0f)
                }


            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        it.invalidate()
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
