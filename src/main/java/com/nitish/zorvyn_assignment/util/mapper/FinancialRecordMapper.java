package com.nitish.zorvyn_assignment.util.mapper;

import com.nitish.zorvyn_assignment.dto.request.TransactionCreateRequest;
import com.nitish.zorvyn_assignment.dto.response.TransactionCreateResponse;
import com.nitish.zorvyn_assignment.dto.response.TransactionDetailsResponse;
import com.nitish.zorvyn_assignment.entity.FinancialRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FinancialRecordMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "recordId", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    FinancialRecord toRecord(TransactionCreateRequest request);

    @Mapping(target = "createdBy", source = "createdBy.userId")
    TransactionCreateResponse toCreateResponse(FinancialRecord record);

    @Mapping(target = "createdBy", source = "createdBy.userId")
    TransactionDetailsResponse toDetailsResponse(FinancialRecord record);
}
