package mantovani.dev.imageliteapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mantovani.dev.imageliteapi.Mapper.ImageMapper;
import mantovani.dev.imageliteapi.Service.ImageService;
import mantovani.dev.imageliteapi.entity.ImageEntity;
import mantovani.dev.imageliteapi.entity.enums.ImageExtesion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImagesController {

    private final ImageService imageService;
    private final ImageMapper mapper;

    @PostMapping
    public ResponseEntity save(@RequestParam("file") MultipartFile file,
                               @RequestParam("name") String name,
                               @RequestParam("tags") List<String> tags) throws IOException {
        log.info("Imagem Recebida: name:{}, size: {}", file.getOriginalFilename(), file.getSize());


        MediaType.valueOf(file.getContentType());

        ImageEntity imageEntity = mapper.mapToImage(file, name, tags);
        ImageEntity savedImage = imageService.create(imageEntity);
        URI imageUri = buildImageUrl(savedImage);

        return ResponseEntity.created(imageUri).build();
    }
    @GetMapping("{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id){
       var possibleImage =  imageService.getById(id);
       if(possibleImage.isEmpty()){
           return ResponseEntity.notFound().build();
       }
        var image = possibleImage.get();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(image.getExtesion().getMediaType());
        headers.setContentLength(image.getSize());
        headers.setContentDispositionFormData("inline; filename= \"" +image.getFileName()+ "\"", image.getFileName());

        return new ResponseEntity<>(image.getFile(), headers, HttpStatus.OK);
    }
    private URI buildImageUrl(ImageEntity imageo) {
        String imagePath = "/" + imageo.getId();
        return ServletUriComponentsBuilder.fromCurrentRequest().path(imagePath).build().toUri();
    }
}