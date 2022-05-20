package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.example.demo.entity.Users;

@Controller
public class AccountController {
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

	@Autowired
	UsersRepository usersRepository;

	// 新規登録画面の表示
	@RequestMapping("/newAccount")
	public String newAccount() {
		return "newAccount";
	}

	// ログイン画面の表示
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	// 登録ボタン押したときの処理<form action="/newAccount">
	// DBに登録、未入力チェックをおこなう
	@RequestMapping(value = "/newAccount", method = RequestMethod.POST)
	public ModelAndView newAccount(@RequestParam(name = "name") String name,
			@RequestParam(name = "address") String address, @RequestParam(name = "tel") String tel,
			@RequestParam(name = "email") String email, @RequestParam(name = "pass") String pass,

			ModelAndView mv) {

		// 未入力チェック

		// 新規登録画面に未入力があった場合
		if (name == null || name.length() == 0 || address == null || address.length() == 0 || tel == null
				|| tel.length() == 0 || email == null || email.length() == 0 || pass == null || pass.length() == 0) {

			// 新規登録画面に「未入力」の表示
			mv.addObject("message", "未入力があります");
			mv.setViewName("newAccount");
		} else {
			// すべて入力があった場合
			// DBへ登録して、
			Users users = new Users(null, name, address, tel, email, pass);
			usersRepository.saveAndFlush(users);

			mv.addObject("name", name);
			mv.addObject("address", address);
			mv.addObject("tel", tel);
			mv.addObject("email", email);
			mv.addObject("pass", pass);
//			ログイン画面へ遷移する
			mv.setViewName("login");
		}
		return mv;
	}

	// ログインボタンを押したときの処理<form action="login" method="post">
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(name = "name") String name, @RequestParam(name = "pass") String pass,

			ModelAndView mv) {
		// 未入力チェック
		// ログイン画面に未入力があった場合
		if (name == null || name.length() == 0 || pass == null || pass.length() == 0) {

			// 新規登録画面に「未入力」の表示
			mv.addObject("message", "未入力があります");
			mv.setViewName("login");
		} else {
			// すべて入力があった場合

			// DB、Usersからデータを取得
			// 5/19 List<Users> findByNameAndPass(String name,String pass);
			List<Users> usersList = usersRepository.findByNameAndPass(name, pass);

			// DB,usersのnameとpasswordと一致していたらshowItemへ遷移
			// usersListが1件以上あったらログイン
			if (usersList.size() >= 1) {
				//itemList
					List<Items> itemList = itemsRepository.findAll();
					mv.addObject("items",itemList);
				mv.setViewName("showItem");
			} else {

				// nameとpassが不一致の場合、「一致しません」とメッセージを表示
				// ログイン画面へ遷移
				mv.addObject("message", "一致しません");
				mv.setViewName("login");

			}
//			mv.addObject("name",name);		
//			mv.addObject("pass",pass);	
//			

		}

		return mv;

	}
}
