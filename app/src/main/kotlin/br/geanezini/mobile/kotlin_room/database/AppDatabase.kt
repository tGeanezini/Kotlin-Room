package br.geanezini.mobile.kotlin_room.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.geanezini.mobile.kotlin_room.dao.PersonDAO
import br.geanezini.mobile.kotlin_room.model.Person

@Database(entities = arrayOf(Person::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDAO(): PersonDAO
}