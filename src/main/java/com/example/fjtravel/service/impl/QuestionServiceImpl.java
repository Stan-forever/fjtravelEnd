package com.example.fjtravel.service.impl;

import com.example.fjtravel.process.ModelProcess;
import com.example.fjtravel.repository.QuestionRepository;
import com.example.fjtravel.service.QuestionService;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class QuestionServiceImpl implements QuestionService {

	@Value("${rootDirPath}")
	private String rootDictPath;

	@Value("${HanLP.CustomDictionary.path.sightsDict}")
	private String sightsDictPath;

	@Value("${HanLP.CustomDictionary.path.genreDict}")
	private String genreDictPath;

	@Value("${HanLP.CustomDictionary.path.scoreDict}")
	private String scoreDictPath;
	@Value("${HanLP.CustomDictionary.path.cityDict}")
	private String cityDictPath;
//	@Value("${HanLP.CustomDictionary.path.addressDict}")
//	private String addressDictPath;
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public void showDictPath() {
		System.out.println("HanLP分词字典及自定义问题模板根目录：" + rootDictPath);
		System.out.println("用户自定义扩展词库【景点】：" + sightsDictPath);
	}

	@Override
	public String answer(String question) throws Exception {

		ModelProcess queryProcess = new ModelProcess(rootDictPath);

		/**
		 * 加载自定义的电影字典 == 设置词性 nm 0
		 */

		loadSightsDict(sightsDictPath);

		/**
		 * 加载自定义的类型字典 == 设置词性 ng 0
		 */
		loadGenreDict(genreDictPath);

		/**
		 * 加载自定义的评分字典 == 设置词性 x 0
		 */
		loadScoreDict(scoreDictPath);
		loadCityDict(cityDictPath);
//		loadAddressDict(addressDictPath);
		ArrayList<String> reStrings = queryProcess.analyQuery(question);
		int modelIndex = Integer.valueOf(reStrings.get(0));
		String answer = null;
		String title = "";
		String name = "";
		String type = "";
		Double score = 0.0;
		/**
		 * 匹配问题模板
		 */
		switch (modelIndex) {
			case 0:
				/**
				 * nm 评分 == 景点评分
				 */
				name = reStrings.get(1);
				score = questionRepository.getSightsStar(name);
				if (score != null) {
					BigDecimal b = new BigDecimal(score);
					// 四舍五入取两位小数
					answer = String.valueOf(b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				} else {
					answer = null;
				}
				break;
			case 1:
				/**
				 * nm 地址 == 景点地址
				 */
				name = reStrings.get(1);
				answer = questionRepository.getSightsAddress(name);
				break;
			case 2:
				/**
				 * nm 政策 == 景点政策
				 */
				name = reStrings.get(1);
				answer = questionRepository.getSightsPolicy(name);
				break;
			case 3:
				/**
				 * nm 简介 == 电影简介、详情
				 */
				name = reStrings.get(1);
				answer = questionRepository.getSightsPhone(name);
				break;
			case 4:
				/**
				 * nm 演员列表 == 电影演员列表
				 */
				name = reStrings.get(1);
				answer = questionRepository.getSightsTime(name);
				break;
			case 5:
				/**
				 * nnt 介绍 == 演员简介
				 */
				name = reStrings.get(1);
				answer = questionRepository.getSightsDesc(name);
				break;
			case 6:
				/**
				 * nnt 电影类型 ng == 演员演过的x类型的电影有哪些
				 */
				name = reStrings.get(1);
				answer = questionRepository.getSightsService(name);
				break;

			case 7:
				/**
				 * nnt 电影作品 == 演员的电影作品有哪些
				 */
				name = reStrings.get(1);
				answer = questionRepository.getSightsNearviews(name);
				break;
			case 8:
				/**
				 * 1 2 3 4 nnt 参演评分 大于 x == 演员参演的电影评分大于x的有哪些
				 */
				name = reStrings.get(1);
				answer = questionRepository.getSightsNearfood(name);
				break;
			case 9:
				/**
				 * 1 2 3 4 nnt 参演评分 小于 x == 演员参演的电影评分小于x的有哪些
				 */
				name = reStrings.get(1);
				answer = questionRepository.getSightsNearshop(name);
				break;
			case 10:
				/**
				 * nnt 电影类型 == 演员参演的电影类型有哪些
				 */
				name = reStrings.get(1);
				List<String> haveSights = questionRepository.getHaveSights(name);
				if (haveSights.size() == 0) {
					answer = null;
				} else {
					answer = haveSights.toString().replace("[", "").replace("]", "");
				}
				break;
			case 11:
				/**
				 * 1 2 3 4 nnt nnr 合作 电影列表 == 演员A和演员B合作的电影有哪些
				 */
				name = reStrings.get(1);
				type = reStrings.get(2);
				if (type.indexOf("片") > 0) {
					type = type.substring(0, type.indexOf("片"));
				}
				// 模糊查询拼接参数 == 包含type的电影都查出来
				type = ".*" + type + "*.";
				List<String> sights = questionRepository.getHaveSightsByType(name, type);
				if (sights.size() == 0) {
					answer = null;
				} else {
					answer = sights.toString().replace("[", "").replace("]", "");
				}
				break;
			case 12:
				name = reStrings.get(1);
				answer = questionRepository.getSightsTypes(name);
				break;
			case 13:
				/**
				 * nnt 电影类型 == 演员参演的电影类型有哪些
				 */
				name = reStrings.get(1);
				List<String> citySights = questionRepository.citySights(name);
				if (citySights.size() == 0) {
					answer = null;
				} else {
					answer = citySights.toString().replace("[", "").replace("]", "");
				}
				break;
			default:
				break;
		}
		System.out.println(answer);
		if (answer != null && !answer.equals("") && !answer.equals("\\N")) {
			return answer;
		} else {
			return "sorry,我没有找到你要的答案";
		}
	}

	/**
	 * 加载自定义电影字典
	 * 
	 * @param path
	 */
	public void loadSightsDict(String path) {

		File file = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			addCustomDictionary(br, 0);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * 加载自定义电影类别字典
	 * 
	 * @param path
	 */
	public void loadGenreDict(String path) {

		File file = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			addCustomDictionary(br, 1);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 加载自定义电影评分字典
	 * 
	 * @param path
	 */
	public void loadScoreDict(String path) {

		File file = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			addCustomDictionary(br, 2);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	public void loadCityDict(String path) {

		File file = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			addCustomDictionary(br, 3);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
//	public void loadAddressDict(String path) {
//
//		File file = new File(path);
//		BufferedReader br = null;
//		try {
//			br = new BufferedReader(new FileReader(file));
//			addCustomDictionary(br, 3);
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
//	}
	/**
	 * 添加自定义分词及其词性，注意数字0表示频率，不能没有
	 * 
	 * @param br
	 * @param type
	 */
	public void addCustomDictionary(BufferedReader br, int type) {

		String word;
		try {
			while ((word = br.readLine()) != null) {
				switch (type) {
				/**
				 * 设置电影名词词性 == nm 0
				 */
				case 0:
					CustomDictionary.add(word, "nm 0");
					break;
				/**
				 * 设置电影类型名词 词性 == ng 0
				 */
				case 1:
					CustomDictionary.add(word, "ng 0");
					break;
				/**
				 * 设置电影评分数词 词性 == x 0
				 */
				case 2:
					CustomDictionary.add(word, "x 0");
					break;
				case 3:
					CustomDictionary.add(word,"chengshi 0")	;
				default:
					break;
				}
			}
			br.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
