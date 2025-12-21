package com.gustavoallves.movieflix.service;

import com.gustavoallves.movieflix.controller.request.StreamingRequest;
import com.gustavoallves.movieflix.controller.response.StreamingResponse;
import com.gustavoallves.movieflix.entity.Streaming;
import com.gustavoallves.movieflix.mapper.StreamingMapper;
import com.gustavoallves.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository repository;

    public StreamingResponse save(StreamingRequest request) {
        Streaming streaming = StreamingMapper.toStreaming(request);
        repository.save(streaming);
        return StreamingMapper.toStreamingResponse(streaming);
    }

    public Optional<StreamingResponse> findById(Long id) {
        return repository.findById(id)
                .map(StreamingMapper::toStreamingResponse);
    }

    public List<StreamingResponse> findAll() {
        List<Streaming> streamingList = repository.findAll();
            return streamingList.stream()
                    .map(StreamingMapper::toStreamingResponse)
                    .toList();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
