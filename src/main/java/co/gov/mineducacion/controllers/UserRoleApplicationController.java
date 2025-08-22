package co.gov.mineducacion.controllers;

import co.gov.mineducacion.models.dtos.RoleDto;
import co.gov.mineducacion.models.dtos.UserRoleApplicationDto;
import co.gov.mineducacion.models.entities.UserRoleApplication;
import co.gov.mineducacion.services.IUserRoleApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "User Role Application", description = "Endpoints para la gestión de las relaciones entre usuarios, roles y aplicaciones.")
@RestController
@RequestMapping("/user-role-application")
@RequiredArgsConstructor
public class UserRoleApplicationController {

    private final IUserRoleApplicationService userRoleApplicationService;

    @Operation(summary = "Obtiene todas las asignaciones de roles a usuarios por aplicación", description = "Devuelve una lista con todas las relaciones de roles de usuario y sus respectivas aplicaciones.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de asignaciones obtenida exitosamente.", content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud.", content = @Content)
    })
    @GetMapping("/list")
    public ResponseEntity<List<UserRoleApplication>> findAll(){
        return new ResponseEntity<>(userRoleApplicationService.findAll(), OK);
    }

    @Operation(summary = "Asigna uno o más roles a un usuario", description = "Asigna uno o más roles a un usuario en una aplicación específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles asignados exitosamente.", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "Usuario, rol o aplicación no encontrados.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos.", content = @Content)
    })
    @PostMapping("/assign-role-to-user")
    public ResponseEntity<List<UserRoleApplication>> assignRoleToUser(@RequestBody UserRoleApplicationDto userRoleApplication){
        return new ResponseEntity<>(userRoleApplicationService.assignRolesToUser(userRoleApplication),OK);
    }
    @Operation(summary = "Elimina roles a un usuario", description = "Remueve la asignación de roles a un usuario en una aplicación específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles removidos exitosamente. Devuelve el número de registros afectados.", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Integer.class))),
            @ApiResponse(responseCode = "404", description = "Usuario, rol o aplicación no encontrados.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos.", content = @Content)
    })
    @DeleteMapping("/remove-roles-to-user")
    public ResponseEntity<Integer> removeRoleToUser(@RequestBody RoleDto roleDto){
        return new ResponseEntity<>(userRoleApplicationService.removeRoleToUser(roleDto),OK);
    }
}
