package co.gov.mineducacion.controllers;

import co.gov.mineducacion.models.dtos.PasswordDto;
import co.gov.mineducacion.models.dtos.UserDto;
import co.gov.mineducacion.models.entities.User;
import co.gov.mineducacion.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Tag(name = "User", description = "Endpoints para la gestión de usuarios.")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @Operation(summary = "Guarda un nuevo usuario", description = "Crea y guarda un nuevo usuario en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<User> save(@Valid @RequestBody User user, BindingResult result){

        /*if (result.hasErrors()) {
            return createValidationErrorResponse(result.getFieldErrors());
        }
        Map<String, Object> response = new HashMap<>();
        response.put("document", userService.save(user));*/

        return new ResponseEntity<>(userService.save(user), CREATED);
    }

    @Operation(summary = "Actualiza un usuario existente", description = "Actualiza los datos de un usuario por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/user-id/{userId}")
    public ResponseEntity<User> update(@PathVariable Long userId, @Valid @RequestBody UserDto user, BindingResult result){
        return new ResponseEntity<>(userService.update(userId, user), OK);
    }

    @Operation(summary = "Obtiene todos los usuarios", description = "Devuelve una lista con todos los usuarios registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente", content = @Content(schema = @Schema(implementation = List.class)))
    })

    @GetMapping("/list")
    public ResponseEntity<Page<User>> findAll(@PageableDefault() Pageable pageable) {
        return new ResponseEntity<>(userService.findAll(pageable), OK);
    }

    @Operation(summary = "Busca un usuario por su ID", description = "Devuelve un usuario específico por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/user-id/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.findById(userId), OK);
    }

    @Operation(summary = "Busca un usuario por su dirección de correo electrónico", description = "Devuelve un usuario por su email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/email")
    public ResponseEntity<User> findByEmail(@PathParam("email") String email){
        return new ResponseEntity<>(userService.findByEmail(email),OK);
    }

    @Operation(summary = "Cambia la contraseña de un usuario", description = "Permite cambiar la contraseña de un usuario a partir del DTO de contraseña.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contraseña cambiada exitosamente. Devuelve un mensaje de confirmación.", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/changed-password")
    public ResponseEntity<String> changedPassword(@RequestBody PasswordDto passwordDto){
        return new ResponseEntity<>(userService.changedPassword(passwordDto), OK);
    }
}
