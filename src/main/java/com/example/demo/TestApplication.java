package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@SpringBootApplication
public class TestApplication {



	public static void main(String[] args) {

		SpringApplication.run(TestApplication.class, args);

		Tea tea1 = new Tea( "Красный");
		System.out.println("ID: "+ tea1.getId() + " Чай: = " +tea1.getName());
		Tea tea2 = new Tea("1234","Черный");
		tea2.setName();
		System.out.println("ID: "+ tea2.getId() + " Чай: = " +tea2.getName());




	}

}

class Tea {
	private final String id;
	private String name;


	public Tea(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Tea(String name) {

		this(UUID.randomUUID().toString(), name);

	}

	public String getId() {
		return id;

	}

	public String getName() {
		return name;
	}


	public void setName() {

		this.name = "Черный";
	}

	}

@RestController
class listTea {
  private List<Tea> teas = new ArrayList<>();

public listTea(){
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
	ResponseEntity<Tea> putTea(@PathVariable String id,@RequestBody Tea tea) {
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