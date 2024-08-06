package com.example.lesktlalertdialog

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lesktlalertdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val users: MutableList<User> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.toolbarMain.title = "Список Пользователей"
        binding.toolbarMain.subtitle = "версия 1.1"
        setSupportActionBar(binding.toolbarMain)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
        binding.usersListLV.adapter = adapter

        binding.saveBTN.setOnClickListener {
            users.add(User(binding.nameET.text.toString(), binding.ageET.text.toString().toByte()))
            adapter.notifyDataSetChanged()
            binding.nameET.text.clear()
            binding.ageET.text.clear()
        }
        binding.usersListLV.onItemClickListener =
                MyDialog.createDialog(this, adapter)
//            AdapterView.OnItemClickListener { parent, v, position, id ->
//                val user = adapter.getItem(position)
//                adapter.remove(user)
//                Toast.makeText(this, "Пользователь удален: $user", Toast.LENGTH_LONG).show()
//            }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
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