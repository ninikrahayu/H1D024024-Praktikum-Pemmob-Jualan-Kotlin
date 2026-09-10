package com.example.ninik.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ninik.R
import com.example.ninik.ui.theme.JualanTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HubungiKamiScreen(navController: NavController) {

    // Menyimpan input email
    var emailText by remember {
        mutableStateOf("")
    }

    // Menyimpan input pesan
    var messageText by remember {
        mutableStateOf("")
    }

    // Focus & Keyboard Manager
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    // Untuk menjalankan Snackbar
    val scope = rememberCoroutineScope()

    // State Snackbar
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Hubungi Kami",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.back_icon
                            ),
                            contentDescription = "Kembali",
                            tint = Color.White
                        )
                    }
                }
            )
        },

        // Snackbar
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(28.dp))

            // Judul
            Text(
                text = "Hubungi Kami",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Input Email
            OutlinedTextField(
                value = emailText,
                onValueChange = {
                    emailText = it
                },
                placeholder = {
                    Text(
                        text = "Email Anda",
                        color = Color(0xFF757575)
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.mail_icon),
                        contentDescription = "Email",
                        tint = Color(0xFF616161),
                        modifier = Modifier.size(20.dp)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color(0xFF79747E),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input Pesan
            OutlinedTextField(
                value = messageText,
                onValueChange = {
                    messageText = it
                },
                placeholder = {
                    Text(
                        text = "Pesan",
                        color = Color(0xFF757575)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color(0xFF79747E),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Tombol Kirim
            Button(
                onClick = {

                    // Validasi email
                    if (emailText.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Email harus diisi"
                            )
                        }
                        return@Button
                    }

                    // Validasi pesan
                    if (messageText.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Pesan harus diisi"
                            )
                        }
                        return@Button
                    }

                    // Kosongkan inputan
                    emailText = ""
                    messageText = ""

                    // Sembunyikan keyboard dan hilangkan fokus
                    focusManager.clearFocus()
                    keyboardController?.hide()

                    // Tampilkan notifikasi berhasil
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Pesan berhasil terkirim"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.send_icon),
                        contentDescription = "Kirim",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    Text(
                        text = "Kirim Pesan",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HubungiKamiScreenPreview() {
    JualanTheme {
        HubungiKamiScreen(navController = rememberNavController())
    }
}