package br.geanezini.mobile.kotlin_room.activities

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.geanezini.mobile.kotlin_room.R
import br.geanezini.mobile.kotlin_room.database.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Monta a base de dados
        database = Room.databaseBuilder(this, AppDatabase::class.java, "test-db").build()


        this.textView.text = "Clique no botão abaixo"

        this.button.setOnClickListener {
            this.textView.text = "Achou"
        }
    }
}