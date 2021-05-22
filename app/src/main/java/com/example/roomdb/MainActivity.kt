package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this)
        viewModel.getNotes()?.observe(this, Observer {
            recyclerview.adapter = MainAdapter(it, object : MainAdapter.OnClickListener {
                override fun onDeleteClick(note: UserEntity) {
                    deletebtnAction(note)
                }

                override fun onViewClick(note: UserEntity) {
                    updateAction(note)
                }
            })
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

    private fun deletebtnAction(note: UserEntity) {
        val builder = AlertDialog.Builder(this)
            .setMessage("Hapus ?")
        builder.setPositiveButton("ya") {_,_ ->
            viewModel.deleteNote(note)
        }
        builder.setNegativeButton("tidak") {_,_ ->
            //nothing
        }
        builder.show()
    }

    private fun updateAction(notes: UserEntity) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
        val mDialog = builder.show()
        with(dialogView) {
            judul.setText(notes.title)
            note.setText(notes.note)
            simpan_btn.text = "Update"
            simpan_btn.setOnClickListener {
                if(judul.text.trim().isNotEmpty() && note.text.trim().isNotEmpty()) {
                    mDialog.dismiss()
                    notes.note = note.text.toString()
                    notes.title = judul.text.toString()
                    viewModel.updateNote(notes)
                }
            }
            cancel_button.setOnClickListener {
                mDialog.dismiss()
            }
        }
    }

}