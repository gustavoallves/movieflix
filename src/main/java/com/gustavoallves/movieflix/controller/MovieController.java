package com.gustavoallves.movieflix.controller;

import com.gustavoallves.movieflix.controller.request.MovieRequest;
import com.gustavoallves.movieflix.controller.response.MovieResponse;
import com.gustavoallves.movieflix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> saveMovie(@RequestBody MovieRequest movieRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movieService.save(movieRequest));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<MovieResponse> findMovieById(@PathVariable Long id) {
        return movieService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @RequestBody MovieRequest movieRequest) {
        return movieService.findById(id)
                .map(movieUpdate -> ResponseEntity.ok(movieService.update(id, movieRequest)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
