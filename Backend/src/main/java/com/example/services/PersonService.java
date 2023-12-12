package com.example.services;

import com.example.dto.PersonDto;
import com.example.entity.Person;
import com.example.repository.PersonRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PersonService {
    private final PersonRepo personRepo;

    public PersonDto getPerson(Long id) {
        log.info("Get Person From Database");
        Optional<Person> optionalPerson = personRepo.findById(id);
        if (optionalPerson.isEmpty()) {
            return null;
        } else {
            PersonDto dto = new PersonDto();
            BeanUtils.copyProperties(optionalPerson.get(), dto);
            return dto;
        }
    }

    public List<PersonDto> getAllPerson() {
        log.info("Get All Person From Database");
        List<Person> personList = personRepo.findAll();
        List<PersonDto> personDtoList = new ArrayList<>();
        for (Person person : personList) {
            PersonDto dto = new PersonDto();
            BeanUtils.copyProperties(person, dto);
            personDtoList.add(dto);
        }
        return personDtoList;
    }


    public PersonDto save(PersonDto dto) {
        Person person = new Person();
        BeanUtils.copyProperties(dto, person);
        person = personRepo.save(person);
        BeanUtils.copyProperties(person, dto);
        return dto;
    }




    /**-- For multiple Caching Command --**/

    public PersonDto update(PersonDto dto) {
        Person person = new Person();
        BeanUtils.copyProperties(dto, person);
        person = personRepo.save(person);
        BeanUtils.copyProperties(person, dto);
        return dto;
    }

    @Caching(
            evict = {@CacheEvict(value = "personList", allEntries = true),
                    @CacheEvict(value = "person", key = "#id")
            }
    )
    public void delete(Long id) {
        personRepo.deleteById(id);
    }

}
