package br.com.fiap.globalsolution.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.globalsolution.R
import br.com.fiap.globalsolution.model.Beach

class BeachAdapter(private val onRemove: (Beach) -> Unit) : RecyclerView.Adapter<BeachAdapter.BeachViewHolder>() {

    private var beaches = listOf<Beach>()

    fun updateBeaches(newBeaches: List<Beach>) {
        beaches = newBeaches
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeachViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_praia, parent, false)
        return BeachViewHolder(view)
    }

    override fun getItemCount(): Int = beaches.size

    override fun onBindViewHolder(holder: BeachViewHolder, position: Int) {
        val beach = beaches[position]
        holder.bind(beach, onRemove)
    }

    class BeachViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = view.findViewById(R.id.tvName)
        private val cityTextView: TextView = view.findViewById(R.id.tvCity)
        private val stateTextView: TextView = view.findViewById(R.id.tvState)
        private val deleteButton: Button = view.findViewById(R.id.btnDelete)

        fun bind(beach: Beach, onRemove: (Beach) -> Unit) {
            nameTextView.text = beach.name
            cityTextView.text = beach.city
            stateTextView.text = beach.state
            deleteButton.setOnClickListener {
                onRemove(beach)
            }
        }
    }
}