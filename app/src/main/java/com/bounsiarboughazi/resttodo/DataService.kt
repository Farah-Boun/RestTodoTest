package com.bounsiarboughazi.resttodo

import retrofit2.Call
import retrofit2.http.*

interface DataService {
    @GET("todos/{id}")
    fun getTodo(@Path("id") id: String): Call<Todo>

    @DELETE("todos/{id}")
    fun deleteTodo(@Path("id") id: String): Call<Todo>

    @PUT("todos/{id}")
    fun edit(@Path("id") id: String,@Body todo: Todo): Call<Todo>

    @GET("todos")
    fun getAll(): Call<List<Todo>>

    @POST("todos")
    fun addTodo(@Body todo: Todo): Call<Todo>
}