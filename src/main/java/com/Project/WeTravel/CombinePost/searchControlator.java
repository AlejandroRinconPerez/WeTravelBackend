
package com.Project.WeTravel.CombinePost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
public class searchControlator {
    
    
    private final SearchService searchService;
    
    
    @Autowired
    public searchControlator(SearchService searchService) {
        this.searchService = searchService;
    }
    
    
    
    
    @GetMapping ("/{querry}")
    public SearchClass getseracgresults(@PathVariable String querry){
     SearchClass   searchResult = searchService.searchAll(querry);
     return searchResult;
    }
    
    
    
    
}
