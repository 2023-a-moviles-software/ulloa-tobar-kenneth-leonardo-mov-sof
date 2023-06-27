package com.example.movilessoftware2023a

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result -> {
                if(result.resultCode == Activity.RESULT_OK) {
                    if(result.data != null) {
                        val data = result.data
                        "${data?.getStringExtra("nombreModificado")}"
                    }
                }
            }
        }

    val callbackIntentPickUri =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            {
                result -> {
                    if (result.resultCode == RESULT_OK) {
                        if(result.data != null) {
                            if(result.data!!.data != null) {
                                var uri = result.data!!.data!!
                                val cursor = contentResolver.query(uri, null, null, null, null, null)
                                cursor?.moveToFirst()
                                val indiceTelefono = cursor?.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER
                                )

                                val telefono = cursor?.getString(indiceTelefono!!)
                                cursor?.close()
                                "Telefono $telefono"
                            }
                        }
                    }
                }
            }
        )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida.setOnClickListener {
            irActividad(MainActivity2::class.java)
        }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView.setOnClickListener {
            irActividad(BListView::class.java)
        }

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito.setOnClickListener {
            val intentRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
        }

        val botonExplicito = findViewById<Button>(R.id.btn_ir_intent_explicito)
        botonExplicito.setOnClickListener {
            abrirActividadConParametros(CIntentExplicitoParametros::class.java)
        }


    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("nombre", "Adrian")
        intentExplicito.putExtra("apellido","Eguez")
        intentExplicito.putExtra("edad", 30)
        callbackContenidoIntentExplicito.launch(intentExplicito)
    }

}