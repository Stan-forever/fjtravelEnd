//package com.example.fjtravel.controller;
//
//import com.appleyk.node.Person;
//import com.appleyk.repository.PersonRepository;
//import com.appleyk.result.ResponseMessage;
//import com.appleyk.result.ResponseResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/rest/appleyk/person")
//public class PersonController {
//
//	@Autowired
//	PersonRepository personRepository;
//
//	/**
//	 * 根据演员名查询Person实体
//	 *
//	 * @param title
//	 * @return
//	 */
//	@RequestMapping("/get")
//	public List<Person> getPersons(@RequestParam(value = "name") String name) {
//		return personRepository.findByName(name);
//	}
//
//	/**
//	 * 创建一个演员节点
//	 *
//	 * @param genre
//	 * @return
//	 */
//	@PostMapping("/save")
//	public ResponseResult savePerson(@RequestBody Person person) {
//		personRepository.save(person);
//		return new ResponseResult(ResponseMessage.OK);
//	}
//
//
//}
