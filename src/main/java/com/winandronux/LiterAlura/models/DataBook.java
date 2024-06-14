package com.winandronux.LiterAlura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(
        @JsonAlias("id") Long id,
        @JsonAlias("title") String title,
        @JsonAlias("subjects") List<String> subjects,
        @JsonAlias("authors") List<DataPerson> authors,
        @JsonAlias("translators") List<DataPerson> translators,
        @JsonAlias("bookshelves") List<String> bookshelves,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("copyright") Boolean copyright,
        @JsonAlias("download_count") Integer downloadCount
        ) {}
