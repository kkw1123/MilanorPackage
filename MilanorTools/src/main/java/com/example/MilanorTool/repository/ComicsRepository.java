package com.example.MilanorTool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MilanorTool.model.Comics;

public interface ComicsRepository extends JpaRepository<Comics, Long>{
	
	List<Comics> findById(String id);
}
