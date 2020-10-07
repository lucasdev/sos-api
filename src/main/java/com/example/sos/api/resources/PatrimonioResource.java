package com.example.sos.api.resources;

import com.example.sos.api.dto.PatrimonioDTO;
import com.example.sos.api.services.PatrimonioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
• GET patrimonios - Obter todos os patrimônios
• GET patrimonios/{id} - Obter um patrimônio por ID
• POST patrimonios - Inserir um novo patrimônio
• PUT patrimonios/{id} - Alterar os dados de um patrimônio
• DELETE patrimonios/{id} - Excluir um patrimônio
*/

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "patrimonios")
public class PatrimonioResource {
    private final PatrimonioService patrimonioService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatrimonioDTO>> obtemPatrimonios(@RequestParam(defaultValue = "0", name = "page") int page,
                                                                @RequestParam(defaultValue = "10", name = "size") int size) {
        Page<PatrimonioDTO> patrimonioDTOS = patrimonioService.fetchAllWithPaginate(page, size);
        return new ResponseEntity<>(patrimonioDTOS.getContent(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatrimonioDTO> obtemPatrimonio(@PathVariable("id") Long id) {
        PatrimonioDTO patrimonioDTO = patrimonioService.findById(id);
        return new ResponseEntity<>(patrimonioDTO, HttpStatus.FOUND);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatrimonioDTO> save(@RequestBody PatrimonioDTO patrimonioDTO) {
        PatrimonioDTO save = patrimonioService.save(patrimonioDTO);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateP(@RequestBody PatrimonioDTO patrimonioDTO, @PathVariable("id") Long id) {
        patrimonioService.update(patrimonioDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteP(@PathVariable("id") Long id) {
        patrimonioService.delete(id);
    }
}
