package br.geanezini.mobile.kotlin_room.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Bundle;

@Entity
public class Person {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String firstName;
    public String lastName;
    public int age;

    @Embedded
    public ContactInfo contactInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public static Bundle toBundle(Person person)
    {
        if (person == null)
            return null;

        if (person.getContactInfo() == null)
            return null;

        Bundle bundle = new Bundle();
        bundle.putInt("id", person.getId());
        bundle.putString("firstName", person.getFirstName());
        bundle.putString("lastName", person.getLastName());
        bundle.putInt("age", person.getAge());
        bundle.putString("email", person.getContactInfo().getEmail());
        bundle.putString("phone", person.getContactInfo().getPhone());

        return bundle;
    }

    public static Person toPerson(Bundle bundle)
    {
        if (bundle == null)
            return null;

        Person person = new Person();
        ContactInfo contactInfo = new ContactInfo();

        person.setId(bundle.getInt("id"));
        person.setFirstName(bundle.getString("firstName"));
        person.setLastName(bundle.getString("lastName"));
        person.setAge(bundle.getInt("age"));

        contactInfo.setEmail(bundle.getString("email"));
        contactInfo.setPhone(bundle.getString("phone"));

        person.setContactInfo(contactInfo);

        return person;
    }
}
