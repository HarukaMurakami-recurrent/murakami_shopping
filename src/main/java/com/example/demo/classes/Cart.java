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
		
		//Itemsに追加しようとしていた商品（code）がなかった時
		if (existedItem == null) {
			
			//itemにquantityをセット
			item.setQuantity(quantity);
			//カート内にアイテムが存在しないとき、アイテム追加
			items.put(item.getCode(), item);
		}else {
			//アイテムコードが存在していた時、在庫数が増える
			int i = existedItem.getQuantity();
			i += quantity ;
			existedItem.setQuantity(i);
			
		}
		//合計金額を計算
		recalcTotal();
	}
	

	//カートの中身を削除→合計金額の計算
	public void deleteCart(int itemCode) {
		items.remove(itemCode);
		recalcTotal();
	}
	
	//合計金額の計算
	private void recalcTotal() {
		total=0;
		for(Items item : items.values()) {
			total += item.getPrice() * item.getQuantity();		}
	}

}
