package com.nitish.zorvyn_assignment.util.mapper;

import com.nitish.zorvyn_assignment.dto.response.UserDetailsResponse;
import com.nitish.zorvyn_assignment.dto.response.UserUpdateResponse;
import com.nitish.zorvyn_assignment.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserUpdateResponse toUpdateResponse(User user);

    UserDetailsResponse toDetailsResponse(User user);
}
