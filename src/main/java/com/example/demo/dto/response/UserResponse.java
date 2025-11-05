package com.example.demo.dto.response;

import com.example.demo.model.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    @Schema(description = "ID del usuario generado por la BD", example = "1")
    private Long id;

    @Schema(description = "Nombre del usuario", example = "Andrea")
    private String name;

    @Schema(description = "Correo electrónico del usuario", example = "andra@gmail.cl")
    private String email;

    @Schema(description = "Fecha de creación", example = "2025-11-04 15:38:23")
    private String created;

    @Schema(description = "Fecha de última modificación", example = "2025-11-04 15:38:23")
    private String modified;

    @Schema(description = "Fecha de última login", example = "2025-11-04 15:38:23")
    private String lastLogin;

    @Schema(description = "Token JWT generado para el usuario", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6...")
    private String token;

    @Schema(description = "Indica si el usuario sigue activo", example = "true")
    private boolean isActive;

}
