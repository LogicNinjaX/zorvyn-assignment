package com.nitish.zorvyn_assignment.repository;

import com.nitish.zorvyn_assignment.dto.CategorySummaryDTO;
import com.nitish.zorvyn_assignment.dto.MonthlySummaryDTO;
import com.nitish.zorvyn_assignment.entity.FinancialRecord;
import com.nitish.zorvyn_assignment.enums.RecordType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
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

    @Query("""
                SELECT COALESCE(SUM(fr.amount), 0)
                FROM FinancialRecord fr
                WHERE fr.type = :type
            """)
    BigDecimal fetchTotalAmountByType(RecordType type);

    @Query("""
            SELECT fr.category AS category, COALESCE(SUM(fr.amount), 0) AS total
            FROM FinancialRecord fr
            GROUP BY fr.category
            """)
    List<CategorySummaryDTO> getCategorySummary();

    @Query("""
                SELECT FUNCTION('MONTH', fr.timeStamp) AS month, COALESCE(SUM(fr.amount), 0) AS total
                FROM FinancialRecord fr
                WHERE fr.type = :type
                GROUP BY FUNCTION('MONTH', fr.timeStamp)
                ORDER BY month
            """)
    List<MonthlySummaryDTO> getMonthlySummary(RecordType type);
}
