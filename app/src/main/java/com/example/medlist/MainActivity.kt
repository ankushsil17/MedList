package com.example.medlist

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), MedRVAdapter.MedItemClickInterface {

        lateinit var itemsRV : RecyclerView
        lateinit var addFAB : FloatingActionButton
        lateinit var list : List<MedItems>
        lateinit var medRVAdapter : MedRVAdapter
        lateinit var medViewModel : MedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemsRV = findViewById(R.id.idRVItems)
        addFAB = findViewById(R.id.idFABAdd)
        list = ArrayList<MedItems>()
        medRVAdapter = MedRVAdapter(list,this)
        itemsRV.layoutManager = LinearLayoutManager(this)
        itemsRV.adapter = medRVAdapter
        val medRepository = MedRepository(MedDatabase(this))
        val factory = MedViewModelFactory(medRepository)
        medViewModel = ViewModelProvider(this, factory).get(MedViewModel::class.java)
        medViewModel.getAllMedItems().observe(this, Observer {
            medRVAdapter.list = it
            medRVAdapter.notifyDataSetChanged()

        })

        addFAB.setOnClickListener{
           openDialog()
        }
    }

    fun openDialog(){
          val dialog = Dialog(this)
        dialog.setContentView(R.layout.med_add_dialog)
        val cancelBtn = dialog.findViewById<Button>(R.id.idBtnCancel)
        val addBtn = dialog.findViewById<Button>(R.id.idBtnAdd)
        val itemEdt = dialog.findViewById<EditText>(R.id.idEditItemName)
        val itemPriceEdt= dialog.findViewById<EditText>(R.id.idEditItemPrice)
        val itemQuantityEdt = dialog.findViewById<EditText>(R.id.idEditItemQuantity)
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }
        addBtn.setOnClickListener{
            val itemName : String = itemEdt.text.toString()
            val itemPrice : String = itemPriceEdt.text.toString()
            val itemQuantity : String = itemQuantityEdt.text.toString()
            val qty: Int = itemQuantity.toInt()
            val pr :Int = itemPrice.toInt()
            if(itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty()){
                val items = MedItems(itemName,qty,pr)
                medViewModel.insert(items)
                Toast.makeText(applicationContext,"Item Inserted",Toast.LENGTH_SHORT).show()
                medRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                Toast.makeText(applicationContext,"Please enter all the data..",Toast.LENGTH_SHORT).show()
            }


        }
        dialog.show()
    }

    override fun onItemClick(medItems: MedItems) {
          medViewModel.delete(medItems)
          medRVAdapter.notifyDataSetChanged()
          Toast.makeText(applicationContext,"Item deleted",Toast.LENGTH_SHORT).show()
    }
}