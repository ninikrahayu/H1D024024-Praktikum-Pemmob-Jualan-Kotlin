package com.example.ninik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ninik.ui.screen.DaftarProdukScreen
import com.example.ninik.ui.theme.JualanTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            JualanTheme {
                DaftarProdukScreen()
            }
        }
    }
}