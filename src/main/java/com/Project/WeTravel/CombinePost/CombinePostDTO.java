package com.Project.WeTravel.CombinePost;



import com.Project.WeTravel.Comments.application.DTO.CommentDTO;
import com.Project.WeTravel.Likes.application.DTO.LikePostDTO;
import com.Project.WeTravel.Photo.application.DTO.PhotoDTOurl;
import com.Project.WeTravel.Post.application.DTO.DTO.ShowPostDTO;
import com.Project.WeTravel.Tags.domain.Tag;

import java.util.ArrayList;
import java.util.List;

public class CombinePostDTO {

    private ShowPostDTO showPostDTO;
    private List<PhotoDTOurl> photoDTOurl;
    private List<Tag> tagDTO;
    private List<LikePostDTO> likePostDTO;
    private List<CommentDTO> commentDTO; 

    public CombinePostDTO() {
        this.photoDTOurl = new ArrayList<>();
        this.tagDTO = new ArrayList<>();
        this.likePostDTO = new ArrayList<>();
        this.commentDTO = new ArrayList<>();
    }

    public CombinePostDTO(ShowPostDTO showPostDTO, List<PhotoDTOurl> photoDTOurl, List<Tag> tagDTO,
                          List<LikePostDTO> likePostDTO, List<CommentDTO> commentDTO) {
        this.showPostDTO = showPostDTO;
        this.photoDTOurl = photoDTOurl;
        this.tagDTO = tagDTO;
        this.likePostDTO = likePostDTO;
        this.commentDTO = commentDTO;
    }



    public ShowPostDTO getShowPostDTO() {
        return showPostDTO;
    }

    public void setShowPostDTO(ShowPostDTO showPostDTO) {
        this.showPostDTO = showPostDTO;
    }

    public List<PhotoDTOurl> getPhotoDTOurl() {
        return photoDTOurl;
    }

    public void setPhotoDTOurl(List<PhotoDTOurl> photoDTOurl) {
        this.photoDTOurl = photoDTOurl;
    }

    public void addPhotoDTOurl(PhotoDTOurl photo) {
        this.photoDTOurl.add(photo);
    }

    public void removePhotoDTOurl(PhotoDTOurl photo) {
        this.photoDTOurl.remove(photo);
    }

    public List<Tag> getTagDTO() {
        return tagDTO;
    }

    public void setTagDTO(List<Tag> tagDTO) {
        this.tagDTO = tagDTO;
    }

    public void addTagDTO(Tag tag) {
        this.tagDTO.add(tag);
    }

    public void removeTagDTO(Tag tag) {
        this.tagDTO.remove(tag);
    }

    public List<LikePostDTO> getLikePostDTO() {
        return likePostDTO;
    }

    public void setLikePostDTO(List<LikePostDTO> likePostDTO) {
        this.likePostDTO = likePostDTO;
    }

    public void addLikePostDTO(LikePostDTO likePost) {
        this.likePostDTO.add(likePost);
    }

    public void removeLikePostDTO(LikePostDTO likePost) {
        this.likePostDTO.remove(likePost);
    }

    public List<CommentDTO> getCommentDTO() {
        return commentDTO;
    }

    public void setCommentDTO(List<CommentDTO> commentDTO) {
        this.commentDTO = commentDTO;
    }

    public void addCommentDTO(CommentDTO comment) {
        this.commentDTO.add(comment);
    }

    public void removeCommentDTO(CommentDTO comment) {
        this.commentDTO.remove(comment);
    }

    @Override
    public String toString() {
        return "CombinePostDTO{" +
                "showPostDTO=" + showPostDTO +
                ", photoDTOurl=" + photoDTOurl +
                ", tagDTO=" + tagDTO +
                ", likePostDTO=" + likePostDTO +
                ", commentDTO=" + commentDTO +
                '}';
    }
}
