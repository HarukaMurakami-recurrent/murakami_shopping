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
import com.example.demo.classes.Cart;
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
	
	@RequestMapping(value="/cart/add/{code}" ,method=RequestMethod.POST)
	public ModelAndView addCart(
			@PathVariable("code") int code,
			@RequestParam(name="stock" , defaultValue="1") int stock,
			ModelAndView mv
			) {
		
		//セッションからカート情報を取得
		Cart cart = (Cart)session.getAttribute("cart");
		
		if(cart == null) {
			cart = new Cart();
			//sessionにカート情報を追加
			session.setAttribute("cart", cart);
		}
		
		//商品コードをキーに商品情報を取得し、カートに追加する
		Items item =itemsRepository.findById(code).get();
		cart.addCart(item,stock);
		
		mv.addObject("items",cart.getItems());
		
		mv.setViewName("cart");
		return mv;
	}
	
	//カートから削除
	@RequestMapping("/cart/delete/{code}")
	public ModelAndView deleteCart(
			@PathVariable(name="code") int code,
			ModelAndView mv) {
		//セッションスコープからカート情報を取得
		Cart cart = (Cart)session.getAttribute("cart");
		
		//カート情報から削除
		cart.deleteCart(code);
		
		mv.addObject("items",cart.getItems());
		
		mv.setViewName("cart");
		return mv;
	}
	
	
	// カートから商品一覧ページへ遷移
	@RequestMapping("/showItem")
	public ModelAndView showItem(ModelAndView mv) {

		List<Items> itemList = itemsRepository.findAll();
		mv.addObject("items", itemList);
		mv.setViewName("showItem");
		return mv;
	}
	
	//購入するボタンをおしたらメッセージ表示
//	@RequestMapping("/purchaseCart")
//	public ModelAndView purchaseCart(ModelAndView mv) {
//		
//		mv.addObject("message", "購入ありがとうございました！");
//		mv.setViewName("cart");
//		return mv;
//	}
	
	//購入するボタンをおしたら画面遷移
	@RequestMapping("/purchaseCart")
	public String purchaseCart() {
		
		return "purchaseCart";
	}

}
