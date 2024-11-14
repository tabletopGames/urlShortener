package com.piotr.wanat.urlshortener.rest.dto;

import com.piotr.wanat.urlshortener.validation.ValidUrl;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ValidUrl
public class SimpleMappingDto {

    @NotNull
    private String url;

}
