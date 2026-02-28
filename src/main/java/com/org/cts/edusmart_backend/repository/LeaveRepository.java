package com.org.cts.edusmart_backend.repository;

import com.org.cts.edusmart_backend.entity.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveApplication, Long> {
}