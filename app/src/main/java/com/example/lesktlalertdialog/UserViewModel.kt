package com.example.lesktlalertdialog


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    var usersViewList: MutableList<User> = mutableListOf()
    val currentUsers: MutableLiveData<MutableList<User>> by lazy { MutableLiveData<MutableList<User>>() }
}
