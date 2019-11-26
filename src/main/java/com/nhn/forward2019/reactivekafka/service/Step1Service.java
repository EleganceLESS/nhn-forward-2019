package com.nhn.forward2019.reactivekafka.service;

import com.nhn.forward2019.reactivekafka.core.DelayedRepeatTenGenerator;
import com.nhn.forward2019.reactivekafka.core.HundredGenerator;
import com.nhn.forward2019.reactivekafka.core.KafkaManager;
import com.nhn.forward2019.reactivekafka.core.RecordProcessor;
import com.nhn.forward2019.reactivekafka.repository.SomeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.ReceiverRecord;

@Service
public class Step1Service extends OperatorDemoService<Boolean> implements RecordProcessor, DelayedRepeatTenGenerator {

    private SomeRepository repository;

    public Step1Service(KafkaManager kafkaManager, SomeRepository repository) {
        super("step-1", kafkaManager);
        this.repository = repository;
    }

    @Override
    protected Flux<Boolean> consumer(Flux<ReceiverRecord<String, String>> consumerFlux) {
        return consumerFlux.map(this::commitAndConvertToInteger)
                .flatMap(repository::saveItem)
                .flatMap(repository::getReceivers)
                .flatMap(repository::notify)
                .flatMap(repository::saveResult);
    }
}
