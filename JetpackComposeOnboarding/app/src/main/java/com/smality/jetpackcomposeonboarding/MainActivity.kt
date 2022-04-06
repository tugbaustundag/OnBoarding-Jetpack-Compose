package com.smality.jetpackcomposeonboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.smality.jetpackcomposeonboarding.onBoarding.OnBoarding
import com.smality.jetpackcomposeonboarding.ui.theme.JetpackComposeOnboardingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeOnboardingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    OnBoarding()
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeOnboardingTheme {
        OnBoarding()
    }
}