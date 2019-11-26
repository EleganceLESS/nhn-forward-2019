package com.nhn.forward2019.reactivekafka.core;

import reactor.core.publisher.Flux;

public interface SourceFluxGenerator {
    Flux<Integer> generateSource();
}
