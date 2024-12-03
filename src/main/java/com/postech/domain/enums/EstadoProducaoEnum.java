package com.postech.domain.enums;

public enum EstadoProducaoEnum {

    RECEBIDO(1),
    PREPARANDO(2),
    PRONTO(3);

    final Integer ordem;

    EstadoProducaoEnum(Integer ordem) {
        this.ordem = ordem;
    }

}