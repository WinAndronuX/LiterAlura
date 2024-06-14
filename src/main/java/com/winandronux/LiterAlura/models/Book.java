package com.winandronux.LiterAlura.models;

import com.winandronux.LiterAlura.repository.PersonRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> authors;
    private String language;
    private Integer downloadCount;

    public Book() {}

    public Book(DataBook dataBook, PersonRepository personRepository) {
        this.title = dataBook.title();
        this.authors = getPersons(dataBook.authors(), personRepository);
        this.language = String.join(",", dataBook.languages());
        this.downloadCount = dataBook.downloadCount();
    }

    private List<Person> getPersons(List<DataPerson> dataPersonList, PersonRepository personRepository) {
        var persons = new ArrayList<Person>();

        for (DataPerson dataPerson : dataPersonList) {
            System.out.println(dataPerson.name());
            Optional<Person> p = personRepository.findByName(dataPerson.name());
            if (p.isEmpty()) {
                var person = new Person(dataPerson);
                personRepository.save(person);
                persons.add(person);
            } else {
                persons.add(p.get());
            }
        }

        return persons;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Person> getAuthors() {
        return authors;
    }

    public String getLanguage() {
        return language;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    @Override
    public String toString() {
        var strAuthors = authors.stream()
                .map(Person::getName)
                .toList();

        return String.format("""
                ----- LIBRO -----
                Titulo: %s
                Autor: %s
                Idioma: %s
                Numero de descargas: %d
                -----------------
                """, title, String.join(",", strAuthors), language, downloadCount);
    }
}
