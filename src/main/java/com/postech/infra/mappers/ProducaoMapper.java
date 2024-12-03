package com.postech.infra.mappers;

import com.postech.domain.entities.Producao;
import com.postech.infra.dto.response.FinalizarPreparoResponseDTO;
import com.postech.infra.dto.response.IniciarPreparoResponseDTO;
import com.postech.infra.persistence.entities.ProducaoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducaoMapper {

    ProducaoEntity paraEntidade(Producao producao);
    Producao paraDominio(ProducaoEntity clienteEntity);
    IniciarPreparoResponseDTO paraIniciarPreparoDTO(Producao producao);
    FinalizarPreparoResponseDTO paraFinalizarPreparoDTO(Producao producao);
}
