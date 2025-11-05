package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneRequest {
    @Schema(description = "Teléfono del usuario")
    public int number;

    @Schema(description = "Código de ciudad en caso de número telefónico red fija")
    public int citycode;

    @Schema(description = "Código del país en caso de número telefónico red fija", example = "+56")
    public String contrycode;
}
