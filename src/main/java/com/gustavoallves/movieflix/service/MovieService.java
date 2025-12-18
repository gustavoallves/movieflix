package com.gustavoallves.movieflix.service;

import com.gustavoallves.movieflix.controller.request.MovieRequest;
import com.gustavoallves.movieflix.controller.response.MovieResponse;
import com.gustavoallves.movieflix.entity.Movie;
import com.gustavoallves.movieflix.mapper.MovieMapper;
import com.gustavoallves.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;

    public MovieResponse save(MovieRequest request) {
        Movie movie = MovieMapper.toMovie(request);
        repository.save(movie);
        return MovieMapper.toMovieResponse(movie);
    }

    public Optional<MovieResponse> findById(Long id) {
        return repository.findById(id)
                .map(movie -> MovieMapper.toMovieResponse(movie));
    }

    public List<MovieResponse> findAll() {
        return repository.findAll().stream()
                .map(movie -> MovieMapper.toMovieResponse(movie))
                .toList();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
