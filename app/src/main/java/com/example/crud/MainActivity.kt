package com.example.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSend = findViewById<Button>(R.id.btnSend)

        btnSend.setOnClickListener{
            val textFieldContent = getText()
            if (
                textFieldContent["nome"]?.isNotBlank() == true &&
                textFieldContent["endereco"]?.isNotBlank() == true &&
                textFieldContent["bairro"]?.isNotBlank() == true &&
                textFieldContent["cep"]?.isNotBlank() == true
            ){
                saveData(textFieldContent)
            }else{
                Toast.makeText(this, "viado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getText():HashMap<String, String>{
        val edtName = findViewById<EditText>(R.id.edtName).text.toString()
        val edtAddress = findViewById<EditText>(R.id.edtAdress).text.toString()
        val edtCity = findViewById<EditText>(R.id.edtCity).text.toString()
        val edtStreetCode = findViewById<EditText>(R.id.edtStreetCode).text.toString()

        val data = hashMapOf<String,String>(
            "nome" to edtName,
            "endereco" to edtAddress,
            "bairro" to edtCity,
            "cep" to edtStreetCode
        )
        return data
    }

    fun saveData(data:HashMap<String, String>){
        val db = Firebase.firestore

        db.collection("users").add(
            data
        )
        .addOnSuccessListener { documentReference ->
            Toast.makeText(this, "sucesso ao adicionar usuário ID: ${documentReference.id}", Toast.LENGTH_SHORT).show()
        }
        .addOnFailureListener { e ->
            Toast.makeText(this, "Erro: $e", Toast.LENGTH_SHORT).show()
        }
    }
}