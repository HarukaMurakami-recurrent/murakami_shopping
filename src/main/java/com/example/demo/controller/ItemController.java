package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@Autowired
	UsersRepository usersRepository;

	
	//商品一覧ページを表示
	@RequestMapping("/showItem")
	public String newAccount() {
		return "showItem";
	}
	

}
