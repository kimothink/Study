package com.example.firstproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ArticleApiController {
	
	@Autowired
	private ArticleService articleService;

	@GetMapping("/api/articles")
	public List<Article> index()
	{
		return articleService.index();
	}
	
	@GetMapping("/api/articles/{id}")
	public Article show(@PathVariable Long id){
		return articleService.show(id);
		
	}
	
	@PostMapping("/api/articles")
	public ResponseEntity<Article> create(@RequestBody ArticleForm dto)
	{
		Article created = articleService.create(dto);
		return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
		
	
	@PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
            @RequestBody ArticleForm dto) 
    {
		Article update = articleService.update(id,dto);
		return (update != null) ? 
		    ResponseEntity.status(HttpStatus.OK).body(update) :
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
	
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
       
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
