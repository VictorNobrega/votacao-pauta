package br.com.softdesign.votacao.controller;

import br.com.softdesign.votacao.dto.response.MessageResponse;
import br.com.softdesign.votacao.dto.request.TopicRequest;
import br.com.softdesign.votacao.dto.response.TopicResponse;
import br.com.softdesign.votacao.dto.response.TopicResultResponse;
import br.com.softdesign.votacao.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity<MessageResponse> createTopic(@Valid @RequestBody TopicRequest topicRequest) {
        return new ResponseEntity<>(topicService.createTopic(topicRequest), HttpStatus.OK);
    }

    @RequestMapping(value = "/{topicId}", method = RequestMethod.GET)
    private ResponseEntity<TopicResultResponse> searchTopicResult(@PathVariable("topicId") Long topicId) {
        try {
            return new ResponseEntity<>(topicService.searchTopicResult(topicId), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value = "/infos", method = RequestMethod.GET)
    private ResponseEntity<List<TopicResponse>> searchInfoTopic() {
        try {
            return new ResponseEntity<>(topicService.searchInfoTopic(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
