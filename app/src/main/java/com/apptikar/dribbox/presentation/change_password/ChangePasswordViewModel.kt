package com.apptikar.dribbox.presentation.change_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class ChangePasswordViewModel : ViewModel() {

    var currentPasswordState by mutableStateOf("")
    private set

    var newPasswordState by mutableStateOf("")
    private set

    var confirmPasswordState by mutableStateOf("")
    private set


    fun setCurrentPassword(currentPassword:String){
        currentPasswordState = currentPassword
    }

    fun setNewPassword(newPassword:String){
        newPasswordState = newPassword
    }

    fun setConfirmPassword(confirmPassword:String){
        confirmPasswordState = confirmPassword
    }
}