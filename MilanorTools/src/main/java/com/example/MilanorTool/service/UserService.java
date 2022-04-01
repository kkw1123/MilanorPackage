package com.example.MilanorTool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.MilanorTool.model.Role;
import com.example.MilanorTool.model.Users;
import com.example.MilanorTool.repository.UsersRepository;

@Service
public class UserService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Users save(Users users) {
		String encodePass = passwordEncoder.encode(users.getPassword());
		users.setPassword(encodePass); //인코딩된 패스워드로 DB저장
		users.setEnabled(true); //default 설정
		Role role = new Role();
		role.setId(2);
		users.getRoles().add(role); //user_role 관계테이블에도 데이터 삽입
		System.out.println(users);
		return usersRepository.save(users);
	}
}
