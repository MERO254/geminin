package com.example.geminin

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var list:MutableList<Ai>
    lateinit var adapter: aiAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        list = mutableListOf()


        var edtquestion:TextInputEditText = findViewById(R.id.edtquestion)
        var imgsend:ImageView = findViewById(R.id.imgsend)
        var recycler:RecyclerView = findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = aiAdapter(list)
        recycler.adapter = adapter


        imgsend.setOnClickListener {
            var question = edtquestion.text.toString()
            list.add(Ai(1,"${question}"))
            adapter.notifyDataSetChanged()
            val generativeModel = GenerativeModel(
                // The Gemini 1.5 models are versatile and work with both text-only and multimodal prompts
                modelName = "gemini-1.5-flash",
                // Access your API key as a Build Configuration variable (see "Set up your API key" above)
                apiKey = "${R.string.geminin_Api}"
            )
            val prompt = "${question}"

            MainScope().launch {
                val response = generativeModel.generateContent(prompt)
                list.add(Ai(2, "${response.text.toString()}"))
                adapter.notifyDataSetChanged()
            }

        }
    }
}