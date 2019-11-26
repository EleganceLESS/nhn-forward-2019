package com.nhn.forward2019.reactivekafka.core;

import reactor.core.publisher.Flux;

public interface HundredGenerator extends SourceFluxGenerator {
    @Override
    default Flux<Integer> generateSource() {
        return Flux.range(1, 100);
    }
}
