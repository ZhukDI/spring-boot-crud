package com.upwork.jokesapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String category;
    @NotBlank(message = "Field 'joke' is required")
    private String joke;
    @NotNull(message = "Field 'nsfw' is required")
    private Boolean nsfw;

    public Joke() {
    }

    public Joke(String category, String joke, Boolean nsfw) {
        this.category = category;
        this.joke = joke;
        this.nsfw = nsfw;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public Boolean getNsfw() {
        return nsfw;
    }

    public void setNsfw(Boolean nsfw) {
        this.nsfw = nsfw;
    }
}
