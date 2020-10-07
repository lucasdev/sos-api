package com.example.sos.api.repositories;

import com.example.sos.api.entities.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Page<Marca> findAll(Pageable pageable);
    Optional<Marca> findById(Long id);

    @Modifying
    @Query("update Marca m set m.nome = :nome where m.id = :id")
    void update(@Param("nome") String nome, @Param("id") Long id);

    @Query("select m from Marca m where m.nome = :nome")
    Optional<Marca> findByNome(@Param("nome") String nome);
}
