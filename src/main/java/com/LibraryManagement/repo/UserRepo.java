package com.LibraryManagement.repo;


import com.LibraryManagement.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users,Long> {
    Optional<Users> findByUserName(String userName);
}
