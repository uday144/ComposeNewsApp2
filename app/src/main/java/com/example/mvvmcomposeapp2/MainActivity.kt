package com.example.mvvmcomposeapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mvvmcomposeapp2.presentation.Page
import com.example.mvvmcomposeapp2.presentation.onboarding.OnBoardingPage
import com.example.mvvmcomposeapp2.ui.theme.MVVMComposeApp2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            MVVMComposeApp2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OnBoardingPage(
                        page = Page(
                            title = "Lorem Ipsum is simply dummy",
                            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                            image = R.drawable.onboarding1
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MVVMComposeApp2Theme {
        Greeting("Android")
    }
}