package com.example.sos.api.resources;

import com.example.sos.api.dto.MarcaDTO;
import com.example.sos.api.dto.PatrimonioDTO;
import com.example.sos.api.services.MarcaService;
import com.example.sos.api.services.PatrimonioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "marcas")
public class MarcaResource {
    private final MarcaService marcaService;
    private final PatrimonioService patrimonioService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MarcaDTO>> obtemMarcas(@RequestParam(defaultValue = "0", name = "page") int page,
                                                      @RequestParam(defaultValue = "1000", name = "size") int size) {
        Page<MarcaDTO> marcaDTOS = marcaService.fetchAllWithPaginate(page, size);
        return new ResponseEntity<>(marcaDTOS.getContent(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarcaDTO> obtemMarca(@PathVariable("id") Long id) {
        MarcaDTO marcaDTO = marcaService.findById(id);
        return new ResponseEntity<>(marcaDTO, HttpStatus.FOUND);
    }

    @GetMapping(value = "/{nome}/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> buscarPorNome(@PathVariable("nome") String nome) {
        MarcaDTO marcaDTO = marcaService.findByNome(nome);
        return new ResponseEntity<>(marcaDTO.getNome(), HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}/patrimonios", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatrimonioDTO>> obtemPatrimoniosMarca(@PathVariable("id") Long id,
                                                                     @RequestParam(defaultValue = "0", name = "page") int page,
                                                                     @RequestParam(defaultValue = "10", name = "size") int size) {
        MarcaDTO marcaDTO = marcaService.findById(id);
        Page<PatrimonioDTO> patrimonioDTOS = patrimonioService.fetchAllByMarcaWithPaginate(marcaDTO, page, size);
        return new ResponseEntity<>(patrimonioDTOS.getContent(), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarcaDTO> save(@RequestBody MarcaDTO marcaDTO) {
        MarcaDTO save = marcaService.save(marcaDTO);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateM(@RequestBody MarcaDTO marcaDTO, @PathVariable("id") Long id) {
        marcaService.update(marcaDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteM(@PathVariable("id") Long id) {
        marcaService.delete(id);
    }
}
