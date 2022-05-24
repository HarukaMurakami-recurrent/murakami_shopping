package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Repository.CategoriesRepository;
import com.example.demo.Repository.ItemsRepository;
import com.example.demo.Repository.OrderDetailRepository;
import com.example.demo.Repository.PaymentsRepository;
import com.example.demo.Repository.UsersRepository;
import com.example.demo.entity.Items;

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
	@RequestMapping(value = "/itemDetail/{code}")
	public ModelAndView itemDetail(@PathVariable("code") int code, ModelAndView mv) {

		// itemから1件取得
		mv.addObject("item", itemsRepository.findById(code).get());
		mv.setViewName("itemDetail");
		return mv;
	}

	// 購入するボタンをおしたら画面遷移
	@RequestMapping("/purchaseCart")
	public String purchaseCart() {

		return "purchaseCart";
	}

	// 商品追加ページを表示
	@RequestMapping(value = "/addItem")
	public String addItem() {

		return "addItem";

	}

	// 出品ボタン押したときの処理<a href="/addItem">出品する</a>
	// DBに登録、未入力チェックをおこなう
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public ModelAndView addItem(@RequestParam(name = "name") String name,
			@RequestParam(name = "price", defaultValue = "0") Integer price,
			@RequestParam(name = "picture") String picture,
			@RequestParam(name = "stock", defaultValue = "0") Integer stock,
			@RequestParam(name = "category_key", defaultValue = "0") Integer category_key,
			@RequestParam(name = "delivary_days ", defaultValue = "0") Integer delivary_days,

			ModelAndView mv) {

		// 未入力チェック

		// 商品登録画面に未入力があった場合
		if (name == null || name.length() == 0 || picture == null || picture.length() == 0 || price <= 0 || stock <= 0
				|| category_key <= 0 || delivary_days <= 0) {

			// 商品登録ページに「未入力」の表示
			mv.addObject("message", "もう一度入力");
			mv.setViewName("addItem");
		} else {
			// すべて入力があった場合
			// DBへ登録して、
			Items item = new Items(null, name, price, picture, stock, category_key, delivary_days, null, null);
			itemsRepository.saveAndFlush(item);

			List<Items> itemList = itemsRepository.findAll();
			mv.addObject("items", itemList);

			// 画面へ遷移する
			mv.setViewName("showItem");
		}
		return mv;
	}

	// 出品した商品の情報閲覧、変更画面表示
	@RequestMapping("/updateItem/{code}")
	public ModelAndView itemInfo(@PathVariable("code") int code, ModelAndView mv) {
		mv.addObject("item", itemsRepository.findById(code).get());
		mv.addObject("code", code);
		mv.setViewName("itemInfo");
		return mv;
	}

	// 出品商品の変更を確定した時の処理<form action="|/updateItem/${code}|" method="post">
	@RequestMapping(value = "/updateItem/{code}", method = RequestMethod.POST)
	public ModelAndView updateItem(@PathVariable("code") int code, @RequestParam("name") String name,
			@RequestParam("price") int price, @RequestParam("picture") String picture, @RequestParam("stock") int stock,
			@RequestParam("delivaryDays") int delivaryDays, ModelAndView mv) {

		// 未入力のチェック
		if (name == null || name.length() == 0) {
			// 画面に未入力の文字を表示
			mv.addObject("message", "未入力があります");
			mv.setViewName("showItem");
		} else {
			// すべての入力があった場合
			// DB,itemsからデータを取得
			Items i = itemsRepository.findById(code).get();

			mv.addObject("code", i.getCode());

			Items item = new Items(i.getCode(), name, price, picture, stock, delivaryDays);
			itemsRepository.saveAndFlush(item);

			// 商品一覧ページへ遷移する
			mv.addObject("items", item);

			List<Items> itemList = itemsRepository.findAll();
			mv.addObject("items", itemList);
			mv.setViewName("showItem");
		}
		return mv;
	}

	// 出品商品の削除処理<a th:href="|/deleteItem/${item.code}|">
	@RequestMapping(value = "/deleteItem/{code}")
	public ModelAndView deleteItem(
			@PathVariable("code") int code, 
			ModelAndView mv) {
		// DB,itemsからデータを取得
		Items item = itemsRepository.findById(code).get();
		mv.addObject("code", item.getCode());
		
		//itemsから削除
		itemsRepository.deleteById(code);
		
		mv.addObject("code",item.getCode());
		
		List<Items> itemList = itemsRepository.findAll();
		mv.addObject("items", itemList);
		mv.setViewName("showItem");
		
		return mv;
	}

}
