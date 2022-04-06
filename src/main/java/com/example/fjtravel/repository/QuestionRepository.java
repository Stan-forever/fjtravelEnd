package com.example.fjtravel.repository;

import com.example.fjtravel.node.Movie;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * 基于电影知识图谱的自问自答的查询接口
 * @author yukun24@126.com
 * @blob   http://blog.csdn.net/appleyk
 * @date   2018年5月10日-下午3:48:51
 */
public interface QuestionRepository extends Neo4jRepository<Movie,Long> {

	/**
	 * 0 对应问题模板0 == nm(景点) 评分
	 *
	 * @param name
	 *            景点名字
	 * @return 返回景点的评分
	 */
	@Query("match(n:Sights) where n.name={name} return n.star")
	Double getSightsStar(@Param("name") String name);

	/**
	 * 1 对应问题模板1 == nm(景点) 地址
	 *
	 * @param name
	 *            景点名字
	 * @return 返回景点的地址
	 */
	@Query("match(n:Sights) where n.name={name} return n.addr")
	String getSightsAddress(@Param("name") String name);

	/**
	 * 2 对应问题模板2 == nm(景点) 政策
	 *
	 * @param name
	 *            景点名字
	 * @return 返回景点的政策
	 */
	@Query("match(n:Sights) where n.name={name} return n.policy")
	String getSightsPolicy(@Param("name") String name);

	/**
	 * 3 对应问题模板3 == nm(景点) 联系电话
	 *
	 * @param name
	 *            景点名字
	 * @return 返回景点的联系电话
	 */
	@Query("match(n:Sights) where n.name={name} return n.phone")
	String getSightsPhone(@Param("name") String name);

	/**
	 * 4 对应问题模板4 == nm(景点) 时间
	 *
	 * @param name
	 *            景点名字
	 * @return 返回景点的时间
	 */
	@Query("match(n:Sights) where n.name={name} return n.time")
	String getSightsTime(@Param("name") String name);

	/**
	 * 5 对应问题模板5 == nm(景点) 简介
	 *
	 * @param name
	 *            景点名字
	 * @return 返回景点的简介
	 */
	@Query("match(n:Sights) where n.name={name} return n.desc")
	String getSightsDesc(@Param("name") String name);

	/**
	 * 6 对应问题模板6 == nm(景点)服务设施
	 *
	 * @param name
	 *            景点名字
	 * @return 返回景点的服务设施
	 */
	@Query("match(n:Sights) where n.name={name} return n.service")
	String getSightsService(@Param("name") String name);

	/**
	 * 7对应问题模板7 == nm(景点) 附近景点
	 *
	 * @param name
	 * 			  景点名字
	 * @return  返回景点的附近景点
	 */
	@Query("match(n:Sights) where n.name={name} return n.nearviews")
	String getSightsNearviews(@Param("name") String name);

	/**
	 * 8对应问题模板8 == nm(景点) 附近餐厅
	 *
	 * @param name
	 * 			  景点名字
	 * @return  返回景点的附近餐厅
	 */
	@Query("match(n:Sights) where n.name={name} return n.nearfood")
	String getSightsNearfood(@Param("name") String name);

	/**
	 * 9对应问题模板9 == nm(景点) 附近商店
	 *
	 * @param name
	 * 			  景点名字
	 * @return  返回景点的附近商店
	 */
	@Query("match(n:Sights) where n.name={name} return n.nearshop")
	String getSightsNearshop(@Param("name") String name);


	/**
	 * 10 对应问题模板10 == nnt(地点) 某地点有哪些景点
	 *
	 * @param name
	 *            地点名字
	 * @return 返回某个地点的所有景点
	 */
	@Query("match(n:City)-[:have]->(m:Sights) where n.name={name} return m.name")
	List<String> getHaveSights(@Param("name") String name);


	/**
	 * 11 对应问题模板11 == nnt(地点) ng(景点类型) 景点
	 *
	 * @param name
	 *            地点名
	 * @param sname
	 *            景点类型名称
	 * @return 返回电影名称列表
	 */
	@Query("match(n:City)-[:have]-(m:Sights) where n.name ={name} "
			+ "match(g:Genre)-[:is]-(m) where g.name=~{name} return distinct  m.name")
	List<String> getHaveSightsByType(@Param("name") String name, @Param("sname") String sname);

	/**
	 * 12对应问题模板12 == nm(景点) 类型
	 *
	 * @param name
	 * 			  景点名字
	 * @return  返回景点的类型
	 */
	@Query("match(n:Sights)-[r:is]->(b:Genre) where n.name={name} return b.name")
	String getSightsTypes(@Param("name") String name);
	
}
