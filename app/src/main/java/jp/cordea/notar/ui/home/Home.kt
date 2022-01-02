package jp.cordea.notar.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Home(viewModel: HomeViewModel) {
    val query by viewModel.query.observeAsState("")
    Scaffold(topBar = {
        TopAppBar(
            contentPadding = PaddingValues(horizontal = 4.dp),
            content = {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    placeholder = {
                        Text(text = "Search")
                    },
                    singleLine = true,
                    value = query,
                    onValueChange = viewModel::onSearchQueryChanged,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        cursorColor = MaterialTheme.colors.onPrimary
                    )
                )
            },
        )
    }) {
    }
}
