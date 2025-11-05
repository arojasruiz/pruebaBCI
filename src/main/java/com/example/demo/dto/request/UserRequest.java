package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @Schema(description = "Nombre del usuario", example = "Andrea", required = true)
    private String name;

    @Schema(description = "Email del usuario con formato correcto", example = "Andrea@gmail.cl", required = true)
    private String email;

    @Schema(description = "Contraseña del usuario", example = "Andrea123", required = true)
    private String password;

    @Schema(description = "Lista de teléfonos del usuario")
    private List<PhoneRequest> phones;
}
