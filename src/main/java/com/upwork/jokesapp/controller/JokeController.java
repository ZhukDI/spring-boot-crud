package com.upwork.jokesapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upwork.jokesapp.model.Joke;
import com.upwork.jokesapp.service.IJokeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("jokes")
@Api(value = "Joke Management System", description = "Operations pertaining to joke in Joke Management System")
public class JokeController {
    private final IJokeService jokeService;

    @Autowired
    public JokeController(IJokeService jokeService) {
        this.jokeService = jokeService;
    }

    @ApiOperation(value = "Find a list of jokes")
    @GetMapping("all")
    public List<Joke> getAll() {
        return jokeService.getAll();
    }

    @ApiOperation(value = "Find a random joke")
    @GetMapping("random")
    public  Joke getRandom() {
        return jokeService.getRandom();
    }

    @ApiOperation(value = "Find a joke by id")
    @GetMapping("{id}")
    public Joke getById(@PathVariable("id") Long id) {
        return jokeService.getById(id);
    }

    @ApiOperation(value = "Find a joke by category")
    @GetMapping
    public List<Joke> getByCategory(@RequestParam String category) {
        return jokeService.getByCategory(category);
    }

    @ApiOperation(value = "Find a random joke by category")
    @GetMapping("random/{category}")
    public Joke getRandomByCategory(@PathVariable("category") String category) {
        return jokeService.getRandomByCategory(category);
    }

    @ApiOperation(value = "Add a new joke")
    @PostMapping
    public Joke add(@ApiParam(value = "Joke object for saving to the database", required = true) @Valid @RequestBody Joke joke) {
        return jokeService.add(joke);
    }

    @ApiOperation(value = "Update a joke")
    @PutMapping("{id}")
    public Joke update(
            @ApiParam(value = "Joke Id to update joke object", required = true) @PathVariable(value = "id") Long id,
            @ApiParam(value = "Updated joke object", required = true) @Valid @RequestBody Joke joke) {
        return jokeService.update(id, joke);
    }


    @ApiOperation(value = "Delete a joke by id")
    @DeleteMapping("{id}")
    public Long deleteById(@PathVariable("id") Long id) {
        return jokeService.deleteById(id);
    }
}
