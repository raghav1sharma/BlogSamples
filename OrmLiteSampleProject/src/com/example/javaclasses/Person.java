package com.example.javaclasses;

import com.j256.ormlite.field.DatabaseField;

public class Person {
	
	@DatabaseField(id = true)
    private Integer PersonId;
	
	@DatabaseField
    private String Name;
	
	@DatabaseField
    private String City;

    public Person() {
    }

    public Person(Integer PersonId) {
        this.PersonId = PersonId;
    }

    public Person(Integer PersonId, String Name, String City) {
        this.PersonId = PersonId;
        this.Name = Name;
        this.City = City;
    }

    public Integer getPersonId() {
        return PersonId;
    }

    public void setPersonId(Integer PersonId) {
        this.PersonId = PersonId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

}
