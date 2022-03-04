package com.example.dao;

import com.example.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int peopleCount;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++peopleCount, "Sasha"));
        people.add(new Person(++peopleCount, "Vova"));
        people.add(new Person(++peopleCount, "Petya"));
        people.add(new Person(++peopleCount, "Lesha"));
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
}
