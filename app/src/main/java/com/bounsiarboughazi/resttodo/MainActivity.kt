package com.bounsiarboughazi.resttodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var listTodo: ArrayList<Todo> = arrayListOf()
    private lateinit var adapter: ListViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListe()
        adapter = ListViewAdapter(this,listTodo)
        listview.adapter = adapter
    }

    private fun initListe(){
        val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create<DataService>(DataService::class.java)
        service.getAll().enqueue(object: Callback<List<Todo>> {
            override fun onResponse(call: Call<List<Todo>> , response: retrofit2.Response<List<Todo>>?) {
                if ((response != null) && (response.code() == 200)) {
                    val reslist:List<Todo>? = response.body()
                    if (reslist != null) {
                        listTodo.addAll(reslist)
                        listTodo.reverse()
                        adapter.notifyDataSetChanged()
                    }
                    Toast.makeText(this@MainActivity, "Succès", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<List<Todo>> , t: Throwable) {
                Toast.makeText(this@MainActivity, "Echec", Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun addTask(todo: Todo){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create<DataService>(DataService::class.java)
        service.addTodo(todo).enqueue(object: Callback<Todo> {
            override fun onResponse(call: Call<Todo> , response: retrofit2.Response<Todo>?) {
                if ((response != null) && (response.code() == 200)) {
                    listTodo.reverse()
                    response.body()?.let { listTodo.add(it) }
                    listTodo.reverse()
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this@MainActivity, "Task added", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<Todo> , t: Throwable) {
                Toast.makeText(this@MainActivity, "Error adding task", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun addOnClick(view: View) {
        if (!editText.text.isEmpty())
            addTask(Todo(null,editText.text.toString(),false))
    }
}
