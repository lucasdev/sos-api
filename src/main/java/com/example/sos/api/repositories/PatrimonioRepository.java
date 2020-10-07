package com.example.sos.api.repositories;

import com.example.sos.api.entities.Marca;
import com.example.sos.api.entities.Patrimonio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, Long> {
    Page<Patrimonio> findAll(Pageable pageable);
    Page<Patrimonio> findAllByMarca(Marca marca, Pageable pageable);
    Optional<Patrimonio> findById(Long id);
}
