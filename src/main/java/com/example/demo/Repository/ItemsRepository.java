package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Items;

@Repository
public interface ItemsRepository extends JpaRepository<Items,Integer>{
//	List<Items> findByCategoryKey(Integer categoryKey);
	
}