package co.gov.mineducacion.controllers;

import co.gov.mineducacion.exceptions.errors.ErrorMessage;
import co.gov.mineducacion.models.dtos.ApplicationDto;
import co.gov.mineducacion.models.entities.Application;
import co.gov.mineducacion.services.IApplicationService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Application", description = "Endpoints para la gestión de solicitudes")
@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final IApplicationService applicationService;

    @Operation(summary = "Guarda una nueva solicitud", description = "Crea y guarda una nueva solicitud en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Declaration sent successfully", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Application.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input data", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PostMapping
    public ResponseEntity<Application> save(@RequestBody Application application){
        return new ResponseEntity<>(applicationService.save(application), CREATED);
    }

    @Operation(summary = "Actualiza una solicitud existente", description = "Actualiza los datos de una solicitud por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud actualizada exitosamente", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Application.class))),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PutMapping("/application-id/{applicationId}")
    public ResponseEntity<Application> update(@PathVariable Long applicationId, @RequestBody ApplicationDto application){
        return new ResponseEntity<>(applicationService.update(applicationId, application), OK);
    }

    @Operation(summary = "Obtiene todas las solicitudes", description = "Devuelve una lista con todas las solicitudes registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de solicitudes obtenida exitosamente", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = List.class)))
    })
    @GetMapping("/list")
    public ResponseEntity<List<Application>> findAll() {
        return new ResponseEntity<>(applicationService.findAll(), OK);
    }

    @Operation(summary = "Busca una solicitud por su ID", description = "Devuelve una solicitud específica por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Application.class))),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/application-id/{applicationId}")
    public ResponseEntity<Application> findApplicationById(@PathVariable Long applicationId){
        return new ResponseEntity<>(applicationService.findById(applicationId), OK);
    }

    @Operation(summary = "Busca una solicitud por código de tipo", description = "Devuelve una solicitud por el código de tipo (codeType).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Application.class))),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/code-type/{codeType}")
    public ResponseEntity<Application> findByApplicationTypeCode(@PathVariable String codeType){
        return new ResponseEntity<>(applicationService.findByCodeType(codeType), OK);
    }
}
