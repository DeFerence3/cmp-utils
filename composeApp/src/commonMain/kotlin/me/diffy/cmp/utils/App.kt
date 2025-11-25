package me.diffy.cmp.utils

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import me.diffy.utils.InputFieldFontSize
import me.diffy.utils.InputFieldPlaceholderColor
import me.diffy.utils.composable.DropDownSelector
import me.diffy.utils.composable.MultiDropDownSelector
import me.diffy.utils.toast.ToastDurationType
import me.diffy.utils.toast.ToastManager
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val toastManager by remember { mutableStateOf(ToastManager()) }
        var selected by remember { mutableStateOf("") }
        FlowRow(
            modifier = Modifier
                .statusBarsPadding()
                .height(IntrinsicSize.Max)
                .padding(2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            ComponentShowCase(
                name = "Toast",
                component = {
                    OutlinedButton(onClick = {
                        toastManager.showToast("Showing toast...", ToastDurationType.SHORT)
                    }){
                        Text("Show Toast")
                    }
                }
            )
            ComponentShowCase(
                name = "MultiDropDownSelector",
                component = {
                    var expandedTwo by remember { mutableStateOf(false) }
                    val selectedValues = remember { mutableListOf<String>() }
                    val values = listOf("One","Two","Three","Four","Five","Six")
                    MultiDropDownSelector(
                        modifier = Modifier
                            .width(OutlinedTextFieldDefaults.MinWidth),
                        label = { it },
                        onExpandedChange = { expandedTwo = it},
                        expanded = expandedTwo,
                        values = values,
                        onClickItem = { selectedValues.add(it) },
                        anchor = {
                            Box(
                                modifier = Modifier
                                    .border(OutlinedTextFieldDefaults.FocusedBorderThickness,OutlinedTextFieldDefaults.colors().focusedIndicatorColor , shape = OutlinedTextFieldDefaults.shape)
                                    .heightIn( min = OutlinedTextFieldDefaults.MinHeight)
                                    .padding(10.dp)
                                    .menuAnchor(MenuAnchorType.PrimaryNotEditable, true),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (selectedValues.isEmpty()) {
                                    Text(
                                        text = "Numbers",
                                        style = TextStyle(
                                            color = InputFieldPlaceholderColor,
                                            fontSize = InputFieldFontSize
                                        )
                                    )
                                }

                                FlowRow(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ){
                                    selectedValues.forEach {
                                        AssistChip(
                                            modifier = Modifier,
                                            onClick = {
                                                selectedValues.remove(it)
                                                expandedTwo = false
                                            },
                                            label = { Text(it)},
                                        )
                                    }
                                }
                            }
                        }
                    )
                }
            )
            ComponentShowCase(
                name = "DropDownSelector",
                component = {
                    var expandedTwo by remember { mutableStateOf(false) }
                    DropDownSelector(
                        modifier = Modifier.width(OutlinedTextFieldDefaults.MinWidth),
                        onSelect = { selected = it },
                        list = List(6){ "Item $it" },
                        label = { it },
                        onExpandedChange = { expandedTwo = it},
                        expanded = expandedTwo,
                        anchor = {
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor(MenuAnchorType.PrimaryNotEditable, true),
                                value = selected,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTwo)
                                },
                                onValueChange = { },
                                readOnly = true,
                            )
                        }
                    )
                }
            )
        }
    }
}