package com.winandronux.LiterAlura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;

public record DataGutendexResponse(
        @JsonAlias("count") int count,
        @JsonAlias("next") String next,
        @JsonAlias("previous") String previous,
        @JsonAlias("results") List<DataBook> results
        ) {}
