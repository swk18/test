package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
class MyController {
    private List<Tea> teas = new ArrayList<>();

    public MyController(){
        teas.addAll(List.of(
                new Tea("Зеленный"),
                new Tea("Черный"),
                new Tea("Красный"),
                new Tea("Пуэро")
        ));
    }
    @GetMapping("/teas/{id}")      // @RequestMapping(value = "/teas", method = RequestMethod.GET)
    Optional<Tea> getTeaById(@PathVariable String id){
        for(Tea t: teas){
            if(t.getId().equals(id)) {
                return Optional.of(t);
            }
        }
        return Optional.empty();
    }

    @PostMapping("/teas")  // @RequestMapping(value = "/teas", method = RequestMethod.POST)
    Tea postTea(@RequestBody Tea tea){
        teas.add(tea);
        return tea;
    }


    @PutMapping("/{id}")
    ResponseEntity<Tea> putTea(@PathVariable String id, @RequestBody Tea tea) {
        int teaNumber = -1;
        for (Tea c : teas) {
            if (c.getId().equals(id)) {
                teaNumber = teas.indexOf(c);
                teas.set(teaNumber, tea);
            }
        }
        return (teaNumber == -1) ?
                new ResponseEntity<>(postTea(tea), HttpStatus.CREATED) :
                new ResponseEntity<>(tea, HttpStatus.OK);
    }


    @DeleteMapping("/teas/{id}")
    void deleteTea(@PathVariable String id) {
        teas.removeIf(t -> t.getId().equals(id));
    }
}