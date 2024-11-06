package mantovani.dev.imageliteapi.Mapper;

import mantovani.dev.imageliteapi.entity.ImageEntity;
import mantovani.dev.imageliteapi.entity.enums.ImageExtesion;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class ImageMapper {

    public ImageEntity mapToImage(MultipartFile file, String name, List<String> tags) throws IOException {
       return ImageEntity.builder().name(name).tags(String.join(",", tags))
                .size(file.getSize()).extesion(ImageExtesion.valueOf(MediaType.valueOf(file.getContentType())))
                .file(file.getBytes()).build();

    }


}
