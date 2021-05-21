package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdb.User.UserEntity
import com.example.roomdb.User.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add.*
import kotlinx.android.synthetic.main.dialog_add.view.*
import kotlinx.android.synthetic.main.item_note.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this)
        viewModel.getNotes()?.observe(this, Observer {
            if(it.isNotEmpty()) {
                recyclerview.adapter = MainAdapter(it)
            }
        })

        addbtn.setOnClickListener {
            addbtnAction()
        }

    }

    private fun addbtnAction() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
        val mDialog = builder.show()
        dialogView.simpan_btn.setOnClickListener {
            val judul = ""+dialogView.judul.text
            val note = ""+dialogView.note.text
            if(judul.trim().isNotEmpty() && note.trim().isNotEmpty()) {
                mDialog.dismiss()
                viewModel.insertNote(UserEntity(0,""+judul, ""+note))
            }
        }
        dialogView.cancel_button.setOnClickListener {
            mDialog.dismiss()
        }

    }

}