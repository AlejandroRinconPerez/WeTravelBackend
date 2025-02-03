package com.Project.WeTravel.Tags.infrastructure;

import com.Project.WeTravel.Tags.application.TagServiceIpml;
import com.Project.WeTravel.Tags.application.TagsDTO.TagDTO;
import com.Project.WeTravel.Tags.domain.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    private final TagServiceIpml tagServiceIpml;

    @Autowired
    public TagController(TagServiceIpml tagServiceIpml) {
        this.tagServiceIpml = tagServiceIpml;
    }

    @GetMapping("/tagDTO")
    public List<TagDTO> getAllTagsDTO() {
        return tagServiceIpml.getAllTagsDTO();

    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagServiceIpml.getAllTags();

    }

    @GetMapping("/{tag}")
    public Optional<Tag> findBytagContent(@PathVariable String tag) {
        return tagServiceIpml.findBytagContent(tag);

    }
   
    @PostMapping
public ResponseEntity<Tag> createTag(@RequestBody  Tag tag) {
    if (tag.getTagContent() == null || tag.getTagContent().isEmpty()) {
        return ResponseEntity.badRequest().body(null);
    }
    return tagServiceIpml.createTag(tag);
}

    
    @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        return tagServiceIpml.deleteTag(id);
    }
    
}
