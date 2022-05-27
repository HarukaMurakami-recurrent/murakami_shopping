package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//DBにせつぞくするためのクラス		
@Entity
@Table(name = "Categories")
public class Categories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code")
	private Integer code;

	@Column(name = "name")
	private String name;
	
	@Column(name = "delete_flag")
	private Integer delete_flag;
	
	public Categories(Integer code,String name,Integer delete_flag) {
		super();
		this.code = code;
		this.name = name;
		this.delete_flag = delete_flag;
	}
	
	//コンストラクタ作成
	public Categories() {
		//Entityクラス、コンストラクタにsuper();入れたほうがいい
		super();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}
	



}