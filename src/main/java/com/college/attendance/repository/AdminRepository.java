package com.college.attendance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.attendance.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,String>{

Optional<Admin> findByEmail(String email);

}