package com.vladimir.dentalclinic.repositories;

import com.vladimir.dentalclinic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
