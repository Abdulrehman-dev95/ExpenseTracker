package com.example.expensetracker.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensetracker.R
import com.example.expensetracker.ui.theme.ExpenseTrackerTheme
import com.example.expensetracker.ui.theme.interFont
import com.example.expensetracker.ui.widgets.CustomText
import com.example.expensetracker.utils.Utils
import java.util.Currency
import java.util.Locale

@Composable
fun AddExpenseScreen(
    modifier: Modifier = Modifier,
    viewModel: AddExpenseScreenViewModel = viewModel(factory = ViewModelInitializer.factory),
    navigateUp: () -> Unit,
    navigateBack: () -> Unit

) {

    Surface(modifier = modifier.fillMaxSize()) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.rectangle_9),
                contentDescription = null, modifier = Modifier.fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
                .padding(horizontal = 16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable(
                            onClick = {
                                navigateUp()
                            }
                        )


                )
                CustomText(
                    text = "Add Expense",
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentSize(Alignment.Center),
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold

                )
            }
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            AddExpenseLayout(
                expenseDetails = viewModel.addExpenseScreenState.expenseDetails,
                onValueChange = {
                    viewModel.updateExpenseDetails(it)
                },
                onDateChange = {
                    viewModel.onDateChange(it)
                }, onAddButtonClick = {
                    viewModel.onAddButtonClick()
                    navigateBack()
                },
                enabled = viewModel.isEnabled()
            )
        }
    }
}

@Composable
fun AddExpenseLayout(
    modifier: Modifier = Modifier,
    expenseDetails: ExpenseDetails,
    onValueChange: (ExpenseDetails) -> Unit,
    onDateChange: (date: Long) -> Unit,
    onAddButtonClick: () -> Unit,
    enabled: Boolean
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier

                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {

            CustomText(
                text = "Name",
                fontSize = 18.sp
            )
            Spacer(
                modifier = Modifier.padding(vertical = 4.dp)
            )
            OutlinedTextField(
                value = expenseDetails.name,
                onValueChange = { onValueChange(expenseDetails.copy(name = it)) },
                label = {
                    CustomText(text = "Enter Title/Name*")
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color(0xFF438883),
                    focusedLeadingIconColor = Color(0xFF438883),
                    focusedTrailingIconColor = Color(0xFF438883),
                    focusedBorderColor = Color(0xFF438883)
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.padding(vertical = 8.dp)
            )
            CustomText(
                text = "Amount",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            OutlinedTextField(
                value = expenseDetails.amount,
                onValueChange = { onValueChange(expenseDetails.copy(amount = it)) },
                trailingIcon = {
                    TextButton(
                        onClick = {

                        },
                    ) {
                        CustomText(
                            text = "Clear",
                            color = Color.Black
                        )
                    }
                },
                leadingIcon = {
                    CustomText(
                        Currency.getInstance(Locale.getDefault()).symbol,
                    )
                },
                label = {
                    CustomText(
                        text = "Enter Amount*",
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color(0xFF438883),
                    focusedBorderColor = Color(0xFF438883),
                    focusedLeadingIconColor = Color(0xFF438883),
                    focusedTrailingIconColor = Color(0xFF438883),
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            CustomText(
                text = "Date",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            ExpenseDatePicker(
                onDateSelected = onDateChange,
                selectedDate = Utils.dateFormater(expenseDetails.date)
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            CustomText(
                text = "Category",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            ExpenseDropDownMenu(
                onItemSelected = { onValueChange(expenseDetails.copy(category = it)) },
                options = listOf(
                    "Netflix",
                    "PrimeVideo",
                    "Youtube",
                    "GooglePay",
                    "Easypaisa",
                    "Jazzcash",
                    "Others"
                ),
                selectedOption = expenseDetails.category
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            CustomText(
                text = "Type",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            ExpenseDropDownMenu(
                onItemSelected = { onValueChange(expenseDetails.copy(type = it)) },
                options = listOf(
                    "Income", "Expense"
                ),
                selectedOption = expenseDetails.type
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            CustomText(
                text = "*Required Fields",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            AddButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                isEnabled = enabled
            ) {
                onAddButtonClick()
            }
            Spacer(modifier = Modifier.padding(vertical = 16.dp))

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseDatePicker(
    onDateSelected: (date: Long) -> Unit,
    selectedDate: String
) {
    val datePickerState = rememberDatePickerState()


    var isVisibility by remember {
        mutableStateOf(false)
    }

    if (isVisibility) {
        DatePickerDialog(
            onDismissRequest = {
                isVisibility = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDateSelected(datePickerState.selectedDateMillis!!)
                        isVisibility = false
                    }, enabled = datePickerState.selectedDateMillis != null
                ) {
                    CustomText(
                        text = "Confirm"
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { isVisibility = false }
                ) {
                    CustomText(
                        text = "Cancel"
                    )
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors()
            )
        }
    }
    OutlinedTextField(
        value = if (selectedDate.isBlank()) "Select Date*" else {
            selectedDate
        },
        onValueChange = {},
        readOnly = true,
        enabled = false,
        textStyle = LocalTextStyle.current.copy(fontFamily = interFont),
        trailingIcon = {
            Icon(
                Icons.Default.DateRange,
                contentDescription = "Date Selection",
                tint = Color(0xFF438883)
            )
        }, colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color(0xFF438883),
            focusedBorderColor = Color(0xFF438883),
            focusedLeadingIconColor = Color(0xFF438883),
            focusedTrailingIconColor = Color(0xFF438883),
            disabledTextColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    isVisibility = true
                }
            )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseDropDownMenu(
    onItemSelected: (String) -> Unit,
    selectedOption: String,
    options: List<String>
) {
    var expanded by remember {
        mutableStateOf(false)
    }


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = Modifier.fillMaxWidth()
    ) {

        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }, label = {

                CustomText(text = if (options.size == 2) "Select Type*" else "Select Category*")

            }, colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFF438883),
                focusedBorderColor = Color(0xFF438883),
                focusedTrailingIconColor = Color(0xFF438883),
            ), leadingIcon = if (options.size > 2) {
                {

                    Row {
                        Spacer(modifier = Modifier.size(16.dp))

                        Icon(
                            painter = painterResource(
                                id = when (selectedOption) {
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
                            tint = Color.Unspecified,
                            modifier = Modifier.size(36.dp)

                        )
                        Spacer(modifier = Modifier.size(8.dp))

                    }
                }
            } else {
                null
            }, modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(type = MenuAnchorType.PrimaryNotEditable)

        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }, modifier = Modifier

        ) {
            options.forEach {
                DropdownMenuItem(
                    text = {
                        CustomText(
                            text = it
                        )
                    },
                    onClick = {
                        onItemSelected(it)
                        expanded = false
                    }
                )
            }
        }
    }

}

@Composable
fun AddButton(modifier: Modifier = Modifier, isEnabled: Boolean, onAddClick: () -> Unit) {
    Button(
        onClick = onAddClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF438883)),
        enabled = isEnabled


    ) {
        CustomText(
            text = "Add Expense",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline
        )

    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddExpenseScreenPreview() {
    ExpenseTrackerTheme {
        AddExpenseScreen(
            navigateUp = {},
            navigateBack = {}
        )
    }


}