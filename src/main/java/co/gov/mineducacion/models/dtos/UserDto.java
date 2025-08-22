package co.gov.mineducacion.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String secondName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String secondLastName;
    @NotBlank
    private String email;
    @NotBlank
    private String updatedUser;
}
