package co.gov.mineducacion.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleApplicationDto {

    private Long userId;
    private List<Long> roles;
    private Long applicationId;
}
