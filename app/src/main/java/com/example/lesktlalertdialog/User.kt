package com.example.lesktlalertdialog

data class User(val name: String, val age: Byte) {
    override fun toString(): String {
        return "Имя: $name, Возраст: $age"
    }
}