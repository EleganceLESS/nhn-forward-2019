package com.nhn.forward2019.reactivekafka.service;

import com.nhn.forward2019.reactivekafka.core.DelayedRepeatTenGenerator;
import com.nhn.forward2019.reactivekafka.core.KafkaManager;
import com.nhn.forward2019.reactivekafka.core.RecordProcessor;
import com.nhn.forward2019.reactivekafka.repository.SomeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.kafka.receiver.ReceiverRecord;

import java.time.Duration;
import java.util.function.Function;

@Service
public class Step2Service extends OperatorDemoService<Boolean> implements RecordProcessor, DelayedRepeatTenGenerator {

    private SomeRepository repository;

    public Step2Service(KafkaManager kafkaManager, SomeRepository repository) {
        super("step-2", kafkaManager);
        this.repository = repository;
    }

    @Override
    protected Flux<Boolean> consumer(Flux<ReceiverRecord<String, String>> consumerFlux) {
        return consumerFlux.map(this::commitAndConvertToInteger)
                .groupBy(Function.identity())
                .flatMap(this::sampling)
                .flatMap(repository::saveItem)
                .flatMap(repository::getReceivers)
                .flatMap(repository::notify)
                .flatMap(repository::saveResult);
    }

    protected Flux<Integer> sampling(GroupedFlux<Integer, Integer> groupedFlux) {
        return groupedFlux.sampleFirst(Duration.ofSeconds(5));
    }
}
