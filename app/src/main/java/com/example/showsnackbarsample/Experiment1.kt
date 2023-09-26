package com.example.showsnackbarsample

//import android.util.Log
//import androidx.compose.foundation.gestures.Orientation
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ExperimentalLayoutApi
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.imePadding
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.wrapContentSize
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Button
//import androidx.compose.material3.DismissValue
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Snackbar
//import androidx.compose.material3.SnackbarHost
//import androidx.compose.material3.SnackbarHostState
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.rememberDismissState
//import androidx.compose.material3.rememberSwipeableState
//import androidx.compose.material3.swipeable
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.DisposableEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.layout.onSizeChanged
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.IntOffset
//import androidx.compose.ui.unit.dp
//import kotlinx.coroutines.launch
//import kotlin.math.roundToInt
//
//
//@Preview
//@Composable
//fun MySnackbarDemo1() {
//    val snackbarHostState = remember { SnackbarHostState() }
//    val scope = rememberCoroutineScope()
//    Scaffold(snackbarHost = {
//        SwipeableSnackbarHost(
//            hostState = snackbarHostState,
//            modifier = Modifier.imePadding()
//        )
//    }, content = { innerPadding ->
//        Column(
//            Modifier
//                .verticalScroll(rememberScrollState())
//                .padding(innerPadding),
//        ) {
//
//            Text(
//                text = "Body content", modifier = Modifier
//                    .wrapContentSize()
//                    .padding(16.dp)
//            )
//
//            var text1 by remember { mutableStateOf("") }
//            TextField(value = text1,
//                onValueChange = { newText -> text1 = newText },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                label = { Text("Enter Text") })
//
//
//            var text2 by remember { mutableStateOf("") }
//            TextField(value = text2,
//                onValueChange = { newText ->
//                    text2 = newText
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                label = { Text("Enter Text") })
//
//            var text3 by remember { mutableStateOf("") }
//            TextField(value = text3,
//                onValueChange = { newText ->
//                    text3 = newText
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                label = { Text("Enter Text") })
//
//            var clickCount by remember { mutableIntStateOf(0) }
//            Button(onClick = {
//                // show snackbar as a suspend function
//                Log.d("", "PRESS BUTTON")
//                scope.launch {
//                    snackbarHostState.showSnackbar("Snackbar # ${++clickCount}")
//                }
//            }) { Text("Show snackbar") }
//        }
//    })
//}
//
//enum class SwipeDirection {
//    START,
//    IDLE,
//    END,
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SwipeableSnackbarHost(hostState: SnackbarHostState, modifier: Modifier = Modifier) {
//    if (hostState.currentSnackbarData == null) return
//    val swipeableState = rememberSwipeableState(SwipeDirection.IDLE)
//    var size by remember { mutableStateOf(Size.Zero) }
//    val width = remember(size) { if (size.width == 0f) 1f else size.width }
//
//    if (swipeableState.isAnimationRunning) {
//        DisposableEffect(swipeableState) {
//            onDispose {
//                when (swipeableState.currentValue) {
//                    SwipeDirection.START,
//                    SwipeDirection.END -> hostState.currentSnackbarData?.dismiss()
//
//                    else -> return@onDispose
//                }
//            }
//        }
//    }
//    SnackbarHost(
//        hostState,
//        modifier = modifier
//            .onSizeChanged { size = Size(it.width.toFloat(), it.height.toFloat()) }
//            .swipeable(
//                state = swipeableState,
//                anchors = mapOf(
//                    -width to SwipeDirection.START,
//                    0f to SwipeDirection.IDLE,
//                    width to SwipeDirection.END,
//                ),
//                orientation = Orientation.Horizontal
//            ),
//        snackbar = { snackbarData ->
//            Snackbar(
//                snackbarData,
//                modifier = Modifier.offset {
//                    IntOffset(
//                        swipeableState.offset.value.roundToInt(),
//                        0
//                    )
//                }
//            )
//        }
//    )
//}
