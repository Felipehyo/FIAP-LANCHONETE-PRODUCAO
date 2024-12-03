package com.postech.infra.resource;

import com.postech.domain.entities.Producao;
import com.postech.infra.dto.ErroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/preparar")
@Tag(name = "Producao", description = "Recursos relacionados a operacionalização/produção de um pedido")
public interface ProducaoResource {

    @Operation(summary = "Iniciar preparo", method = "POST", description = "Recurso para iniciar o preparo de um pedido")
    @ApiResponses(value = {
            @ApiResponse(description = "Preparo iniciado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producao.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao iniciar preparo do pedido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
            @ApiResponse(description = "Preparo não foi encontrado", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> iniciarPreparo(@PathVariable Long id);

    @Operation(summary = "Finalizar preparo", method = "GET", description = "Recurso para finalizar preparo de um pedido")
    @ApiResponses(value = {
            @ApiResponse(description = "Preparo finalizado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producao.class))),
            @ApiResponse(description = "Erro ao finalizar preparo do pedido", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
            @ApiResponse(description = "Preparo não foi encontrado", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))),
    })
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> finalizarPreparo(@PathVariable Long id);

}