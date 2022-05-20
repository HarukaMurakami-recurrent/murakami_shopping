package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer>{
	//nameとpassで検索
	List<Users> findByNameAndPass(String name,String pass);
	
}