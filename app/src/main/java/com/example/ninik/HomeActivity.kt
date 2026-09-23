package com.example.ninik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ninik.ui.screen.DaftarProdukScreen
import com.example.ninik.ui.screen.DetailProductScreen
import com.example.ninik.ui.screen.HubungiKamiScreen
import com.example.ninik.ui.theme.JualanTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            JualanTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "daftar_produk"
                ) {
                    // Rute 1: Daftar Produk (layar awal)
                    composable(route = "daftar_produk") {
                        DaftarProdukScreen(navController = navController)
                    }

                    // Rute 2: Detail Produk, menerima productId lewat argument
                    composable(
                        route = "detail/{productId}",
                        arguments = listOf(navArgument(name = "productId") {
                            type = NavType.IntType
                        })
                    ) { backStackEntry ->
                        val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                        DetailProductScreen(
                            productId = productId,
                            navController = navController
                        )
                    }

                    // Rute 3: Hubungi Kami
                    composable(route = "hubungi_kami") {
                        HubungiKamiScreen(navController = navController)
                    }
                }
            }
        }
    }
}


