package com.kas.model;

import com.kas.entity.Author;

import java.util.List;

public class AuthorView {
        
    private final List<Author> authors;
    private final boolean last;

    public AuthorView(List<Author> authors, boolean last) {
        this.authors = authors;
        this.last = last;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public boolean isLast() {
        return last;
    }           
}
