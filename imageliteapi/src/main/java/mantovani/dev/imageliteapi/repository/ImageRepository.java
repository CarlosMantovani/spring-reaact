package mantovani.dev.imageliteapi.repository;

import mantovani.dev.imageliteapi.entity.ImageEntity;
import mantovani.dev.imageliteapi.entity.enums.ImageExtesion;

import mantovani.dev.imageliteapi.repository.specs.GenericSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import java.util.List;

import static mantovani.dev.imageliteapi.repository.specs.GenericSpecs.conjunction;
import static mantovani.dev.imageliteapi.repository.specs.ImageSpecs.*;
import static org.springframework.data.jpa.domain.Specification.*;


public interface ImageRepository extends JpaRepository<ImageEntity, String>, JpaSpecificationExecutor<ImageEntity> {

    default List<ImageEntity> findByExtensioAndNameOrTagsLike(ImageExtesion extension, String query) {

        Specification<ImageEntity> specification = where(conjunction());

        if (extension != null) {

            specification= specification.and(extesionEqual(extension));
        }

        if(StringUtils.hasText(query)){

            specification = specification.and(anyOf(nameLike(query), tagsLike(query)));

        }
        return findAll(specification);
    }
}