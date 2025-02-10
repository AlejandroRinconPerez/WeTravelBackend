
package com.Project.WeTravel.CombinePost;

import com.Project.WeTravel.Post.application.PostServiceImpl;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Post.infrastructure.PostJpaRepository;
import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import com.Project.WeTravel.Users.domain.Users;
import com.Project.WeTravel.Users.infrastructure.UserJpaRepositorty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class SearchService {
    
   @Autowired
   private final PostJpaRepository postJpaRepository;
   private final UserJpaRepositorty userJpaRepositorty;
   private final PostServiceImpl postServiceImpl;

    public SearchService(PostJpaRepository postJpaRepository, UserJpaRepositorty userJpaRepositorty, PostServiceImpl postServiceImpl) {
        this.postJpaRepository = postJpaRepository;
        this.userJpaRepositorty = userJpaRepositorty;
        this.postServiceImpl = postServiceImpl;
    }
   
   
   
public List<CombinePostDTO> searchingPost(String query) {
    List<Post> listaPostFinal = new ArrayList<>();

    
    List<Post> postListDescription = postJpaRepository.findPostsByDescriptionContainingIgnoreCase(query);
    System.out.println("Posts by Description: " + postListDescription.size());

    List<Post> postListTag = postJpaRepository.findPostsByTagContentContainingIgnoreCase(query);
    System.out.println("Posts by Tag: " + postListTag.size());

    List<Post> postList = postJpaRepository.findPostsByUserNameContainingIgnoreCase(query);
    System.out.println("Posts by UserName: " + postList.size());


    listaPostFinal.addAll(postList);
    listaPostFinal.addAll(postListDescription);
    listaPostFinal.addAll(postListTag);

    System.out.println("Total Posts Combined: " + listaPostFinal.size());


    List<CombinePostDTO> listaSearchPost = postServiceImpl.getAllPosts2(listaPostFinal);
    System.out.println("Combined Post DTOs: " + listaSearchPost.size());

    return listaSearchPost;
}

public List<UsersDTO> searchingUser(String query) {
    List<Users> usersByEmail = userJpaRepositorty.findUsersByEmailContainingIgnoreCase(query);
    List<Users> usersByUserName = userJpaRepositorty.findUsersByUserNameContainingIgnoreCase(query);

    List<Users> combinedUsers = new ArrayList<>();
    combinedUsers.addAll(usersByEmail);
    combinedUsers.addAll(usersByUserName);

    System.out.println("Users by Email: " + usersByEmail.size());
    System.out.println("Users by UserName: " + usersByUserName.size());

   
    List<UsersDTO> usersDTOList = combinedUsers.stream()
            .map(user -> user.toDTO()) 
            .collect(Collectors.toList());

    System.out.println("Combined Users DTOs: " + usersDTOList.size());

    return usersDTOList;
}

public SearchClass searchAll(String query) {
    SearchClass searchClass = new SearchClass();

    
    searchClass.setCombinePostDTOList(searchingPost(query));

    
    searchClass.setUsersDTOList(searchingUser(query));

    System.out.println("Search Results Combined: ");
    System.out.println("Post DTOs: " + searchClass.getCombinePostDTOList().size());
    System.out.println("User DTOs: " + searchClass.getUsersDTOList().size());

    return searchClass;
}



    
    
}
