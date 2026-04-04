package com.nitish.zorvyn_assignment.repository;

import com.nitish.zorvyn_assignment.entity.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, UUID> {
}
