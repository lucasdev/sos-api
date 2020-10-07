package com.example.sos.api.repositories;

import com.example.sos.api.entities.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
    @Query("select a from Arquivo a order by a.id desc")
    List<Arquivo> findArquivos();
}
