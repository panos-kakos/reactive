package com.task2.reactive.api;


import com.task2.reactive.model.Foo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.stream.Stream;

@RestController
public class FooRestController {


    /**
     * Endpoint that simply returns/emits one resource every second.
     */
    @GetMapping(value = "/fooResource", produces = (MediaType.TEXT_EVENT_STREAM_VALUE))
    public Flux<Foo> emitEverySecond() {

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<Foo> fooStream = Flux.fromStream(Stream.generate(() -> new Foo(1L,"panos")));

        return Flux.zip(interval, fooStream).map(Tuple2::getT2);
    }

}
