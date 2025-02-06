
package com.Project.WeTravel.Photo.infrastructure;

import com.Project.WeTravel.Photo.application.DTO.PhotoDTOurl;
import com.Project.WeTravel.Photo.application.PhotoServiceImpl;
import com.Project.WeTravel.Post.application.DTO.DTO.ShowPostDTO;
import com.Project.WeTravel.Post.application.PostServiceImpl;
import com.Project.WeTravel.Post.domain.Post;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/photo")
public class PhotoController {
    
    private final PhotoServiceImpl photoServiceImpl;
    private final PostServiceImpl postServiceImpl;

    
   @Autowired
   public PhotoController(PhotoServiceImpl photoServiceImpl, PostServiceImpl postServiceImpl) {
        this.photoServiceImpl = photoServiceImpl;
        this.postServiceImpl = postServiceImpl;
    }
    
    
    
 @GetMapping("/user/{email}")
public List<PhotoDTOurl> getPhotosByUser(@PathVariable String email) {
    return photoServiceImpl.allPhotobyUser(email);
}

       

    
    
    
    
    
}
