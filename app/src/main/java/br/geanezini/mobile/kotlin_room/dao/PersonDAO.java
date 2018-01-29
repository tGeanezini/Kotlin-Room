package br.geanezini.mobile.kotlin_room.dao;

import android.arch.persistence.room.*;
import br.geanezini.mobile.kotlin_room.model.Person;

@Dao
public interface PersonDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPerson(Person person);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPeople(Person... people);

    @Update
    void updatePerson(Person person);

    @Delete
    void deletePerson(Person person);

    @Delete
    void deletePeople(Person... people);

    @Query("SELECT * FROM Person")
    Person[] loadAllPeople();

    @Query("SELECT * FROM Person WHERE id == :personId")
    Person loadPersonData(int personId);
}
