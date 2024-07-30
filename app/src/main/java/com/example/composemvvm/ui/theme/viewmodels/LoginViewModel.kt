package com.example.composemvvm.ui.theme.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemvvm.ui.theme.repository.LoginRepository
import com.example.composemvvm.ui.theme.room.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val repository: LoginRepository) : ViewModel() {


    private val _userStateFlow = MutableSharedFlow<User?>()

    val userStateFlow: SharedFlow<User?> = _userStateFlow

    suspend fun authenticate(email: String, password: String) {

        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.authenticate(
                email, password
            )
            _userStateFlow.emit(result)
        }

    }
}