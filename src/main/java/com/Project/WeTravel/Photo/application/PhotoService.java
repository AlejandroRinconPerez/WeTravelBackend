

package com.Project.WeTravel.Photo.application;

import com.Project.WeTravel.Photo.application.DTO.PhotoDTOurl;
import com.Project.WeTravel.Post.domain.Post;
import java.util.List;

public interface PhotoService {

    // Encontrar fotos por post
    List<PhotoDTOurl> findByPost(Post Post);

    // Encontrar todas las fotos por usuario usando su correo electrónico
    List<PhotoDTOurl> allPhotobyUser(String email);
}
