
package com.Project.WeTravel.Comments.application.DTO;





import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Likes.application.DTO.LikeCommentDTO;
import com.Project.WeTravel.Likes.domain.Likes;
import com.Project.WeTravel.Users.domain.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDTO {

    private Long idComment;
    private String content;
    private Date createDate;
    private String userName; 
    private String userProfilePhoto; 
        private List<LikeCommentDTO> likes = new ArrayList<>(); 

    // Getters y Setters

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfilePhoto() {
        return userProfilePhoto;
    }

    public void setUserProfilePhoto(String userProfilePhoto) {
        this.userProfilePhoto = userProfilePhoto;
    }

    public List<LikeCommentDTO> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeCommentDTO> likes) {
        this.likes = likes;
    }


//
//    // Método fromDTO
//    public static Comment fromDTO(CommentDTO commentDTO) {
//        Comment comment = new Comment();
//        comment.setIdComment(commentDTO.getIdComment());
//        comment.setContent(commentDTO.getContent());
//        comment.setCreateDate(commentDTO.getCreateDate());
//
//        Users user = new Users();
//        user.setName(commentDTO.getUserName());
//        user.setPhoto(commentDTO.getUserProfilePhoto());
//        comment.setUser(user);
//
//        // Aquí solo se configura la lista de likes para después ser usada
//        List<Likes> likes = new ArrayList<>();
//        for (LikeCommentDTO likeCommentDTO : commentDTO.getLikes()) {
//            Likes like = Likes.fromLikeCommentDTO(likeCommentDTO);
//            like.setComment(comment); 
//            likes.add(like);
//        }
//        comment.setLikeList(likes);
//
//        return comment;
//    }
    
    
    @Override
    public String toString() {
        return "CommentDTO{" +
                "idComment=" + idComment +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", userName='" + userName + '\'' +
                ", userProfilePhoto='" + userProfilePhoto + '\'' +
                ", likes=" + likes +
                '}';
    }
}
