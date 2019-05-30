package com.bird.repository;

import com.bird.model.Bird;
import com.bird.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdRepository extends JpaRepository<Bird, Long> {

}
