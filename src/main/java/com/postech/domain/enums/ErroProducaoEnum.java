package com.postech.domain.enums;

public enum ErroProducaoEnum {
    PREPARO_INVALIDO(400, "Pedido já se encontra em status de preparação ou já foi finalizado"),
    PREPARACAO_NAO_INICIADA(400, "Pedido não iniciou a preparação"),
    PREPARACAO_JA_FINALIZADA(400, "Preparação do pedido já foi finalizada");

    private final Integer httpStatusCode;
    private final String detalhe;

    ErroProducaoEnum(Integer httpStatusCode, String detalhe) {
        this.httpStatusCode = httpStatusCode;
        this.detalhe = detalhe;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getDetalhe() {
        return detalhe;
    }
}
