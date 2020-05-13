package com.bounsiarboughazi.resttodo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView

class ListViewAdapter(var context : Context?, var tasks: ArrayList<Todo>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(context)
        val rowView = layoutInflater.inflate(R.layout.todo_listitem, parent, false)

        val current = tasks[position]
        var numTV = rowView.findViewById<TextView>(R.id.task_num)
        var contentTV = rowView.findViewById<TextView>(R.id.task_text)
        var doneCB = rowView.findViewById<CheckBox>(R.id.done_checkBox)

        numTV.text = current.id.toString()
        contentTV.text = current.title
        doneCB.isChecked = current.completed

        return rowView
    }

    override fun getItem(position: Int): Any {
        return tasks[position]
    }

    override fun getItemId(position: Int): Long {
        return  position.toLong()
    }

    override fun getCount(): Int {
        return  tasks.count()
    }

}