package br.geanezini.mobile.kotlin_room.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.geanezini.mobile.kotlin_room.R
import br.geanezini.mobile.kotlin_room.database.AppDatabase
import br.geanezini.mobile.kotlin_room.model.Person
import br.geanezini.mobile.kotlin_room.utils.DatabaseInitializer
import kotlinx.android.synthetic.main.detail_activity.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class DetailActivity : AppCompatActivity() {

    private var person = Person()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        person = intent.extras.get("person") as Person

        btn_cancelar.onClick { startActivity<MainActivity>() }

        btn_excluir.onClick {
            DatabaseInitializer.removePerson(AppDatabase.getAppDatabase(applicationContext), person)
        }

        btn_salvar.onClick {

            person = Person()
            person.firstName = text_first_name.text.toString()
            person.lastName = text_last_name.text.toString()
            person.age = text_age.text.toString().toInt()
            person.contactInfo.email = text_email.text.toString()
            person.contactInfo.phone = text_phone.text.toString()

            DatabaseInitializer.insertPerson(AppDatabase.getAppDatabase(applicationContext), person)

            startActivity<MainActivity>()
        }

    }

}