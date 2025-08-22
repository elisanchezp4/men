package co.gov.mineducacion.controllers;

import co.gov.mineducacion.models.entities.Role;
import co.gov.mineducacion.services.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Role", description = "Endpoints para la gestión de roles de usuarios.")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService roleService;

    @Operation(summary = "Guarda un nuevo rol", description = "Crea un nuevo rol en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rol creado exitosamente.", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Role.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos.", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Role> save(@RequestBody Role role){
        return new ResponseEntity<>(roleService.save(role), CREATED);
    }

    @Operation(summary = "Obtiene todos los roles", description = "Devuelve una lista con todos los roles registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de roles obtenida exitosamente.", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = List.class)))
    })
    @GetMapping("/list")
    public ResponseEntity<List<Role>> findAll() {
        return new ResponseEntity<>(roleService.findAll(), OK);
    }
    @Operation(summary = "Busca un rol por su ID", description = "Devuelve un rol específico por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol encontrado.", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Role.class))),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado.", content = @Content)
    })
    @GetMapping("/role-id/{roleId}")
    public ResponseEntity<Role> findRoleById(@PathVariable Long roleId ){
        return new ResponseEntity<>(roleService.findById(roleId),OK);
    }
}
