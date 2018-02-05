package br.geanezini.mobile.kotlin_room.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.geanezini.mobile.kotlin_room.R
import br.geanezini.mobile.kotlin_room.database.AppDatabase
import br.geanezini.mobile.kotlin_room.model.ContactInfo
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

        //Carregando o objeto do banco via Id recebido por intent
        if (intent.extras != null)
        {
            val id  = intent.getIntExtra("id", 0)

            person = DatabaseInitializer.loadPersonInfo(AppDatabase.getAppDatabase(applicationContext), id)

            text_first_name.setText(person.firstName)
            text_last_name.setText(person.lastName)
            text_age.setText(person.age.toString())
            text_email.setText(person.contactInfo.email)
            text_phone.setText(person.contactInfo.phone)


            //Nesse caso, se o usuário pressionar o botão salvar o método 'update' do banco
            //deve ser chamado
            btn_salvar.onClick {
                person.firstName = text_first_name.text.toString()
                person.lastName = text_last_name.text.toString()
                person.age = text_age.text.toString().toInt()
                person.contactInfo = ContactInfo()
                person.contactInfo.email = text_email.text.toString()
                person.contactInfo.phone = text_phone.text.toString()

                DatabaseInitializer.updatePerson(AppDatabase.getAppDatabase(applicationContext), person)

                startActivity<MainActivity>()
            }
        }
        else {
            btn_salvar.onClick {

                person = Person()
                person.firstName = text_first_name.text.toString()
                person.lastName = text_last_name.text.toString()
                person.age = text_age.text.toString().toInt()
                person.contactInfo = ContactInfo()
                person.contactInfo.email = text_email.text.toString()
                person.contactInfo.phone = text_phone.text.toString()

                DatabaseInitializer.insertPerson(AppDatabase.getAppDatabase(applicationContext), person)

                startActivity<MainActivity>()
            }
        }

        /*
        //Carregando dados do objeto recebido via bundle na intent
        if (intent.extras != null && intent.extras.containsKey("person"))
        {
            person = Person.toPerson(intent.getBundleExtra("person"))

            text_first_name.setText(person.firstName)
            text_last_name.setText(person.lastName)
            text_age.setText(person.age.toString())
            text_email.setText(person.contactInfo.email)
            text_phone.setText(person.contactInfo.phone)


            //Nesse caso, se o usuário pressionar o botão salvar o método 'update' do banco
            //deve ser chamado

            btn_salvar.onClick {

                person = Person()
                person.firstName = text_first_name.text.toString()
                person.lastName = text_last_name.text.toString()
                person.age = text_age.text.toString().toInt()
                person.contactInfo = ContactInfo()
                person.contactInfo.email = text_email.text.toString()
                person.contactInfo.phone = text_phone.text.toString()

                DatabaseInitializer.updatePerson(AppDatabase.getAppDatabase(applicationContext), person)

                startActivity<MainActivity>()
            }
        }
        */

        btn_cancelar.onClick { startActivity<MainActivity>() }

        btn_excluir.onClick {
            DatabaseInitializer.removePerson(AppDatabase.getAppDatabase(applicationContext), person)
            startActivity<MainActivity>()
        }
    }
}