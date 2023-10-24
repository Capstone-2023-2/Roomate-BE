package org.example.springbootdeveloper.dorm_auth.repository;

import org.example.springbootdeveloper.dorm_auth.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

}