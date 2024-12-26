package gaur.himanshu.splashapi

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
import gaur.himanshu.splashapi.ui.theme.SplashAPITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val isFirstTime = savedInstanceState == null
        if(isFirstTime){
            val splashScreen = installSplashScreen()
            splashScreen.setOnExitAnimationListener{splashScreenViewProvider->
                splashScreenViewProvider.iconView.animate()
                    .alpha(0f)
                    .setDuration(6000)
                    .withEndAction {
                        splashScreenViewProvider.remove()
                        setContent {
                            SplashAPITheme {
                                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                                    Greeting(
                                        name = "Android",
                                        modifier = Modifier.padding(innerPadding)
                                    )
                                }
                            }
                        }
                    }.start()
            }
            splashScreen.setKeepOnScreenCondition{
                false
            }
        }else{
            setContent {
                SplashAPITheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
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
    SplashAPITheme {
        Greeting("Android")
    }
}