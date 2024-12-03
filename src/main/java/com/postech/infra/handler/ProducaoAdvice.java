package com.postech.infra.handler;

import com.postech.domain.exceptions.ProducaoException;
import com.postech.infra.dto.ErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProducaoAdvice {

    @ExceptionHandler(value = {ProducaoException.class})
    ResponseEntity<Object> produtoExcecaoHandler(ProducaoException excecao) {
        return ResponseEntity.status(HttpStatus.valueOf(excecao.getErro().getHttpStatusCode())).body(new ErroDTO(excecao.getErro().name(), excecao.getErro().getDetalhe()));
    }

}
