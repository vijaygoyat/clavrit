package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.AdminUserRequestDto;
import com.example.demo.dto.AdminUserResponseDto;

public interface AdminUserService {
	
	AdminUserResponseDto createAdmin(AdminUserRequestDto dto);

    AdminUserResponseDto getAdminById(Long id);

    List<AdminUserResponseDto> getAllAdmins();

    AdminUserResponseDto updateAdmin(Long id, AdminUserRequestDto dto);

    String deleteAdmin(Long id);

}
