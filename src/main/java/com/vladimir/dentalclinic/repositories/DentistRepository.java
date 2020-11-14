package com.vladimir.dentalclinic.repositories;

import com.vladimir.dentalclinic.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Long> {

    @Query(
            nativeQuery = true,
            value = "select* from dentist d where d.user_id = ?1",
            countQuery = "select count(*) from dentist d where d.user_id = ?1"
    )
    Dentist findByUserId(String username);

}
