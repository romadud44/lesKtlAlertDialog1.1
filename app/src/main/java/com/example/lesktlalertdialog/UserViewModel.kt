package com.example.lesktlalertdialog

import android.content.Context
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    val currentUsers: MutableLiveData<MutableList<User>> by lazy { MutableLiveData<MutableList<User>>() }
}