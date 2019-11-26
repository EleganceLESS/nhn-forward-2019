package com.nhn.forward2019.reactivekafka.service;

import com.nhn.forward2019.reactivekafka.core.KafkaManager;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.ReceiverOffset;
import reactor.kafka.receiver.ReceiverRecord;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class Step5ServiceTest {

    @Test
    public void test() {
        ReceiverOffset offset = mock(ReceiverOffset.class);
        doNothing().when(offset).acknowledge();

        ReceiverRecord<String, String> record = mock(ReceiverRecord.class);
        when(record.key()).thenReturn("1");
        when(record.value()).thenReturn("1");
        when(record.receiverOffset()).thenReturn(offset);

        KafkaManager manager = mock(KafkaManager.class);
        when(manager.producer(any())).thenReturn(Flux.empty());
        when(manager.consumer("step-5")).thenReturn(Flux.just(record));

        Step5Service service = new Step5Service(manager);
        StepVerifier.create(service.start())
                .expectNext("START")
                .verifyComplete();

        StepVerifier.create(service.stop())
                .expectNext("STOP")
                .verifyComplete();
    }
}
