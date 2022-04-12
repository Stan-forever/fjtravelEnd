package com.example.fjtravel.controller;

import com.example.fjtravel.node.Sights;
import com.example.fjtravel.repository.SightsRepository;
import com.example.fjtravel.result.ResponseMessage;
import com.example.fjtravel.result.ResponseResult;
import com.example.fjtravel.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fjtravel/sights") //restful风格的api接口
public class SightsController {

	@Autowired
    SightsRepository sightsRepository;

	/**
	 * 根据电影名查询电影实体
	 * @param name
	 * @return
	 */
    @RequestMapping("/get")
    public List<Sights> getSights(@RequestParam(value="name") String name){
    	return sightsRepository.findByName(name);
    }

    /**
     * 创建一个电影节点
     * @param genre
     * @return
     */
//    @PostMapping("/save")
//    public ResponseResult saveMovie(@RequestBody Movie movie){
//    	movieRepository.save(movie);
//    	return new ResponseResult(ResponseMessage.OK);
//    }


    @RequestMapping("/query")
    public ResponseResult querySights(){
    	List<String> movieTiles = sightsRepository.getSightsName();
    	ResultData<String> result = new ResultData<String>(ResponseMessage.OK, movieTiles);
    	return new ResponseResult(result);
    }
}
