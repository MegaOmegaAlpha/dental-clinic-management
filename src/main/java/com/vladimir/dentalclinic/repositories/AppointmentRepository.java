package com.vladimir.dentalclinic.repositories;

import com.vladimir.dentalclinic.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(
            nativeQuery = true,
            value = "select* from appointment a where a.appointment_id not in (select app.appointment_id " +
                    "from appointment app join visit v on app.appointment_id = v.appointment_id)",
            countQuery = "select count(*) from appointment a where a.appointment_id not in (select app.appointment_id " +
                    "from appointment app join visit v on app.appointment_id = v.appointment_id)"
    )
    List<Appointment> findAllNotInVisit();

}
