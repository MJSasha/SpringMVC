package com.example.dao;

import com.example.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int peopleCount;
    private final List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++peopleCount, "Sasha", 18, "sasha@gmail.com"));
        people.add(new Person(++peopleCount, "Vova", 19, "vova@gmail.com"));
        people.add(new Person(++peopleCount, "Petya", 28, "petya@gmail.com"));
        people.add(new Person(++peopleCount, "Lesha", 29, "lesha@gmail.com"));
    }


    public List<Person> index() {
        return people;
    }

    public Person show(int id) throws Exception {
        return people.stream().
                filter(p -> p.getId() == id).
                findAny().
                orElseThrow(() -> new Exception("Cant find"));
    }

    public void save(Person person){
        person.setId(++peopleCount);
        people.add(person);
    }

    public void update(Person person){
        try {
            Person personToBeUpdated = show(person.getId());
            personToBeUpdated.setName(person.getName());
            personToBeUpdated.setAge(person.getAge());
            personToBeUpdated.setEmail(person.getEmail());
        } catch (Exception ex) {}
    }

    public void delete(int id){
        people.removeIf(p->p.getId() == id);
    }
}
