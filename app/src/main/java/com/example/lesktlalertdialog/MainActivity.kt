package com.example.lesktlalertdialog

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.lesktlalertdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var usersViewModel: UserViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.toolbarMain.title = "Список Пользователей"
        binding.toolbarMain.subtitle = "версия 1.2"
        setSupportActionBar(binding.toolbarMain)

        var adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, UserViewModel().usersViewList)
        binding.usersListLV.adapter = adapter

        usersViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        usersViewModel.currentUsers.observe(this) {
            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, it)
            binding.usersListLV.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        binding.saveBTN.setOnClickListener {
            addUser(adapter)
        }
        binding.usersListLV.onItemClickListener =
            MyDialog.createDialog(this, adapter, usersViewModel)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun addUser(adapter: ArrayAdapter<User>) {
        if (binding.nameET.text.isEmpty() || binding.ageET.text.isEmpty()) {
            Toast.makeText(
                this@MainActivity,
                "Заполните все поля!",
                Toast.LENGTH_LONG
            )
                .show()
            return
        }
        if (binding.ageET.text.toString().toIntOrNull() !in 1..150) {
            Toast.makeText(
                this@MainActivity,
                "Ошибка ввода возраста, повторите попытку",
                Toast.LENGTH_LONG
            )
                .show()
            return
        }
        usersViewModel.usersViewList.add(
            User(
                binding.nameET.text.toString(),
                binding.ageET.text.toString()
            )
        )
        adapter.notifyDataSetChanged()
        usersViewModel.currentUsers.value = usersViewModel.usersViewList
        binding.nameET.text.clear()
        binding.ageET.text.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}