package com.example.controllers;

import com.example.dto.PersonDto;
import com.example.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
@CrossOrigin("*")
public class PersonController {
    private final PersonService personService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PersonDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personService.save(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody PersonDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.update(dto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        PersonDto dto = personService.getPerson(id);
        return dto != null
                ? ResponseEntity.status(HttpStatus.OK).body(dto)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        List<PersonDto> allPerson = personService.getAllPerson();
        return !allPerson.isEmpty()
                ? ResponseEntity.status(HttpStatus.OK).body(allPerson)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Delete Successfully....");
    }
}
