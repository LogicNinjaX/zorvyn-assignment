package com.nitish.zorvyn_assignment.service;

import com.nitish.zorvyn_assignment.dto.request.TransactionCreateRequest;
import com.nitish.zorvyn_assignment.dto.request.TransactionUpdateRequest;
import com.nitish.zorvyn_assignment.entity.FinancialRecord;
import com.nitish.zorvyn_assignment.entity.User;
import com.nitish.zorvyn_assignment.enums.Category;
import com.nitish.zorvyn_assignment.enums.RecordType;
import com.nitish.zorvyn_assignment.exception.InvalidCategoryException;
import com.nitish.zorvyn_assignment.exception.TransactionNotFoundException;
import com.nitish.zorvyn_assignment.exception.UserNotFoundException;
import com.nitish.zorvyn_assignment.repository.FinancialRecordRepository;
import com.nitish.zorvyn_assignment.repository.UserRepository;
import com.nitish.zorvyn_assignment.service.impl.FinancialRecordServiceImpl;
import com.nitish.zorvyn_assignment.util.mapper.FinancialRecordMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FinancialRecordServiceImplTest {

    @Mock
    private FinancialRecordRepository recordRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FinancialRecordMapper recordMapper;

    @InjectMocks
    private FinancialRecordServiceImpl service;

    private UUID userId;
    private UUID recordId;

    private User user;
    private FinancialRecord record;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        recordId = UUID.randomUUID();

        user = new User();
        user.setUserId(userId);

        record = new FinancialRecord();
        record.setRecordId(recordId);
        record.setDeleted(false);
    }


    @Test
    void createTransaction_shouldThrowException_whenCategoryMismatch() {

        TransactionCreateRequest request = mock(TransactionCreateRequest.class);
        Category category = mock(Category.class);

        when(request.category()).thenReturn(category);
        when(request.type()).thenReturn(RecordType.INCOME);
        when(category.getRecordType()).thenReturn(RecordType.EXPENSE);

        assertThrows(InvalidCategoryException.class,
                () -> service.createTransaction(userId, request));
    }

    @Test
    void createTransaction_shouldThrowException_whenUserNotFound() {

        TransactionCreateRequest request = mock(TransactionCreateRequest.class);
        Category category = mock(Category.class);

        when(request.category()).thenReturn(category);
        when(request.type()).thenReturn(RecordType.INCOME);
        when(category.getRecordType()).thenReturn(RecordType.INCOME);

        when(userRepository.findUserById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> service.createTransaction(userId, request));
    }


    @Test
    void getTransactionById_shouldThrowException_whenNotFound() {

        when(recordRepository.findRecordById(recordId)).thenReturn(Optional.empty());

        assertThrows(TransactionNotFoundException.class,
                () -> service.getTransactionById(recordId));
    }

    @Test
    void updateTransactionRecord_shouldUpdateSuccessfully() {

        TransactionUpdateRequest request = mock(TransactionUpdateRequest.class);

        when(recordRepository.findRecordById(recordId)).thenReturn(Optional.of(record));
        when(recordRepository.save(record)).thenReturn(record);

        service.updateTransactionRecord(recordId, request);

        verify(recordRepository).save(record);
    }

    @Test
    void updateTransactionRecord_shouldThrowException_whenNotFound() {

        TransactionUpdateRequest request = mock(TransactionUpdateRequest.class);

        when(recordRepository.findRecordById(recordId)).thenReturn(Optional.empty());

        assertThrows(TransactionNotFoundException.class,
                () -> service.updateTransactionRecord(recordId, request));
    }

    @Test
    void softDeleteTransaction_shouldMarkDeleted() {

        when(recordRepository.findRecordById(recordId)).thenReturn(Optional.of(record));
        when(recordRepository.save(record)).thenReturn(record);

        service.softDeleteTransaction(recordId);

        assertTrue(record.isDeleted());
        verify(recordRepository).save(record);
    }

    @Test
    void softDeleteTransaction_shouldThrowException_whenNotFound() {

        when(recordRepository.findRecordById(recordId)).thenReturn(Optional.empty());

        assertThrows(TransactionNotFoundException.class,
                () -> service.softDeleteTransaction(recordId));
    }
}
