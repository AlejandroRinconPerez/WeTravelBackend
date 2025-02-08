package com.Project.WeTravel.Tags.application;

import com.Project.WeTravel.Tags.application.TagsDTO.TagDTO;
import com.Project.WeTravel.Tags.domain.Tag;
import com.Project.WeTravel.Post.domain.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

public interface TagService {

    // Obtener todos los TagDTO
    List<TagDTO> getAllTagsDTO();

    // Obtener todos los Tags
    List<Tag> getAllTags();

    // Encontrar un Tag por su contenido
    Optional<Tag> findBytagContent(String tag);

    // Crear un nuevo Tag
    ResponseEntity<Tag> createTag(Tag tag);

    // Eliminar un Tag dado su ID
    ResponseEntity<Void> deleteTag(Long tag);

    // Verificar si un Tag existe por su contenido
    Boolean verificarTag(String tag);

    // Obtener todos los Tags por Post
    List<Tag> getAllTagsByPost(Post post);
}
