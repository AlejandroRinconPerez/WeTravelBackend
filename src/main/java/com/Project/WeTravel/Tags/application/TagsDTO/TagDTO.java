
package com.Project.WeTravel.Tags.application.TagsDTO;




public class TagDTO {
    
    private String content;
    private Long count; 

    public TagDTO() {
    }
    
     public TagDTO(String content, Long count) {
        this.content = content;
        this.count = count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TagDTO{" + "content=" + content + ", count=" + count + '}';
    }
    
    
    
    
}
