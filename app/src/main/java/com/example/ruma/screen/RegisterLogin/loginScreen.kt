package com.example.ruma.screen.RegisterLogin

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ruma.ViewModel.AuthViewModel
import com.example.ruma.ui.theme.RUMATheme
import com.example.ruma.ui.theme.RedWine

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun loginScreen (onRegister: () -> Unit,
                 onLoginSuccess: () -> Unit,
                 viewModel: AuthViewModel = viewModel ()
) {
    val state by viewModel.state.collectAsState()
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF2ECDC)
    ){
        Column (
            Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(Modifier.height(36.dp))
            if (state.error?.contains("salah") == true) {
                Text(
                    text = "Email atau Password salah !!!",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(12.dp))
            }

            Text("Login Here", style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
            ),
                color = Color(0xFF7E2625))
            Spacer(Modifier.height(6.dp))
            Text("Selamat Datang Kembali!", style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            ),
                color = Color(0xFF868859))
            Spacer(Modifier.height(28.dp))
            OutlinedTextField(
                value = state.email, onValueChange = viewModel::updateEmail, singleLine = true,
                label = { Text("Email") }, modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = state.password, onValueChange = viewModel::updatePassword, singleLine = true,
                label = { Text("Password") }, visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(36.dp))
            Button(
                onClick = { viewModel.login(onLoginSuccess) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(8.dp, shape = MaterialTheme.shapes.large),
                colors = ButtonDefaults.buttonColors(containerColor = RedWine)
            ) { Text("Masuk") }
            Spacer(Modifier.height(12.dp))
            OutlinedButton(
                onClick = onRegister,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) { Text("Buat Akun Baru") }
        }
    }
    }



    @Preview(showBackground = true)
    @Composable
    fun login() {
        RUMATheme {
         loginScreen(
             onRegister = {},
             onLoginSuccess = {}
         )
        }
    }

