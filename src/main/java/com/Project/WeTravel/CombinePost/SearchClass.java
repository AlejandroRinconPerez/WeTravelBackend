package com.Project.WeTravel.CombinePost;

import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import java.util.List;

public class SearchClass {

    private List<UsersDTO> usersDTOList;
    private List<CombinePostDTO> combinePostDTOList;

    // Constructor
    
      public SearchClass() {
    }

    
    
    
    
    public SearchClass(List<UsersDTO> usersDTOList, List<CombinePostDTO> combinePostDTOList) {
        this.usersDTOList = usersDTOList;
        this.combinePostDTOList = combinePostDTOList;
    }

  
    
    
    
    
    // Getters and Setters
    public List<UsersDTO> getUsersDTOList() {
        return usersDTOList;
    }

    public void setUsersDTOList(List<UsersDTO> usersDTOList) {
        this.usersDTOList = usersDTOList;
    }

    public List<CombinePostDTO> getCombinePostDTOList() {
        return combinePostDTOList;
    }

    public void setCombinePostDTOList(List<CombinePostDTO> combinePostDTOList) {
        this.combinePostDTOList = combinePostDTOList;
    }
}
