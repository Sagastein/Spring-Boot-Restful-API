package com.sage.java.RestfulApi.Repository;

import com.sage.java.RestfulApi.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
