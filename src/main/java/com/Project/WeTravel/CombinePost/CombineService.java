/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Project.WeTravel.CombinePost;

import com.Project.WeTravel.Comments.application.CommentServicesImpl;
import com.Project.WeTravel.Likes.application.LikeServiceImpl;
import com.Project.WeTravel.Photo.application.DTO.PhotoDTOurl;
import com.Project.WeTravel.Photo.application.PhotoServiceImpl;
import com.Project.WeTravel.Post.application.DTO.DTO.ShowPostDTO;
import com.Project.WeTravel.Post.application.PostServiceImpl;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Tags.application.TagServiceIpml;
import com.Project.WeTravel.Users.application.UserServiceImpl;
import com.Project.WeTravel.Users.domain.Users;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CombineService {
    
    private final PostServiceImpl postServiceImpl;
    private final PhotoServiceImpl photoServiceImpl;
    private final TagServiceIpml tagServiceIpml;
    private final UserServiceImpl userServiceImpl;
    private final LikeServiceImpl likeServiceImpl;
    private final CommentServicesImpl commentServicesImpl;
    
    
    @Autowired
    public CombineService(PostServiceImpl postServiceImpl, PhotoServiceImpl photoServiceImpl, TagServiceIpml tagServiceIpml, UserServiceImpl userServiceImpl, @Lazy LikeServiceImpl likeServiceImpl,@Lazy CommentServicesImpl commentServicesImpl) {
        this.postServiceImpl = postServiceImpl;
        this.photoServiceImpl = photoServiceImpl;
        this.tagServiceIpml = tagServiceIpml;
        this.userServiceImpl = userServiceImpl;
        this.likeServiceImpl = likeServiceImpl;
        this.commentServicesImpl = commentServicesImpl;
    }
    
    public List<CombinePostDTO> findAllCombineDTO() {
        // lista para el retur final del metodo 
        List<CombinePostDTO> listPostFinal = new ArrayList();

        // Lista de todos los post en el sistema estos no tienen niu like ni comments ni tag 
        List<ShowPostDTO> postonlylist = postServiceImpl.getAllPosts();

        //  iteramos sobre cada elemento de la lista de los post sin elementos 
        for (int i = 0; i < postonlylist.size(); i++) {
            // declaramos en cada iteracion un objeto combinado     
            CombinePostDTO combinePostDTO = new CombinePostDTO();

            // le asigamos un ShowpostDTO  a ese objeto combiando 
            combinePostDTO.setShowPostDTO(postonlylist.get(i));
            // de cada DTOPost saco el Post id 
            Long idPost = postonlylist.get(i).getPostid();

            // Debo encontrar cada Post por id 
            Post postFound = postServiceImpl.getPostsByPostid(idPost);

            // aca post tiene una lista de fotos debo sacarla 
            combinePostDTO.setPhotoDTOurl(photoServiceImpl.findByPost(postFound));
            // traigo la lista de tags por post 
            combinePostDTO.setTagDTO(tagServiceIpml.getAllTagsByPost(postFound));

            // Ahora traigo la lista de likes por  cada post 
            combinePostDTO.setLikePostDTO(likeServiceImpl.findAllByPost(postFound));
            
            combinePostDTO.setCommentDTO(commentServicesImpl.findAllByPost(postFound));
            
            listPostFinal.add(combinePostDTO);
        }
        return listPostFinal;
    }    
    
      public List<CombinePostDTO> findAllCombineDTObyUserid(  Long iduser) {
         
        // lista para el retur final del metodo 
        List<CombinePostDTO> listPostFinal = new ArrayList();
          

        // Lista de todos los post en el sistema estos no tienen niu like ni comments ni tag 
        List<ShowPostDTO> postonlylist = postServiceImpl.getPostsByUserId(iduser);

        //  iteramos sobre cada elemento de la lista de los post sin elementos 
        for (int i = 0; i < postonlylist.size(); i++) {
            // declaramos en cada iteracion un objeto combinado     
            CombinePostDTO combinePostDTO = new CombinePostDTO();

            // le asigamos un ShowpostDTO  a ese objeto combiando 
            combinePostDTO.setShowPostDTO(postonlylist.get(i));
            // de cada DTOPost saco el Post id 
            Long idPost = postonlylist.get(i).getPostid();

            // Debo encontrar cada Post pore id 
            Post postFound = postServiceImpl.getPostsByPostid(idPost);

            // aca post tiene una lista de fotos debo sacarla 
            combinePostDTO.setPhotoDTOurl(photoServiceImpl.findByPost(postFound));
            // traigo la lista de tags por post 
            combinePostDTO.setTagDTO(tagServiceIpml.getAllTagsByPost(postFound));

            // Ahora traigo la lista de likes por  cada post 
            combinePostDTO.setLikePostDTO(likeServiceImpl.findAllByPost(postFound));
            
            combinePostDTO.setCommentDTO(commentServicesImpl.findAllByPost(postFound));
            
            listPostFinal.add(combinePostDTO);
        }
        return listPostFinal;
    }    
    
    
        public List<CombinePostDTO> findAllCombineDTOFollow(Long idUser) {
        
            Users user = userServiceImpl.getUserNormalbyId(idUser);
            user.getFollowedList();
            
            
            
            
            
        List<CombinePostDTO> listPostFinal = new ArrayList();

        // Lista de todos los post en el sistema estos no tienen niu like ni comments ni tag 
        List<ShowPostDTO> postonlylist = postServiceImpl.getAllPosts();

        //  iteramos sobre cada elemento de la lista de los post sin elementos 
        for (int i = 0; i < postonlylist.size(); i++) {
            // declaramos en cada iteracion un objeto combinado     
            CombinePostDTO combinePostDTO = new CombinePostDTO();

            // le asigamos un ShowpostDTO  a ese objeto combiando 
            combinePostDTO.setShowPostDTO(postonlylist.get(i));
            // de cada DTOPost saco el Post id 
            Long idPost = postonlylist.get(i).getPostid();

            // Debo encontrar cada Post pore id 
            Post postFound = postServiceImpl.getPostsByPostid(idPost);

            // aca post tiene una lista de fotos debo sacarla 
            combinePostDTO.setPhotoDTOurl(photoServiceImpl.findByPost(postFound));
            // traigo la lista de tags por post 
            combinePostDTO.setTagDTO(tagServiceIpml.getAllTagsByPost(postFound));

            // Ahora traigo la lista de likes por  cada post 
            combinePostDTO.setLikePostDTO(likeServiceImpl.findAllByPost(postFound));
            
            combinePostDTO.setCommentDTO(commentServicesImpl.findAllByPost(postFound));
            
            listPostFinal.add(combinePostDTO);
        }
        return listPostFinal;
    }    
    
        
        
        
    
}
