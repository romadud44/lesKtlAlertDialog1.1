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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lesktlalertdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var usersViewModel: UserViewModel
    private lateinit var binding: ActivityMainBinding
    private val users: MutableList<User> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.toolbarMain.title = "Список Пользователей"
        binding.toolbarMain.subtitle = "версия 1.2"
        setSupportActionBar(binding.toolbarMain)



        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
        binding.usersListLV.adapter = adapter

//        usersViewModel = ViewModelProvider(this@MainActivity)[UserViewModel::class.java]
//
//        usersViewModel.currentUsers.observe(this@MainActivity, Observer {
//            adapter = it.toMutableList()
//        })

        binding.saveBTN.setOnClickListener {
            addUser(adapter)
        }
        binding.usersListLV.onItemClickListener =
                MyDialog.createDialog(this, adapter)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun addUser(adapter: ArrayAdapter<User>) {
        if (binding.ageET.text.toString().toIntOrNull() !in 1..150) {
            Toast.makeText(
                this@MainActivity,
                "Ошибка ввода возраста, повторите попытку",
                Toast.LENGTH_LONG
            )
                .show()
            return
        }

        users.add(User(binding.nameET.text.toString(), binding.ageET.text.toString()))
        adapter.notifyDataSetChanged()
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