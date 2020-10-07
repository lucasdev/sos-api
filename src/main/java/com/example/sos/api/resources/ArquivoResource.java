package com.example.sos.api.resources;

import com.example.sos.api.entities.Arquivo;
import com.example.sos.api.model.storage.FileStorageService;
import com.example.sos.api.services.ArquivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "arquivos")
@RestController
public class ArquivoResource {
    private final FileStorageService fileStorageService;
    private final ArquivoService arquivoService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Arquivo> uploadFile(@RequestParam("upfile") MultipartFile upfile) {
        String fileName = fileStorageService.storeFile(upfile);
        final Arquivo salvar = arquivoService.salvar(fileName);
        return new ResponseEntity<>(salvar, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Arquivo>> obterTodos() {
        final List<Arquivo> arquivos = arquivoService.obterTodos();
        return new ResponseEntity<>(arquivos, HttpStatus.OK);
    }

    @GetMapping(value = "/download/{d}")
    public ResponseEntity<InputStreamResource> download(@PathVariable("d") Long id) throws FileNotFoundException {
        Arquivo arquivo = arquivoService.buscarUm(id);

        File initialFile = new File("src/main/resources/uploadfiles/"+arquivo.getPath());
        InputStream inputStream = new FileInputStream(initialFile);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename= "+arquivo.getPath());

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(inputStream));
    }
}
