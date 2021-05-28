package com.walktheline2;

import com.walktheline2.model.Event;
import com.walktheline2.model.Group;
import com.walktheline2.model.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initalizer implements CommandLineRunner {
    private final GroupRepository repository;

    public Initalizer(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Denver JUG", "Utah JUG", "Seattle JUG",
                "Richmond JUG").forEach(name -> repository.save(new Group(name))
        );
        Group djug = repository.findByName("Denver Jug");
        Event e = Event.builder()
                .title("Full Stack Reactive").description("Reactive with Spring Boot and React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println);


    }
}
