package com.example.demo.classes;

import java.util.HashMap;
import java.util.Map;

//import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import com.example.demo.entity.Items;

public class Cart {

	// カートの配列
	private Map<Integer, Items> items = new HashMap<>();
	// 合計金額の変数を作る
	private int total;

	public Map<Integer, Items> getItems() {
		return items;
	}

	public int getTotal() {
		return total;
	}
	

	public void addCart(Items item, int quantity) {
		Items existedItem = items.get(item.getCode());
		
		if (existedItem == null) {
			
			//itemにquantityをセット
			item.setQuantity(quantity);
			//カート内にアイテムが存在しないとき、アイテム追加
			items.put(item.getCode(), item);
		}else {
			int i = existedItem.getQuantity();
			i += quantity ;
			existedItem.setQuantity(i);
		}
	}
	
	public void deleteCart(int itemCode) {
		//カートの中身を削除
		items.remove(itemCode);
	}

}
