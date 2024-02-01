package io.gigabyte.labs.sensebot.controller;


import io.gigabyte.labs.sensebot.common.model.controller.MessageRequest;
import io.gigabyte.labs.sensebot.service.generator.GeneratorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/generator")
public class SenseBotProducerController {

    private final GeneratorService generatorService;

    public SenseBotProducerController(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }


    @PostMapping("publishes")
    public String publishMessages(@RequestBody MessageRequest messageRequest) {
        String topicName = messageRequest.topicName();
        int numberOfMessages = messageRequest.numberOfMessages();
//        generatorService.produceMessages(topicName, numberOfMessages, messageRequest.queue());
        generatorService.produceMessagesParallelStreams(topicName, numberOfMessages, messageRequest.sleep());
        return "Messages %d published to topic: %s".formatted(numberOfMessages, topicName);
    }



}
