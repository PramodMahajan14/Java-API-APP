package eduin.courseapi.Repositories;

import org.springframework.data.repository.CrudRepository;

import eduin.courseapi.Models.Topics;

public interface TopicRepo extends CrudRepository<Topics, Integer> {

    boolean existsByName(String name);

}