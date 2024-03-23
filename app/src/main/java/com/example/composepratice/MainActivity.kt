package com.example.composepratice

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.ModifierLocalReadScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composepratice.ui.theme.ComposePraticeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackbarHostState = remember{ SnackbarHostState()}
            val scope = rememberCoroutineScope()
            val (text, setValue) = remember {
                mutableStateOf("")
            }

            Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                    floatingActionButton = {
                    FloatingActionButton(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("메시지를 입력하세요")
                        }
                    }) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                    }
                }) {
                it
                Column (modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    ImageCard()
                    TextField(value = text,
                        onValueChange = setValue)
                    Button(onClick = { }) {
                        Text(text = "클릭")
                    }
                }
            }

            Column (modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                ImageCard()
                TextField(value = text,
                    onValueChange = setValue)
                Button(onClick = { }) {
                    Text(text = "클릭")
                }
            }

        }
    }
}

@Composable
fun ImageCard(){
    var isFavorite by remember{
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .fillMaxWidth(0.5f)
        .padding(15.dp),
        shape = RoundedCornerShape(8.dp)) {
        Box (modifier = Modifier.height(200.dp)){
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "new",
                contentScale = ContentScale.Crop
            )

            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd){
                IconButton(onClick = {
                    isFavorite = !isFavorite
                }) {
                    Icon(imageVector = if(isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorite",
                        tint = Color.Red)
                }
            }
        }
    }
}