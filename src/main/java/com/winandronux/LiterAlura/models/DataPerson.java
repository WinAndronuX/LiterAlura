package com.winandronux.LiterAlura.models;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DataPerson(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") Integer birthYear,
        @JsonAlias("death_year") Integer deathYear
) {}
