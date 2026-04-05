package com.nitish.zorvyn_assignment.service.impl;

import com.nitish.zorvyn_assignment.dto.response.CategorySummaryResponse;
import com.nitish.zorvyn_assignment.dto.response.DashboardResponse;
import com.nitish.zorvyn_assignment.dto.response.MonthlySummaryResponse;
import com.nitish.zorvyn_assignment.dto.response.TransactionDetailsResponse;
import com.nitish.zorvyn_assignment.enums.RecordType;
import com.nitish.zorvyn_assignment.repository.FinancialRecordRepository;
import com.nitish.zorvyn_assignment.service.DashboardService;
import com.nitish.zorvyn_assignment.util.mapper.FinancialRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    private static final Logger logger = LoggerFactory.getLogger(DashboardServiceImpl.class);
    private final FinancialRecordRepository recordRepository;
    private final FinancialRecordMapper recordMapper;

    public DashboardServiceImpl(FinancialRecordRepository recordRepository, FinancialRecordMapper recordMapper) {
        this.recordRepository = recordRepository;
        this.recordMapper = recordMapper;
    }


    @Override
    public DashboardResponse getDashboard(){

        BigDecimal income = recordRepository.fetchTotalAmountByType(RecordType.INCOME);
        BigDecimal expense = recordRepository.fetchTotalAmountByType(RecordType.EXPENSE);

        BigDecimal netBalance = income.subtract(expense); // Net balance

        List<CategorySummaryResponse> categories = recordRepository
                .getCategorySummary()
                .stream()
                .map(dto -> new CategorySummaryResponse(dto.getCategory(), dto.getTotal()))
                .collect(Collectors.toList());

        // Fetching recent transactions (top 5)
        List<TransactionDetailsResponse> recentTransactions = recordRepository
                .findAllRecord(PageRequest.of(0, 5, Sort.by("timeStamp").descending()))
                .map(recordMapper::toDetailsResponse).toList();

        List<MonthlySummaryResponse> monthlyTrends = recordRepository
                .getMonthlySummary(RecordType.INCOME)
                .stream()
                .map(dto -> new MonthlySummaryResponse(dto.getMonth(), dto.getTotal()))
                .collect(Collectors.toList());


        return new DashboardResponse(
                income,
                expense,
                netBalance,
                categories,
                recentTransactions,
                monthlyTrends
        );
    }
}
