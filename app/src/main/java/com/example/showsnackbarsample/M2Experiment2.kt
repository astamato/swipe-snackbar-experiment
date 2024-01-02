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
import androidx.compose.material.Button
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberDismissState
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

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun M2Experiment2() {
    ShowSnackbarSampleTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()

            val dismissSnackbarState = rememberDismissState(confirmStateChange = { value ->
                if (value != DismissValue.Default) {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    true
                } else {
                    false
                }
            })

            LaunchedEffect(dismissSnackbarState.currentValue) {
                if (dismissSnackbarState.currentValue != DismissValue.Default) {
                    dismissSnackbarState.reset()
                }
            }

            Scaffold(snackbarHost = {
                SwipeToDismiss(
                    state = dismissSnackbarState,
                    background = {},
                    dismissContent = {
                        SnackbarHost(
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
