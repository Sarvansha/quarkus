package com.example.springnative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class FoodController {

    @Autowired
    FoodRepository foodRepository;

    @GetMapping
    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello Spring Native!!";
    }

    @GetMapping(value = "/foods")
    public List<Food> getAllItems() {
        Iterable<Food> itr = foodRepository.findAll();

        return StreamSupport.stream(itr.spliterator(), false)
                .collect(Collectors.toList());
    }


    @PostMapping(value = "/foods")
    public ResponseEntity<Long> saveFood(@RequestBody Food food) {
       Food foodSaved = foodRepository.save(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodSaved.getId());
    }

}
