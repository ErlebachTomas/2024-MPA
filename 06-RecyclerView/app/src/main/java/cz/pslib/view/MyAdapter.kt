package cz.pslib.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cz.pslib.view.databinding.ItemLayoutBinding

class MyAdapter(private val itemList: List<Item>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    // ViewHolder udržuje odkazy na komponenty layoutu pomocí ViewBinding
    inner class MyViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    // Vytvoří nový ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflatuje layout jednotlivé položky seznamu pomocí ViewBinding
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    // Naplní data do zobrazení v RecyclerView
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Získá položku z datového seznamu podle pozice
        val item = itemList[position]
        // Nastaví text podle dat
        holder.binding.textView.text = item.name

        // Nastaví onClickListener na položku seznamu
        holder.binding.textView.setOnClickListener {
            Toast.makeText(holder.binding.root.context, "Kliknuto na ${item.name}", Toast.LENGTH_SHORT).show()
        }
    }
    // Vrací počet položek v seznamu
    override fun getItemCount(): Int {
        return itemList.size
    }
}
