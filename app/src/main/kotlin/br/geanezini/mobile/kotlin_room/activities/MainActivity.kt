package br.geanezini.mobile.kotlin_room.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import br.geanezini.mobile.kotlin_room.R
import br.geanezini.mobile.kotlin_room.database.AppDatabase
import br.geanezini.mobile.kotlin_room.model.Person
import br.geanezini.mobile.kotlin_room.utils.DatabaseInitializer
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var personList = ArrayList<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        personList.addAll(DatabaseInitializer.loadAllContacts(AppDatabase.getAppDatabase(applicationContext)))

        var personAdapter = PersonAdapter(this, personList)
        list_contacts.adapter = personAdapter
        list_contacts.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            //startActivity<DetailActivity>("person" to Person.toBundle(personList[position]))
            startActivity<DetailActivity>("id" to personList[position].getId())
        }

        btn_add_person.setOnClickListener { startActivity<DetailActivity>() }
    }

    inner class PersonAdapter : BaseAdapter {

        private var personList = ArrayList<Person>()
        private var context: Context? = null

        constructor(context: Context, personList: ArrayList<Person>) : super() {
            this.personList = personList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.person, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            vh.textPersonSurname.text = personList[position].lastName
            vh.textFirstName.text = personList[position].firstName

            return view
        }

        override fun getItem(position: Int): Any {
            return personList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return personList.size
        }
    }

    private class ViewHolder(view: View?) {
        val textFirstName: TextView = view?.findViewById(R.id.text_person_first_name) as TextView
        val textPersonSurname: TextView = view?.findViewById(R.id.text_person_surname) as TextView

    }
}