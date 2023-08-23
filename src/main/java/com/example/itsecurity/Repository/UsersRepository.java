package com.example.itsecurity.Repository;

import com.example.itsecurity.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
}
