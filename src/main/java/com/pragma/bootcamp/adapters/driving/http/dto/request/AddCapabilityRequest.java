package com.pragma.bootcamp.adapters.driving.http.dto.request;


import com.pragma.bootcamp.domain.util.DomainConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddCapabilityRequest {
    @NotEmpty(message = DomainConstants.FIELD_NAME_EMPTY_MESSAGE)
    private String name;
    @NotEmpty(message = DomainConstants.FIELD_DESCRIPTION_EMPTY_MESSAGE)
    private String description;
    @Size(min = 3, max = 20)
    private List<AddTechnologyToCapability> technologies;
}
