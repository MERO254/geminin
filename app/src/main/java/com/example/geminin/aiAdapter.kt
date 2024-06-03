package com.example.geminin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class aiAdapter(var list:List<Ai>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var ANSWER = 2
    var QUESTION = 1
    class question(view: View):RecyclerView.ViewHolder(view){
        var txtquestion:TextView = view.findViewById(R.id.txtquestion)
    }

    class answer(view: View):RecyclerView.ViewHolder(view){
      var txtanswer = view.findViewById<TextView>(R.id.txtanswer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 1){
            var view = LayoutInflater.from(parent.context).inflate(R.layout.question, parent, false)
            return question(view)

        }else{
            var view = LayoutInflater.from(parent.context).inflate(R.layout.answer, parent, false)
            return answer(view)

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder.javaClass == question::class.java){
            holder as question
            holder.txtquestion.setText(list[position].result)
        }else{
            holder as answer
            holder.txtanswer.setText(list[position].result)
        }

    }

    override fun getItemViewType(position: Int): Int {
        if(list[position].id == 1){
            return QUESTION
        }else{
            return ANSWER
        }

    }
}