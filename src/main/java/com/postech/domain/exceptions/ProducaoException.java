package com.postech.domain.exceptions;

import com.postech.domain.enums.ErroProducaoEnum;

public class ProducaoException extends RuntimeException {

    private final ErroProducaoEnum erro;

    public ProducaoException(ErroProducaoEnum erro) {
        this.erro = erro;
    }

    public ErroProducaoEnum getErro() {
        return this.erro;
    }

}
