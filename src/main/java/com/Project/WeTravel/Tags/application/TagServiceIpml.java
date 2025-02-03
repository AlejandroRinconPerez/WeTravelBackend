package com.Project.WeTravel.Tags.application;

import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Tags.application.TagsDTO.TagDTO;
import com.Project.WeTravel.Tags.domain.Tag;
import com.Project.WeTravel.Tags.infrastructure.TagJpaRepository;
import com.Project.WeTravel.Utilities.exceptions.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class TagServiceIpml implements TagService {

    private final TagJpaRepository tagJpaRepository;

    @Autowired
    public TagServiceIpml(TagJpaRepository tagJpaRepository) {
        this.tagJpaRepository = tagJpaRepository;
    }

    @Override
    public List<TagDTO> getAllTagsDTO() {

        return tagJpaRepository.findTagUsageCount();
    }

    @Override
    public List<Tag> getAllTags() {
        return tagJpaRepository.findAll();
    }

    @Override
    public Optional<Tag> findBytagContent(String tag) {

        Optional<Tag> tagOpt = tagJpaRepository.findBytagContent(tag);
        if (tagOpt.isPresent()) {
            return tagOpt;
        } else {
            throw new NotFoundException("Name Tag Empty ");
        }
    }

    @Override
    public ResponseEntity<Tag> createTag(Tag tag) {
       
        if(!tagJpaRepository.existsBytagContent(tag.getTagContent())){
            Tag tagsaved = tagJpaRepository.save(tag);
            return ResponseEntity.ok(tagsaved);
        }
        return ResponseEntity.badRequest().build();
    
        
    }

    @Override
    public ResponseEntity<Void> deleteTag(Long tag) {
        Optional<Tag> tagToEliminate = tagJpaRepository.findById(tag);
        if (tagToEliminate.isPresent()) {
            tagJpaRepository.delete(tagToEliminate.get());
            return ResponseEntity.ok().build();
        } else {
            throw new NotFoundException("Tag Id not present try agian");

        }
    }

    
    public List<Tag> getAllTagsByPost(Post post ){
      return tagJpaRepository.findAllByPostList(post);
    }
    
    @Override

    public Boolean verificarTag(String tag) {
        return tagJpaRepository.existsBytagContent(tag);
    }

}
