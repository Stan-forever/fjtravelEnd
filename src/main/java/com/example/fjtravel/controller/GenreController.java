package com.example.fjtravel.controller;

import com.example.fjtravel.node.Genre;
import com.example.fjtravel.repository.GenreRepository;
import com.example.fjtravel.result.ResponseMessage;
import com.example.fjtravel.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/appleyk/genre")
public class GenreController {
	
	@Autowired
	GenreRepository genreRepository;  
	

	/**
	 * 根据类型名查询Genre实体
	 * @param name
	 * @return
	 */
    @RequestMapping("/get")  
    public List<Genre> getGenres(@RequestParam(value="name") String name){             
    	return genreRepository.findByName(name); 	
    }  
      
    /**
     * 创建一个电影类型节点
     * @param genre
     * @return
     */
    @PostMapping("/save")  
    public ResponseResult saveGenre(@RequestBody Genre genre){      	
    	genreRepository.save(genre);
    	return new ResponseResult(ResponseMessage.OK);
    }  
}
