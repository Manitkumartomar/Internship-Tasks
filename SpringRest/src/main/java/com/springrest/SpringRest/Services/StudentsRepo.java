package com.springrest.SpringRest.Services;


import org.springframework.data.repository.CrudRepository;

import com.springrest.SpringRest.Entities.Students;

public interface StudentsRepo extends CrudRepository<Students, Integer> {


}
