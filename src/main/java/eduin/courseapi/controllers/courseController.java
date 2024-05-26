package eduin.courseapi.controllers;

import java.util.List;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import eduin.courseapi.Models.ErrorResponse;
import eduin.courseapi.Models.Topics;
import eduin.courseapi.Repositories.TopicRepo;
import eduin.courseapi.Services.TopicService;
import eduin.courseapi.TopicDTO.TopicDTO;

@RestController
public class courseController {

    @Autowired
    TopicRepo topicsrepo;

    @Autowired(required = true)
    TopicService topicService;

    @RequestMapping("/topics")
    public List<Topics> getAllTopics() {
        return topicService.getAllTopics();
    }

    @RequestMapping("/topics/{id}")
    ResponseEntity<?> getTopic(@PathVariable int id) {
        return topicService.getTopic(id);
    }

    @PostMapping("/topic")
    ResponseEntity<?> addTopic(@Validated @RequestBody TopicDTO t, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "All fields are required"));
        }
        return topicService.AddTopics(t);
    }

    @PutMapping("/topic/{id}")
    ResponseEntity<?> UpdateTopic(@PathVariable int id, @RequestBody TopicDTO topics) {
        return topicService.UpdateTopic(id, topics);
    }

}
