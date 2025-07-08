package me.diffy.utils.composable

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDownSelector(
    modifier: Modifier = Modifier,
    onSelect: (T) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    expanded: Boolean,
    list: List<T>,
    label: (T) -> String,
    anchor: @Composable ExposedDropdownMenuBoxScope.() -> Unit
) {
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            onExpandedChange(it)
        },
    ){
        anchor(this)
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
                        onSelect(it)
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
    label: (T) -> String,
    anchor: @Composable ExposedDropdownMenuBoxScope.() -> Unit
) {
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = onExpandedChange
    ){
        anchor(this)
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