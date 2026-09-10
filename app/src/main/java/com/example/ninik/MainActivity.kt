package com.example.ninik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ninik.ui.screen.BasicInfoScreen
import com.example.ninik.ui.screen.HubungiKamiScreen
import com.example.ninik.ui.theme.JualanTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            JualanTheme {

                Surface(
                    modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "basic_info"
                    ) {

                        composable("basic_info") {
                            BasicInfoScreen(
                                onNavigateToContact = {
                                    navController.navigate("form_screen")
                                }
                            )
                        }

                        composable("form_screen") {
                            HubungiKamiScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}