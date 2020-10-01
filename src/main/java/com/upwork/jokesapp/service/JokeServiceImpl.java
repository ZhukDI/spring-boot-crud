package com.upwork.jokesapp.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.upwork.jokesapp.exception.ResourceNotFoundException;
import com.upwork.jokesapp.model.Joke;
import com.upwork.jokesapp.repository.IJokeRepository;

@Service
public class JokeServiceImpl implements IJokeService {
    private final IJokeRepository jokeRepository;

    @Autowired
    public JokeServiceImpl(IJokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @Override
    public List<Joke> getAll() {
        return jokeRepository.findAll();
    }

    @Override
    public Joke getRandom() {
        Long count = jokeRepository.count();
        int idx = (int)(Math.random() * count); // random number in the range [0, count-1]
        Page<Joke> jokePage = jokeRepository.findAll(PageRequest.of(idx, 1));
        Joke joke = null;
        if (jokePage.hasContent()) {
            joke = jokePage.getContent().get(0);
        }
        return joke;
    }

    @Override
    public Joke getById(Long id) {
        return jokeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Joke.class, "id", id.toString()));
    }

    @Override
    public List<Joke> getByCategory(String category) {
        return jokeRepository.findByCategory(category)
                .orElseThrow(() -> new ResourceNotFoundException(Joke.class, "category", category));
    }

    @Override
    public Joke getRandomByCategory(String category) {
        List<Joke> jokes = jokeRepository.findByCategory(category)
                .orElseThrow(() -> new ResourceNotFoundException(Joke.class, "category", category));
        return jokes.get((new Random().nextInt(jokes.size())));
    }

    @Override
    public Joke add(Joke joke) {
        return jokeRepository.save(joke);
    }

    @Override
    public Joke update(Long id, Joke joke) {
        Joke jokeFromDb = jokeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Joke.class, "id", id.toString()));
        BeanUtils.copyProperties(joke, jokeFromDb, "id");
        return jokeRepository.save(jokeFromDb);
    }

    @Override
    public Long deleteById(Long id) {
        jokeRepository.delete(jokeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Joke.class, "id", id.toString())));
        return id;
    }
}
