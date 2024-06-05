package br.com.fiap.globalsolution

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.globalsolution.adapter.BeachAdapter
import br.com.fiap.globalsolution.viewmodel.BeachViewModel

class MainActivity : ComponentActivity() {

    val viewModel: BeachViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val beachAdapter = BeachAdapter { beach ->
            viewModel.removeBeach(beach)
        }

        recyclerView.adapter = beachAdapter

        val button = findViewById<Button>(R.id.btnIncluir)
        val nameEditText = findViewById<EditText>(R.id.etNomePraia)
        val cityEditText = findViewById<EditText>(R.id.etCidade)
        val stateEditText = findViewById<EditText>(R.id.etEstado)

        button.setOnClickListener {
            val name = nameEditText.text.toString()
            val city = cityEditText.text.toString()
            val state = stateEditText.text.toString()

            if (name.isEmpty() || city.isEmpty() || state.isEmpty()) {
                if (name.isEmpty()) {
                    nameEditText.error = "Preencha o nome da praia"
                }
                if (city.isEmpty()) {
                    cityEditText.error = "Preencha a cidade"
                }
                if (state.isEmpty()) {
                    stateEditText.error = "Preencha o estado"
                }
                return@setOnClickListener
            }

            viewModel.addBeach(name, city, state)

            nameEditText.text.clear()
            cityEditText.text.clear()
            stateEditText.text.clear()
        }

        viewModel.beachesLiveData.observe(this) { beaches ->
            beachAdapter.updateBeaches(beaches)
        }
    }
}