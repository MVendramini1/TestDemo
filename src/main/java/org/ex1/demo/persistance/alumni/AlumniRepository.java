package org.ex1.demo.persistance.alumni;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumniRepository  extends JpaRepository<AlumniEntity, Integer> {
    List<AlumniEntity> findByName(String name);
}
