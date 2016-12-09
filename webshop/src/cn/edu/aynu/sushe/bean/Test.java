package cn.edu.aynu.sushe.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * Set：无序的没有重复元素的集合
		
		Set<Product> set = new HashSet<Product>();
		//向集合中的添加元素
		Product p1 = new Product();
		p1.setId("aaa");
		set.add(p1);
		
		Product p2 = new Product();
		p2.setId("bbb");
		set.add(p2);
		//得到遍历集合的迭代器
		Iterator<Product> it = set.iterator();
		//利用迭代器遍历集合
		while(it.hasNext()){
			//获取集合中的元素
			Product pp = it.next();
			System.out.println(pp.getId());
		}
		
		 */
		
		/*
		 * List：动态数组，元素有序可以添加重复的元素
		 */
		
		/**
		 * Map:集合中的元素是键值对： Key------->value     key不能重复
		 *                       China----->中国
		 *                       Korea----->韩国
		 *              
		 */
		Map<Product,Integer> map = new HashMap<Product,Integer>();
		//向map集合中加入键值对
		Product p = new Product();
		p.setId("aaa");
		map.put(p, 10);
		
		p = new Product();
		p.setId("bbb");
		map.put(p, 20);
		/**
		 * 遍历集合map的方法1
		 
		//获取键值对（词条）集合
		Set<Entry<Product,Integer>>  entrys = map.entrySet();
		//获取遍历集合的迭代器
		Iterator it = entrys.iterator();
		//利用迭代器遍历集合
		while(it.hasNext()){
			//获取集合中的元素（词条）
			Map.Entry<Product, Integer> entry = (Entry<Product, Integer>) it.next();
			//从词条中获取键key
			Product key = entry.getKey();
			//从词条中获取值vlaue
			Integer value = entry.getValue();
			System.out.println(key.getId()+"----->"+value);
		}
		*/
		
		
		/**
		 * 遍历集合map的方法2
		 */
		//获取键的集合
		Set<Product> keys = map.keySet();
		//获取遍历键集合的迭代器
		Iterator it2 = keys.iterator();
		//利用迭代器遍历集合
		while(it2.hasNext()){
			//获取集合中的元素--key
			Product key = (Product) it2.next();
			//根据key得到value
			Integer value = map.get(key);
			//输出键值对
			System.out.println(key.getId()+"----->"+value);
		}
		
	}

}
