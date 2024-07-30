package com.example.composemvvm.ui.theme.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemvvm.ui.theme.repository.RegisterRepository
import com.example.composemvvm.ui.theme.room.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

@HiltViewModel
class RegisterViewModel @Inject constructor(val repository: RegisterRepository) : ViewModel() {

    private val TAG = "RegisterViewModel"

    private val _userStateFlow = MutableSharedFlow<String?>()

    val userStateFlow: SharedFlow<String?> = _userStateFlow

    suspend fun storeUser(user: User) {


        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.isUserAlreadyExist(
                user.email
            )
            if (result == null) {
                repository.storeUser(user)
                _userStateFlow.emit("Success")
            } else {
                _userStateFlow.emit("Error")
            }


        }

    }
}