package com.postech.infra.controller;

import com.postech.application.usecases.ProducaoUseCases;
import com.postech.domain.entities.Producao;
import com.postech.infra.mappers.ProducaoMapper;
import com.postech.infra.resource.ProducaoResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProducaoController implements ProducaoResource {

    private final ProducaoUseCases useCases;
    private final ProducaoMapper mapper;

    public ProducaoController(ProducaoUseCases useCases, ProducaoMapper mapper) {
        this.useCases = useCases;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Object> iniciarPreparo(Long id) {
        Producao producao = useCases.iniciarProducao(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.paraIniciarPreparoDTO(producao));
    }

    @Override
    public ResponseEntity<Object> finalizarPreparo(Long id) {
        Producao producao = useCases.finalizarProducao(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.paraFinalizarPreparoDTO(producao));
    }
}