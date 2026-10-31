package com.LibraryManagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {
  @NotBlank(message = "username is required")
  private String userName;
  @NotBlank(message = "password is required")
  private String password;
  @NotBlank(message = "email is required")
  private String email;
}
