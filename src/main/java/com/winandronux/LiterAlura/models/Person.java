package com.winandronux.LiterAlura.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Person() {}

    public Person(DataPerson dataPerson) {
        this.name = dataPerson.name();
        this.birthYear = dataPerson.birthYear();
        this.deathYear = dataPerson.deathYear();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        var strBooks = books
                .stream()
                .map(Book::getTitle)
                .toList();

        return String.format("""
                ----- Autor -----
                Name: %s
                Nació en: %d
                Falleció en: %d
                Libros: %s
                -----------------
                """, name, birthYear, deathYear, strBooks);
    }
}
