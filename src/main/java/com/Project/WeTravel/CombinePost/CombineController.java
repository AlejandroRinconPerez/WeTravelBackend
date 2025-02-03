package com.Project.WeTravel.CombinePost;

import com.Project.WeTravel.Post.application.DTO.DTO.ShowPostDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/combine")
public class CombineController {

    final CombineService combineService;

    @Autowired
    public CombineController(CombineService combineService) {
        this.combineService = combineService;
    }

    @GetMapping
    public ResponseEntity<List<CombinePostDTO>> getAllPosts() {
        List<CombinePostDTO> post = combineService.findAllCombineDTO();
        return new ResponseEntity<>(post, HttpStatus.OK);

    }

     @GetMapping ("/{idUser}")
    public ResponseEntity<List<CombinePostDTO>> getAllPostsByusr(@PathVariable Long idUser) {
        List<CombinePostDTO> post = combineService.findAllCombineDTObyUserid(idUser);
        return new ResponseEntity<>(post, HttpStatus.OK);

    }

    
    
    
}
