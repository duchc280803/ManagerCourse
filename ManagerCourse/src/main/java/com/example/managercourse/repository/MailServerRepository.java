package com.example.managercourse.repository;

import com.example.managercourse.entity.EmailServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailServerRepository extends JpaRepository<EmailServer, Long> {
}
