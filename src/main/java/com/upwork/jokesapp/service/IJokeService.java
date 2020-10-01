package com.upwork.jokesapp.service;

import java.util.List;

import com.upwork.jokesapp.model.Joke;

public interface IJokeService {
    List<Joke> getAll();

    Joke getRandom();

    Joke getById(Long id);

    List<Joke> getByCategory(String category);

    Joke getRandomByCategory(String category);

    Joke add(Joke joke);

    Joke update(Long id, Joke joke);

    Long deleteById(Long id);
}
