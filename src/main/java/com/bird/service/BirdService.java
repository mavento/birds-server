package com.bird.service;

import com.bird.dto.BirdDTO;
import com.bird.model.Bird;
import com.bird.repository.BirdRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BirdService {

    @Autowired
    private BirdRepository birdRepository;

    public Bird findById(Long id) {
        return birdRepository.findById(id).orElse(null);
    }

    public Bird createOrUpdate(BirdDTO birdDTO) {
        Bird bird = birdRepository.findById(birdDTO.getId()).orElse(new Bird());
        BeanUtils.copyProperties(birdDTO, bird);
        this.save(bird);
        return null;
    }

    public List<Bird> list() {
        List<Bird> list = birdRepository.findAll();
        return list;
    }

    public void save(Bird task) {
        birdRepository.save(task);
    }


}
