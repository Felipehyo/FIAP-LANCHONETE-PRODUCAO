package com.postech.domain.entities;

import com.postech.domain.enums.EstadoProducaoEnum;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Producao {

    private Long id;
    private Long idPedido;
    private EstadoProducaoEnum status;
    private LocalDateTime inicioPreparo;
    private LocalDateTime fimPreparo;
    private LocalTime tempoTotalPreparo;

    public Producao(Long idPedido, LocalDateTime inicioPreparo) {
        this.idPedido = idPedido;
        this.status = EstadoProducaoEnum.PREPARANDO;
        this.inicioPreparo = inicioPreparo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public EstadoProducaoEnum getStatus() {
        return status;
    }

    public void setStatus(EstadoProducaoEnum status) {
        this.status = status;
    }

    public LocalDateTime getInicioPreparo() {
        return inicioPreparo;
    }

    public void setInicioPreparo(LocalDateTime inicioPreparo) {
        this.inicioPreparo = inicioPreparo;
    }

    public LocalDateTime getFimPreparo() {
        return fimPreparo;
    }

    public void setFimPreparo(LocalDateTime fimPreparo) {
        this.fimPreparo = fimPreparo;
    }

    public LocalTime getTempoTotalPreparo() {
        return tempoTotalPreparo;
    }

    public void setTempoTotalPreparo(LocalTime tempoTotalPreparo) {
        this.tempoTotalPreparo = tempoTotalPreparo;
    }
}
