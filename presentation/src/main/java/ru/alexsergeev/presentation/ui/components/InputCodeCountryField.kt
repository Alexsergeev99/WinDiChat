package ru.alexsergeev.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.alexsergeev.domain.domain.models.CountryCodeUiModel
import ru.alexsergeev.presentation.R
import ru.alexsergeev.presentation.theme.WinDiTheme
import ru.alexsergeev.presentation.utils.DropdownMenuItemCustom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputCodeCountryField(onTextChange: (String) -> Unit = {}) {

    var expanded by remember { mutableStateOf(false) }

    val countryList = listOf(
        CountryCodeUiModel("Russia", "+7", R.drawable.flag_russia),
        CountryCodeUiModel("Kazakhstan", "+7", R.drawable.flag_kz),
        CountryCodeUiModel("Armenia", "+374", R.drawable.flag_armenia),
        CountryCodeUiModel("USA", "+1", R.drawable.flag_usa)
    )

    val selectedText = remember {
        mutableStateOf(countryList[0].code)
    }

    val selectedFlag = remember {
        mutableStateOf(countryList[0].flag)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier
            .padding(end = 8.dp)
            .background(WinDiTheme.colors.disabledComponent)
    ) {
        DropdownMenuItemCustom(modifier = Modifier
            .height(56.dp)
            .width(86.dp)
            .menuAnchor(),
            contentPadding = PaddingValues(horizontal = 6.dp, vertical = 8.dp),
            onClick = {
                expanded = true
            }) {
            Image(
                painter = painterResource(id = selectedFlag.value),
                contentDescription = "russia"
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = selectedText.value,
                style = WinDiTheme.typography.subheading1,
                color = WinDiTheme.colors.disabledComponent
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                countryList.forEach { item ->
                    DropdownMenuItemCustom(
                        modifier = Modifier
                            .height(40.dp)
                            .width(66.dp),
                        contentPadding = PaddingValues(horizontal = 6.dp, vertical = 8.dp),
                        onClick = {
                            expanded = false
                            selectedText.value = item.code
                            selectedFlag.value = item.flag
                            onTextChange(item.code)
                        },
                    )
                    {
                        Image(
                            painter = painterResource(id = item.flag),
                            contentDescription = "russia"
                        )
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = item.code,
                            style = WinDiTheme.typography.subheading1,
                            color = WinDiTheme.colors.disabledComponent
                        )
                    }
                }
            }
        }
    }
}