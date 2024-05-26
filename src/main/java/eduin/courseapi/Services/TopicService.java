package eduin.courseapi.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;

import eduin.courseapi.Models.ErrorResponse;
import eduin.courseapi.Models.Topics;
import eduin.courseapi.Repositories.TopicRepo;
import eduin.courseapi.TopicDTO.TopicDTO;

@Service
public class TopicService {

    private final TopicRepo repo;

    public TopicService(TopicRepo resRepo) {
        this.repo = resRepo;
    }

    public List<Topics> getAllTopics() {
        return (List<Topics>) repo.findAll();
    }

    public ResponseEntity<?> getTopic(int id) {
        Optional<Topics> topicooptaional = repo.findById(id);
        if (topicooptaional.isPresent()) {
            return ResponseEntity.ok().body(topicooptaional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Topic not found with id: " + id));
        }

    }

    public ResponseEntity<?> AddTopics(TopicDTO t) {

        if (repo.existsByName(t.getName())) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED.value())
                    .body(new ErrorResponse(HttpStatus.ALREADY_REPORTED.value(), "This Topic Already Exist"));
        }
        Topics topic = new Topics();
        topic.setId(t.getId());
        topic.setName(t.getName());
        topic.setDescription(t.getDescription());
        repo.save(topic);
        return new ResponseEntity<Topics>(topic, HttpStatus.CREATED);
    }

    public ResponseEntity<?> UpdateTopic(int id, TopicDTO topic) {

        Optional<Topics> topics = repo.findById(id);
        if (topics.isPresent()) {
            System.out.println(topic.getName());
            Topics existTopic = topics.get();
            existTopic.setName(topic.getName());
            existTopic.setDescription(topic.getDescription());
            repo.save(existTopic);
            return ResponseEntity.ok("User details updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "This Topic not exist"));
        }

    }

}
