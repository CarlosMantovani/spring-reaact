package mantovani.dev.imageliteapi.Service;

import lombok.RequiredArgsConstructor;
import mantovani.dev.imageliteapi.entity.ImageEntity;
import mantovani.dev.imageliteapi.entity.enums.ImageExtesion;
import mantovani.dev.imageliteapi.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    @Transactional
    public ImageEntity create(ImageEntity imageEntity){
        return imageRepository.save(imageEntity);
    }

    public Optional<ImageEntity> getById(String id){
        return imageRepository.findById(id);
    }

    public List<ImageEntity> search(ImageExtesion extesion, String query){
        return imageRepository.findByExtensioAndNameOrTagsLike(extesion, query);
    }
}
