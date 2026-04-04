package com.nitish.zorvyn_assignment.repository;

import com.nitish.zorvyn_assignment.entity.FinancialRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, UUID> {

    @Query("""
            SELECT fr FROM FinancialRecord fr
            JOIN FETCH createdBy
            WHERE fr.recordId = :recordId
            """)
    Optional<FinancialRecord> findRecordById(UUID recordId);

    @Query("""
            SELECT fr FROM FinancialRecord fr
            JOIN FETCH createdBy
            """)
    Page<FinancialRecord> findAllRecord(Pageable pageable);
}
