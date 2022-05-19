package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrderDetail")
public class OrderDetail {
	@Id //主キー
	@GeneratedValue(strategy = GenerationType.IDENTITY) //重複しないように
	@Column(name = "code")
	private Integer code;

	@Column(name = "ordered_code")
	private Integer orderedCode;
	
	@Column(name = "items_code")
	private Integer itemsCode;
	
	@Column(name = "delete_flag")
	private Integer deleteFlag;
	
	

	//コンストラクタ、データの更新取得に必要
	//①コンストラクタを作る
	
	public OrderDetail(
			Integer code,
			Integer orderedCode,
			Integer itemsCode,
			Integer deleteFlag) {
		
		super();
		
		this.code = code;
		this.orderedCode = orderedCode;
		this.itemsCode = itemsCode;
		this.deleteFlag = deleteFlag;
				 
		
	}
	
	//空のコンストラクタ必要
	public OrderDetail() {
		super();
		
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getOrderedCode() {
		return orderedCode;
	}

	public void setOrderedCode(Integer orderedCode) {
		this.orderedCode = orderedCode;
	}

	public Integer getItemsCode() {
		return itemsCode;
	}

	public void setItemsCode(Integer itemsCode) {
		this.itemsCode = itemsCode;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}



}
