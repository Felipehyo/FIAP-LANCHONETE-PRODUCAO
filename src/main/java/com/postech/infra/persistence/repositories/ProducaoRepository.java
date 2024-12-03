package com.postech.infra.persistence.repositories;

import com.postech.infra.persistence.entities.ProducaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProducaoRepository extends JpaRepository<ProducaoEntity, Long> {
    Optional<ProducaoEntity> getProducaoEntityByIdPedido(Long idPedido);
    Optional<ProducaoEntity> getProducaoEntityById(Long id);
}
