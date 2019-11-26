package com.nhn.forward2019.reactivekafka.core;

import reactor.core.publisher.Flux;

import java.time.Duration;

public interface DelayedRepeatTenGenerator extends SourceFluxGenerator {
    @Override
    default Flux<Integer> generateSource() {
        return Flux.range(1, 10)
                .repeat(9)
                .delayElements(Duration.ofMillis(80));
    }
}
