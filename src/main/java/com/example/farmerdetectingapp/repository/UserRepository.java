package com.example.farmerdetectingapp.repository;

import com.example.farmerdetectingapp.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserProfile,Long> {



    Optional<UserProfile> findAllByEmail(String email);
}
