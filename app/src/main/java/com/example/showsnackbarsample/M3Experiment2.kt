package com.example.showsnackbarsample

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberSwipeToDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.showsnackbarsample.ui.theme.ShowSnackbarSampleTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun M3Experiment2() {
    ShowSnackbarSampleTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()

            val dismissSnackbarState = rememberSwipeToDismissState(confirmValueChange = { value ->
                if (value != SwipeToDismissValue.Settled) {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    true
                } else {
                    false
                }
            })

            LaunchedEffect(dismissSnackbarState.currentValue) {
                if (dismissSnackbarState.currentValue != SwipeToDismissValue.Settled) {
                    dismissSnackbarState.reset()
                }
            }

            Scaffold(snackbarHost = {
                SwipeToDismissBox(
                    state = dismissSnackbarState,
                    backgroundContent = {},
                    content = {
                        SnackbarHost(
                            // imePadding doesnt work on M3??
                            hostState = snackbarHostState, modifier = Modifier.imePadding()
                        )
                    },
                )
            }, content = { innerPadding ->
                Column(
                    Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                ) {

                    Text(
                        text = "Body content", modifier = Modifier
                            .wrapContentSize()
                            .padding(16.dp)
                    )

                    var text1 by remember { mutableStateOf("") }
                    TextField(value = text1,
                        onValueChange = { newText -> text1 = newText },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Enter Text") })


                    var text2 by remember { mutableStateOf("") }
                    TextField(value = text2, onValueChange = { newText ->
                        text2 = newText
                    }, modifier = Modifier.fillMaxWidth(), label = { Text("Enter Text") })

                    var text3 by remember { mutableStateOf("") }
                    TextField(value = text3, onValueChange = { newText ->
                        text3 = newText
                    }, modifier = Modifier.fillMaxWidth(), label = { Text("Enter Text") })

                    var clickCount by remember { mutableIntStateOf(0) }
                    Button(onClick = {
                        // show snackbar as a suspend function
                        Log.d("", "PRESS BUTTON")
                        scope.launch {
                            snackbarHostState.showSnackbar("Snackbar # ${++clickCount}")
                        }
                    }) { Text("Show snackbar") }
                }
            })
        }
    }
}
