package mantovani.dev.imageliteapi.entity.enums;

import org.springframework.http.MediaType;

import java.util.Arrays;

public enum ImageExtesion {
    PNG(MediaType.IMAGE_PNG),
    GIF(MediaType.IMAGE_GIF),
    JPEG(MediaType.IMAGE_JPEG);

    private MediaType mediaType;

    ImageExtesion(MediaType mediaType){
        this.mediaType = mediaType;
    }

    public static ImageExtesion valueOf(MediaType mediaType){
        return Arrays.stream(values())
                .filter(ie -> ie.mediaType.equals(mediaType))
                .findFirst()
                .orElse(null);
    }

}
