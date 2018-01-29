package br.geanezini.mobile.kotlin_room.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.geanezini.mobile.kotlin_room.R
import br.geanezini.mobile.kotlin_room.model.Person
import kotlinx.android.synthetic.main.detail_activity.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class DetailActivity : AppCompatActivity() {

    val person = Person()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)



        this.btn_cancelar.onClick { startActivity<MainActivity>() }

        this.btn_salvar.onClick {

            person.firstName = text_first_name.text.toString()
            person.lastName = text_last_name.text.toString()
            person.age = text_age.text.toString().toInt()
            person.contactInfo.email = text_email.text.toString()
            person.contactInfo.phone = text_phone.text.toString()


        }

    }

}