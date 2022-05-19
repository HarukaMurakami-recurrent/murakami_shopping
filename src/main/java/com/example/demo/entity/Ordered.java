package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ordered")
public class Ordered {
	@Id //主キー
	@GeneratedValue(strategy = GenerationType.IDENTITY) //重複しないように
	@Column(name = "code")
	private Integer code;

	@Column(name = "user_code")
	private Integer userCode;
	
	@Column(name = "ordered_date")
	private Date orderedDate;
	
	@Column(name = "total_price")
	private Integer totalPrice;
	
	@Column(name = "delete_flag")
	private Integer deleteFlag;
	
	

	//コンストラクタ、データの更新取得に必要
	//①コンストラクタを作る
	
	public Ordered(
			Integer code,
			Integer userCode,
			Date orderedDate,
			Integer totalPrice,
			Integer deleteFlag) {
		
		super();
		
		this.code = code;
		this.userCode = userCode;
		this.orderedDate = orderedDate;
		this.totalPrice = totalPrice;
		this.deleteFlag = deleteFlag;
				 
		
	}
	
	//空のコンストラクタ必要
	public Ordered() {
		super();
		
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}

	public Date getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}



}
