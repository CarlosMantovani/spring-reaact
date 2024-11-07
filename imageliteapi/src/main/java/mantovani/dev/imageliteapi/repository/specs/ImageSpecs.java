package mantovani.dev.imageliteapi.repository.specs;

import mantovani.dev.imageliteapi.entity.ImageEntity;
import mantovani.dev.imageliteapi.entity.enums.ImageExtesion;
import org.springframework.data.jpa.domain.Specification;

public class ImageSpecs {

    private ImageSpecs(){

    }
    public static Specification<ImageEntity> extesionEqual(ImageExtesion extension){
        return  (root, query1, cb) -> cb.equal(root.get("extension"), extension );
    }

    public static Specification<ImageEntity> nameLike(String name ){
       return  (root, q, cb) -> cb.like(cb.upper(root.get("name")),"%" + name.toUpperCase()+"%");
    }
    public static Specification<ImageEntity> tagsLike(String tags ){
        return  (root, q, cb) -> cb.like(cb.upper(root.get("tags")),"%" + tags.toUpperCase()+"%");
    }

}
