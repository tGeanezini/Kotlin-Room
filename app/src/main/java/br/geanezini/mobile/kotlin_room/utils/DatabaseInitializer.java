package br.geanezini.mobile.kotlin_room.utils;

import android.support.annotation.NonNull;

import br.geanezini.mobile.kotlin_room.database.AppDatabase;
import br.geanezini.mobile.kotlin_room.model.Person;

public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    public static void insertPerson(@NonNull final AppDatabase db, Person person) {
        db.personDAO().insertPerson(person);
    }

    public static void updatePerson(@NonNull final AppDatabase db, Person person) {
        db.personDAO().updatePerson(person);
    }

    public static void removePerson(@NonNull final AppDatabase db, Person person) {
        db.personDAO().deletePerson(person);
    }

    public static Person loadPersonInfo(AppDatabase db, int idPerson)
    {
        return db.personDAO().loadPersonData(idPerson);
    }

    public static Person[] loadAllContacts(@NonNull final AppDatabase db)
    {
        return db.personDAO().loadAllPeople();
    }
}
