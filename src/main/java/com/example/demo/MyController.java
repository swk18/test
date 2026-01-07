package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
class MyController {

    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Tea> teas = new ArrayList<>();
    private Object Tea;

    public MyController(){
        teas.addAll(List.of(
                new Tea("Зеленный"),
                new Tea("Черный"),
                new Tea("Красный"),
                new Tea("Пуэро")
        ));
    }
    @GetMapping("/teas")   // Связал URL с методом teas для отображения всего списка «чая».
    Iterable<Tea> getTeas(){
        return teas;
    }


    @GetMapping("/{id}")      // @RequestMapping(value = "/teas", method = RequestMethod.GET)
    Object getTeaById(@PathVariable String id){
        for(Tea t: teas){
            Tea tea = Optional.of(t).get();  // Если t=null, то Optional выкинет исключение NPE (NullPointerException).
            if(tea.getId().equals(id)) {
                return tea;
            }
        }

        MyError myError = new MyError("Чай не найден"); // Если чай не найден, возвращаем объект с ошибкой в ответ в виде JSON

        return myError;
    }

    @PostMapping  // @RequestMapping(value = "/teas", method = RequestMethod.POST)
    Tea postTea(@RequestBody Tea tea){
        teas.add(tea);
        return tea;
        }

    @PutMapping("/{id}")
    Tea putTea(@PathVariable String id, @RequestBody Tea tea) {
        int teaNumber = -1;

        for (Tea t : teas) {
            if (t.getId().equals(id)) {
                teaNumber = teas.indexOf(t);
                teas.set(teaNumber, tea);
            }
        }
        return (teaNumber == -1) ? postTea(tea) : tea;

    }


    @DeleteMapping("/teas/{id}")
    void deleteTea(@PathVariable String id) {
        teas.removeIf(t -> t.getId().equals(id));
    }
}