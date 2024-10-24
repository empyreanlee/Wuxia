package com.example.wuxia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.wuxia.model.SageSource
import com.example.wuxia.ui.theme.WuxiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WuxiaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WuxiaApp(
                        modifier = Modifier.padding(innerPadding),
                        //color = MaterialTheme.colorScheme.background
                    )
                }
            }
        }
    }
}

@Composable
fun WuxiaApp(modifier: Modifier = Modifier) {
    Scaffold (
        modifier = modifier.fillMaxSize(),
        topBar = {
            WuxiaTopAppBar()
        }
    ){
        val sages = SageSource.sages
        SageList(sage = sages, contentPadding = it)
    }


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WuxiaTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        },
        modifier = modifier
    )
}
@Preview(showBackground = true)
@Composable
fun WuxiaPreview() {
    WuxiaTheme {
        WuxiaApp()
    }
}
@Preview(showBackground = true)
@Composable
fun WuxiaDarkThemePreview() {
    WuxiaTheme(darkTheme = true) {
        WuxiaApp()
    }
}