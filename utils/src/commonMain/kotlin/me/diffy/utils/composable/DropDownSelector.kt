package me.diffy.utils.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import me.diffy.utils.InputFieldBackgroundColor
import me.diffy.utils.InputFieldFontSize
import me.diffy.utils.InputFieldPlaceholderColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDownSelector(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    value: T?,
    onValueChange: (T) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    list: List<T>,
    label: (T) -> String,
    placeholder: String? = null,
) {
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = onExpandedChange
    ){
        CustomTextField(
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true),
            value = if (value != null) label(value) else "",
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            onValueChange = { },
            placeholder = placeholder,
            readOnly = true,
            focusRequester = null,
        )

        ExposedDropdownMenu(
            expanded = expanded,
            modifier = Modifier,
            onDismissRequest = { onExpandedChange(false) },
            shape = RoundedCornerShape(2.dp)
        ) {
            list.map {
                DropdownMenuItem(
                    text = {
                        Text(label(it))
                    },
                    onClick = {
                        onValueChange(it)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> MultiDropDownSelector(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    values: List<T>,
    onRemove: (T) -> Unit,
    onAdd: (T) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    selectedValues: List<T>,
    label: (T) -> String,
    placeholder: String? = null,
) {
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = onExpandedChange
    ){
        Surface(
            modifier = Modifier
                .heightIn( min = OutlinedTextFieldDefaults.MinHeight)
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true),
            color = InputFieldBackgroundColor,
        ) {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.CenterStart
            ) {
                if (selectedValues.isEmpty() && placeholder != null) {
                    Text(
                        text = placeholder,
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
                                onRemove(it)
                                onExpandedChange(false)
                            },
                            label = { Text(label(it))},
                        )
                    }
                }
            }
        }

        ExposedDropdownMenu(
            expanded = expanded,
            modifier = Modifier,
            onDismissRequest = { onExpandedChange(false) },
            shape = RoundedCornerShape(2.dp)
        ) {
            values.map {
                DropdownMenuItem(
                    text = {
                        Text(label(it))
                    },
                    onClick = {
                        onAdd(it)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AutoCompleteDropDownSelector(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    items: List<T>,
    onItemSelected: (T) -> Unit,
    label: (T) -> String,
    placeholder: String? = null,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = it
        }
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { newQuery ->
                onQueryChange(newQuery)
                expanded = true
            },
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryEditable, true),
            readOnly = false,
            label = { if (placeholder != null) Text(placeholder) },
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded,
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.SecondaryEditable, true)
                )
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
        ) {
            if (items.isEmpty() && query.isNotEmpty()) {
                DropdownMenuItem(
                    text = { Text("No results found for \"$query\"") },
                    enabled = false,
                    onClick = { /* No action */ }
                )
            } else {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(label(item)) },
                        onClick = {
                            onItemSelected(item)
                            onQueryChange(label(item))
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}