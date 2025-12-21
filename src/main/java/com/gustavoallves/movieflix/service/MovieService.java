package com.gustavoallves.movieflix.service;

import com.gustavoallves.movieflix.controller.request.MovieRequest;
import com.gustavoallves.movieflix.controller.response.MovieResponse;
import com.gustavoallves.movieflix.entity.Category;
import com.gustavoallves.movieflix.entity.Movie;
import com.gustavoallves.movieflix.entity.Streaming;
import com.gustavoallves.movieflix.mapper.MovieMapper;
import com.gustavoallves.movieflix.repository.CategoryRepository;
import com.gustavoallves.movieflix.repository.MovieRepository;
import com.gustavoallves.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final StreamingRepository streamingRepository;

    public MovieResponse save(MovieRequest request) {
        Movie movie = MovieMapper.toMovie(request);

        List<Category> categories = findCategories(movie.getCategories());
        movie.setCategories(categories);

        List<Streaming> streamings = findStreamings(movie.getStreamings());
        movie.setStreamings(streamings);

        movieRepository.save(movie);
        return MovieMapper.toMovieResponse(movie);
    }

    public Optional<MovieResponse> findById(Long id) {
        return movieRepository.findById(id)
                .map(MovieMapper::toMovieResponse);
    }

    public List<MovieResponse> findAll() {
        return movieRepository.findAll().stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Category> findCategories(List<Category> categories) {
        return categories.stream()
                .map(category -> categoryRepository.findById(category.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public List<Streaming> findStreamings(List<Streaming> streamings) {
        return streamings.stream()
                .map(streaming -> streamingRepository.findById(streaming.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

}