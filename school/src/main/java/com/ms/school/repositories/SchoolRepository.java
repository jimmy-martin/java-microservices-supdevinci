package com.ms.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ms.school.models.School;

public interface SchoolRepository extends JpaRepository<School, Long> {

}
