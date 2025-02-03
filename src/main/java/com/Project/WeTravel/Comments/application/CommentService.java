
package com.Project.WeTravel.Comments.application;

import com.Project.WeTravel.Comments.application.DTO.CommentDTO;
import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Post.domain.Post;
import java.util.List;


public interface CommentService {
    
    
     List<CommentDTO> findAllByPost(Post post);
    
}
