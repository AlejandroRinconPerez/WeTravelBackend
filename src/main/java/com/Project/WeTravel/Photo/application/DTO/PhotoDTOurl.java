
package com.Project.WeTravel.Photo.application.DTO;


public class PhotoDTOurl {
    
    private String url;
    private Long idPost; 

    public PhotoDTOurl() {
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }
    
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PhotoDTOurl{" + "url=" + url + '}';
    }
    
    
    
    
}
