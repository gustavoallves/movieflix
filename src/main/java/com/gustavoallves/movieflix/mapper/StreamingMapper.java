package com.gustavoallves.movieflix.mapper;

import com.gustavoallves.movieflix.controller.request.StreamingRequest;
import com.gustavoallves.movieflix.controller.response.StreamingResponse;
import com.gustavoallves.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest request) {
        return Streaming
                .builder()
                .name(request.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming) {
        return StreamingResponse.builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }

}
