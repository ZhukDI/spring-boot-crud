package com.upwork.jokesapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upwork.jokesapp.model.Joke;

public interface IJokeRepository extends JpaRepository<Joke, Long> {

    Optional<List<Joke>> findByCategory(String category);

}
