package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j // 로깅을 위한 롬복 어노테이션
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;
 
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form ,Model model) {
        log.info(form.toString());    // println() 을 로깅으로 대체!

        Article article = form.toEntity();
        log.info(article.toString()); // println() 을 로깅으로 대체!

        Article saved = articleRepository.save(article);
        log.info(saved.toString());   // println() 을 로깅으로 대체!

        return "redirect:/articles/" + saved.getId();


    }
    
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id , Model model)
    {
    	log.info("id : "+id);
    	
    	Article articleEntity = articleRepository.findById(id).orElse(null);
    	
    	List<CommentDto> commentDtos = commentService.comments(id);
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);

    	return  "articles/show";
    }
    
    @GetMapping("/articles")
    public String index(Model model) {
        // 1: 모든 Article을 가져온다!
        List<Article> articleEntityList = articleRepository.findAll();

        // 2: 가져온 Article 묶음을 뷰로 전달!
        model.addAttribute("articleList", articleEntityList);

        // 3: 뷰 페이지를 설정!
        return "articles/index";
    }
    
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id , Model model)
    {
    	Article articleEntity = articleRepository.findById(id).orElse(null);
       
    	model.addAttribute("article", articleEntity);

    	return "articles/edit";
    }
    
    @PostMapping("/articles/update")
    public String update(ArticleForm form)
    {
    	Article articleEntity = form.toEntity();
    	
    	Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
    	if( target !=null)
    	{
    		articleRepository.save(articleEntity);
    	}
    	log.info(articleEntity.toString());
    	return "redirect:/articles/"+articleEntity.getId();
    }
    
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id , RedirectAttributes rttr)
    {
    	Article target = articleRepository.findById(id).orElse(null);
    	
    	if(target !=null)
    	{
    		articleRepository.delete(target);
    		rttr.addFlashAttribute("msg","삭제가 완료되었습니다.");
    	}
    	return "redirect:/articles";
    }
}