package com.example.expensetracker.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensetracker.R
import com.example.expensetracker.ui.widgets.CustomText
import com.example.expensetracker.utils.Utils

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel(factory = ViewModelInitializer.factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (nameRow, card, list, topBar) = createRefs()


            Box(modifier = Modifier.constrainAs(topBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

            }) {
                Image(
                    painter = painterResource(R.drawable.rectangle_9),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(nameRow) {
                        top.linkTo(parent.top, margin = 32.dp)
                        start.linkTo(parent.start, margin = 24.dp)
                        end.linkTo(parent.end)
                    }) {
                Column(
                    modifier = Modifier.weight(1f)

                ) {
                    Spacer(modifier = Modifier.padding(vertical = 16.dp))
                    CustomText(
                        text = "Good Morning", fontSize = 16.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                    CustomText(
                        text = "Welcome to Expense Tracker", fontSize = 18.sp,
                        color = Color.White, fontWeight = FontWeight.Bold,
                    )
                }
            }

            ItemCard(
                amount = uiState.value.balance,
                expense = uiState.value.expense,
                income = uiState.value.income,
                modifier = Modifier.constrainAs(card) {
                    top.linkTo(nameRow.bottom, margin = 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            TransactionList(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(list) {
                        top.linkTo(card.bottom, margin = 24.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                expenseDetails = uiState.value.expenseList


            )


        }


    }

}

@Composable
fun ItemCard(modifier: Modifier = Modifier, amount: String, expense: String, income: String) {

    Card(
        modifier = modifier
            .size(width = 380.dp, height = 200.dp),
        colors = CardDefaults.cardColors(Color(0xFF2F7E79)),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            Column {

                CustomText(
                    text = "Total Balance",
                    color = Color.White, fontSize = 18.sp
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                CustomText(
                    text = amount,
                    fontWeight = FontWeight.Bold,
                    color = Color.White, fontSize = 36.sp
                )

            }
            Image(
                painter = painterResource(R.drawable.group_19),
                contentDescription = null,
                modifier = Modifier.padding(top = 4.dp)
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CardLowerItems(amount = income, image = R.drawable.frame_5, type = "income")
            CardLowerItems(amount = expense, image = R.drawable.frame_7, type = "Expenses")
        }
    }
}


@Composable
fun CardLowerItems(
    modifier: Modifier = Modifier,
    amount: String,
    @DrawableRes image: Int,
    type: String
) {

    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(image), contentDescription = null)
            Spacer(Modifier.padding(horizontal = 2.dp))
            CustomText(text = type, color = Color.White, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.padding(vertical = 2.dp))
        CustomText(
            text = amount,
            fontWeight = FontWeight.Bold,
            color = Color.White, fontSize = 22.sp
        )

    }
}


@Composable
fun TransactionItem(
    modifier: Modifier = Modifier,
    amount: String,
    date: String,
    transaction: String,
    categories: String,
    type: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Image(
                painter = painterResource(
                    id = when (categories) {
                        "Netflix" -> R.drawable.netflix_logo
                        "Youtube" -> R.drawable.youtube_logo
                        "Easypaisa" -> R.drawable.easypaisa_logo
                        "Jazzcash" -> R.drawable.jazzcash_logo
                        "PrimeVideo" -> R.drawable.primevideo_logo
                        "GooglePay" -> R.drawable.google_pay_logo
                        else -> {
                            R.drawable.money_bag_logo
                        }
                    }


                ),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                CustomText(
                    text = transaction,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                CustomText(
                    text = date
                )
            }

        }

        CustomText(
            text = if (type == "Income") {
                "+$amount"
            } else {
                "-$amount"
            },
            fontWeight = FontWeight.Bold,
            color = if (type == "Income") {
                Color.Cyan
            } else {
                Color.Red
            }, fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

    }

}


@Composable
fun TransactionList(modifier: Modifier = Modifier, expenseDetails: List<ExpenseDetails>) {
    LazyColumn(modifier = modifier.padding(horizontal = 8.dp), contentPadding = PaddingValues(bottom = 8.dp)) {
        item {
            CustomText(
                text = "Transaction History",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp
            )
        }

        items(expenseDetails.size, key = { expenseDetails[it].id }) {
            TransactionItem(
                amount = expenseDetails[it].amount,
                date = Utils.dateFormater(expenseDetails[it].date),
                transaction = expenseDetails[it].name,
                categories = expenseDetails[it].category,
                type = expenseDetails[it].type
            )


        }


    }


}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewScreen() {
    HomeScreen()
}


