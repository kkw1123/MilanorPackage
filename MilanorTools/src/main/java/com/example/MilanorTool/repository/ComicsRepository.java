package com.example.MilanorTool.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.MilanorTool.model.Comics;

public interface ComicsRepository extends JpaRepository<Comics, Long>{
	
	List<Comics> findById(String id);
}
