package com.userservice.repository;

import com.userservice.domain.model.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, Integer> {

    Optional<Student> findByRollNo(int rollNo);

    void deleteByRollNo(int rollNo);
}
