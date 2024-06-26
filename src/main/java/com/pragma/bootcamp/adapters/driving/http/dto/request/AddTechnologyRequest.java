package com.pragma.bootcamp.adapters.driving.http.dto.request;


import com.pragma.bootcamp.domain.util.DomainConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddTechnologyRequest {
    @NotEmpty(message = DomainConstants.FIELD_NAME_EMPTY_MESSAGE)
    @Size(min = 1, max = 50)
    private  String name;
    @Size (min = 10, max = 90)
    @NotEmpty(message = DomainConstants.FIELD_DESCRIPTION_EMPTY_MESSAGE)
    private  String description;
}
