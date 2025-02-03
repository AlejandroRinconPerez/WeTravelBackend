package com.Project.WeTravel.Photo.application;

import com.Project.WeTravel.Photo.application.DTO.PhotoDTOurl;
import com.Project.WeTravel.Photo.domain.Photo;
import com.Project.WeTravel.Photo.infrastructure.PhotoJpaRepository;
import com.Project.WeTravel.Post.domain.Post;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoJpaRepository photoJpaRepository;

    @Autowired
    public PhotoServiceImpl(PhotoJpaRepository photoJpaRepository) {
        this.photoJpaRepository = photoJpaRepository;
    }

    @Override
    public List<PhotoDTOurl> findByPost(Post Post) {
        List<Photo> photoList = photoJpaRepository.findAllByPost(Post);
        return photoList.stream()
                .map(Photo::toPhotoDTOurl)
                .collect(Collectors.toList());

    }

}
