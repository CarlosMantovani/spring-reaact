package mantovani.dev.imageliteapi.repository;

import mantovani.dev.imageliteapi.entity.ImageEntity;
import mantovani.dev.imageliteapi.entity.enums.ImageExtesion;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import java.util.List;


public interface ImageRepository extends JpaRepository<ImageEntity, String>, JpaSpecificationExecutor<ImageEntity> {

    default List<ImageEntity> findByExtensioAndNameOrTagsLike(ImageExtesion extesion, String query) {

        Specification<ImageEntity> conjunction = (root, q, criteriaBuilder) -> criteriaBuilder.conjunction();
        Specification<ImageEntity> specification = Specification.where(conjunction);

        if (extesion != null) {
            Specification<ImageEntity> extensionEqual = (root, query1, cb) -> cb.equal(root.get("extension"), extesion );
            specification= specification.and(extensionEqual);
        }

        if(StringUtils.hasText(query)){
            Specification<ImageEntity> nameLike = (root, q, cb) -> cb.like(cb.upper(root.get("name")),"%" + query.toUpperCase()+"%");
            Specification<ImageEntity> tagsLike = (root, q, cb) -> cb.like(cb.upper(root.get("tags")),"%" + query.toUpperCase()+"%");

            Specification<ImageEntity> namerOrTagsLike = Specification.anyOf(nameLike, tagsLike);

            specification = specification.and(namerOrTagsLike);

        }
        return findAll(specification);
    }
}