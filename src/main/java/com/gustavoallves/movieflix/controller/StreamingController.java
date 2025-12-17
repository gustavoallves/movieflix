package com.gustavoallves.movieflix.controller;

import com.gustavoallves.movieflix.controller.request.StreamingRequest;
import com.gustavoallves.movieflix.controller.response.StreamingResponse;
import com.gustavoallves.movieflix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;

    @PostMapping
    public ResponseEntity<StreamingResponse> saveStreaming(@RequestBody StreamingRequest streamingRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(streamingService.save(streamingRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> findStreamingById(@PathVariable Long id) {
        return streamingService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAllStreaming() {
        return ResponseEntity.ok(streamingService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStreaming(@PathVariable Long id) {
        streamingService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
