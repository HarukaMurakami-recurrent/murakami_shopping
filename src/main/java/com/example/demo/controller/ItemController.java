package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Repository.CategoriesRepository;
import com.example.demo.Repository.ItemsRepository;
import com.example.demo.Repository.OrderDetailRepository;
import com.example.demo.Repository.PaymentsRepository;
import com.example.demo.Repository.UsersRepository;

@Controller
public class ItemController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ItemsRepository itemsRepository;
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@Autowired
	PaymentsRepository paymentsRepository;
//	
	@Autowired
	UsersRepository usersRepository;
	
	

	
//	//商品詳細ページを表示
	@RequestMapping(value="/itemDetail/{code}")
	public ModelAndView itemDetail(
			@PathVariable("code") int code,
//			@RequestParam("name") String name,
//			@RequestParam("price") int price,
//			@RequestParam("picture") String picture,
			ModelAndView mv) {
		
		
//	 List<Items> itemList = itemsRepository.findAll();
		//itemから1件取得
		mv.addObject("item",itemsRepository.findById(code).get());
		mv.setViewName("itemDetail");
		return mv;
	}
	
	
	//購入するボタンをおしたら画面遷移
	@RequestMapping("/purchaseCart")
	public String purchaseCart() {
		
		return "purchaseCart";
	}
	
	//商品追加ページを表示
	@RequestMapping(value="/addItem")
	public String addItem() {
		
		return "addItem";
		
	}
	
	//5/23ここまで
	//出品ボタンを押したときの処理<form action="/addItem" method="post">
	@RequestMapping(value="/addItem" , method=RequestMethod.POST)
	public ModelAndView addItems(ModelAndView mv) {
		
		//未入力だったときの処理
		
		return mv;
	}

}
