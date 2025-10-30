package com.example.ruma.ViewModel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val confirm: String = "",
    val error: String? = null,
    val loading: Boolean = false,
    val registerSuccess: Boolean = false
)

class AuthViewModel : ViewModel() {
    private val _state = MutableStateFlow(AuthUiState())
    val state = _state.asStateFlow()

    fun updateEmail(v: String)   { _state.value = _state.value.copy(email = v, error = null) }
    fun updatePassword(v: String){ _state.value = _state.value.copy(password = v, error = null) }
    fun updateConfirm(v: String) { _state.value = _state.value.copy(confirm = v, error = null) }

    fun login(onSuccess: () -> Unit) {
        val s = _state.value
        if (s.email.isBlank() || s.password.isBlank()) {
            _state.value = s.copy(error = "Masukan Email atau Password !!!"); return
        }
        // DUMMY CREDENTIALS (ganti ke Firebase nanti)
        if (s.email.equals("user@ruma.id", true) && s.password == "123456") {
            onSuccess()
        } else {
            _state.value = s.copy(error = "Email atau Password salah !!!")
        }
    }

    fun register() {
        val s = _state.value
        if (s.email.isBlank() || s.password.isBlank()) {
            _state.value = s.copy(error = "Masukan Email atau Password !!!", registerSuccess = false); return
        }
        if (s.password != s.confirm) {
            _state.value = s.copy(error = "Konfirmasi Password tidak sama", registerSuccess = false); return
        }
        // Anggap sukses (nanti simpan ke backend/Firebase)
        _state.value = s.copy(error = null, registerSuccess = true)
    }

    fun dismissRegisterSuccess() {
        _state.value = _state.value.copy(registerSuccess = false)
    }
}