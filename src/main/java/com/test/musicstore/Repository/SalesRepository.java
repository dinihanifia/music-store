package com.test.musicstore.Repository;
import com.test.musicstore.POJO.Entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SalesRepository extends JpaRepository<Sales, UUID> {
    List<Sales> findByCreatedAt(LocalDateTime createdAt);
    List<Sales> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
