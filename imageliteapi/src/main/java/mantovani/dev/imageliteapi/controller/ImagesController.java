package mantovani.dev.imageliteapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mantovani.dev.imageliteapi.Service.ImageService;
import mantovani.dev.imageliteapi.entity.ImageEntity;
import mantovani.dev.imageliteapi.entity.enums.ImageExtesion;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImagesController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity save(@RequestParam("file") MultipartFile file,
                               @RequestParam("name") String name,
                               @RequestParam("tags")List<String> tags) throws IOException {
        log.info("Imagem Recebida: name:{}, size: {}", file.getOriginalFilename(), file.getSize());


        MediaType.valueOf(file.getContentType());

        ImageEntity imageEntity = ImageEntity.builder().name(name).tags(String.join(",", tags))
                .size(file.getSize()).extesion(ImageExtesion.valueOf(MediaType.valueOf(file.getContentType())))
                .file(file.getBytes()).build();

        imageService.create(imageEntity);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
