package com.example.sos.api.model.storage;

import com.example.sos.api.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        }
        catch (Exception ex) {
            throw new FileStorageException("Não foi possível criar o diretório.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("O nome do arquivo é inválido " + fileName);
            }

            if (!validaExtensaoArquivo(fileName, Arrays.asList("PDF"))) {
                throw new FileStorageException("Tipo de arquivo não suportado " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        }
        catch (IOException ex) {
            throw new FileStorageException("Não foi possível armazenar o arquivo.", ex);
        }
    }

    public static boolean validaExtensaoArquivo(String nomaArquivo, List<String> extensoesValidas) {
        String[] split = nomaArquivo.replaceAll("\\s+", "").split("\\.");
        String extensao = split[split.length - 1].toUpperCase();
        if (!extensoesValidas.contains(extensao)) {
            throw new RuntimeException(
                    "O arquivo deve conter as extensões: " + extensoesValidas.stream().collect(Collectors.joining(",")));
        }
        return true;
    }
}