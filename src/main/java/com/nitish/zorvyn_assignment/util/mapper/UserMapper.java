package com.nitish.zorvyn_assignment.util.mapper;

import com.nitish.zorvyn_assignment.dto.request.UserRegisterRequest;
import com.nitish.zorvyn_assignment.dto.response.UserDetailsResponse;
import com.nitish.zorvyn_assignment.dto.response.UserUpdateResponse;
import com.nitish.zorvyn_assignment.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserUpdateResponse toUpdateResponse(User user);

    UserDetailsResponse toDetailsResponse(User user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "financialRecords", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toUser(UserRegisterRequest request);
}
