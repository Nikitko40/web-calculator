package ru.iskhakov.webcalculator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iskhakov.webcalculator.entities.ResultEntity;

@Repository
public interface ResultRepository extends JpaRepository<ResultEntity, Long> {
}
