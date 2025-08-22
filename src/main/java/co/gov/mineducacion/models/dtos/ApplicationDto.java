package co.gov.mineducacion.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {

    private String applicationTypeCode;
    private String applicationName;
    private String description;
    private String updatedUser;


}
