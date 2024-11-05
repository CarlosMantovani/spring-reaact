package mantovani.dev.imageliteapi.repository;

import mantovani.dev.imageliteapi.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

}
