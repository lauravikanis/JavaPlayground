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
class Initializer implements CommandLineRunner {
    private final GroupRepository repository;

    public Initializer(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Cologne Stuff", "Düsseldorf Stuff", "Berlin Stuff",
                "Hamburg Stuff").forEach(name -> repository.save(new Group(name))
        );
        Group djug = repository.findByName("Cologne Stuff");
        Event e = Event.builder()
                .title("Full Stack ").description("Reactive with Spring Boot and React")
                .date(Instant.parse("2021-12-12T18:00:00.000Z"))
                .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println);


    }
}
