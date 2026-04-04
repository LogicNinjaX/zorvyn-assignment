package com.nitish.zorvyn_assignment.service.impl;

import com.nitish.zorvyn_assignment.dto.request.TransactionCreateRequest;
import com.nitish.zorvyn_assignment.dto.request.TransactionUpdateRequest;
import com.nitish.zorvyn_assignment.dto.response.PageResponse;
import com.nitish.zorvyn_assignment.dto.response.TransactionCreateResponse;
import com.nitish.zorvyn_assignment.dto.response.TransactionDetailsResponse;
import com.nitish.zorvyn_assignment.entity.FinancialRecord;
import com.nitish.zorvyn_assignment.entity.User;
import com.nitish.zorvyn_assignment.exception.TransactionNotFoundException;
import com.nitish.zorvyn_assignment.exception.UserNotFoundException;
import com.nitish.zorvyn_assignment.repository.FinancialRecordRepository;
import com.nitish.zorvyn_assignment.repository.UserRepository;
import com.nitish.zorvyn_assignment.service.FinancialRecordService;
import com.nitish.zorvyn_assignment.util.mapper.FinancialRecordMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Objects;
import java.util.UUID;

public class FinancialRecordServiceImpl implements FinancialRecordService {

    private static final Logger logger = LoggerFactory.getLogger(FinancialRecordServiceImpl.class);
    private final FinancialRecordRepository recordRepository;
    private final UserRepository userRepository;
    private final FinancialRecordMapper recordMapper;

    public FinancialRecordServiceImpl(FinancialRecordRepository recordRepository, UserRepository userRepository, FinancialRecordMapper recordMapper) {
        this.recordRepository = recordRepository;
        this.userRepository = userRepository;
        this.recordMapper = recordMapper;
    }


    @Transactional
    @Override
    public TransactionCreateResponse createTransaction(UUID userId, TransactionCreateRequest request) {
        User createdBy = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        FinancialRecord record = recordMapper.toRecord(request);
        record.setCreatedBy(createdBy);

        record = recordRepository.save(record);

        logger.info("Transaction record saved successfully [record id={}]", record.getRecordId());
        return recordMapper.toCreateResponse(record);
    }

    @Override
    public TransactionDetailsResponse getTransactionById(UUID recordId){
        FinancialRecord record = recordRepository.findRecordById(recordId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction record not found"));

        return recordMapper.toDetailsResponse(record);
    }

    @Override
    public PageResponse<TransactionDetailsResponse> getAllTransactions(Pageable pageable){
        Page<TransactionDetailsResponse> detailsResponseInPages = recordRepository.findAllRecord(pageable)
                .map(recordMapper::toDetailsResponse);

        return PageResponse.from(detailsResponseInPages);
    }

    @Transactional
    @Override
    public void updateTransactionRecord(UUID recordId, TransactionUpdateRequest request){
        FinancialRecord record = recordRepository.findRecordById(recordId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));

        patchDetailsIfProvided(record, request);

        record = recordRepository.save(record);
        logger.info("Transaction record updated successfully [record id={}]", record.getRecordId());
    }

    private void patchDetailsIfProvided(FinancialRecord record, TransactionUpdateRequest request){
        if (Objects.nonNull(request.amount())) record.setAmount(request.amount());
        if (Objects.nonNull(request.type())) record.setType(request.type());
        if (Objects.nonNull(request.category())) record.setCategory(request.category());
        if (Objects.nonNull(request.timeStamp())) record.setTimeStamp(request.timeStamp());
        if (Objects.nonNull(request.description())) record.setDescription(request.description());
        if (Objects.nonNull(request.amount())) record.setAmount(request.amount());
        if (Objects.nonNull(request.deleted())) record.setDeleted(request.deleted());
    }


    @Transactional
    @Override
    public void softDeleteTransaction(UUID recordId){
        FinancialRecord record = recordRepository.findRecordById(recordId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));

        if (!record.isDeleted()){
            record.setDeleted(true);
        }

        record = recordRepository.save(record);
        logger.info("Transaction soft deletion completed successfully [record id={}]", record.getRecordId());
    }

}
