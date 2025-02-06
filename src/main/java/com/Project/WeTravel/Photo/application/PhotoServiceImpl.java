package com.Project.WeTravel.Photo.application;

import com.Project.WeTravel.Photo.application.DTO.PhotoDTOurl;
import com.Project.WeTravel.Photo.domain.Photo;
import com.Project.WeTravel.Photo.infrastructure.PhotoJpaRepository;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Users.application.UserServiceImpl;
import com.Project.WeTravel.Users.domain.Users;
import com.Project.WeTravel.Utilities.exceptions.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoJpaRepository photoJpaRepository;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public PhotoServiceImpl(PhotoJpaRepository photoJpaRepository, UserServiceImpl userServiceImpl) {
        this.photoJpaRepository = photoJpaRepository;
        this.userServiceImpl = userServiceImpl;
    }
    
 
 
   
 

    @Override
    public List<PhotoDTOurl> findByPost(Post Post) {
        List<Photo> photoList = photoJpaRepository.findAllByPost(Post);
        return photoList.stream()
                .map(Photo::toPhotoDTOurl)
                .collect(Collectors.toList());

    }
public List<PhotoDTOurl> allPhotobyUser(String email) {
    Users user = userServiceImpl.fingUserbyEmail(email).getBody();
    if (user == null) {
        throw new NotFoundException("User not found with email: " + email);
    }
    List<Photo> photos = photoJpaRepository.findAllByUser(user);
    List<PhotoDTOurl> photoDTOs = new ArrayList<>();
    for (Photo item : photos) {
        photoDTOs.add(item.toPhotoDTOurl());
    }
    return photoDTOs;
}



}
