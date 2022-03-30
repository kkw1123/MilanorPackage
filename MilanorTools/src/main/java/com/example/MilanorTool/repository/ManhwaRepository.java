package com.example.MilanorTool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MilanorTool.model.Manhwa;

public interface ManhwaRepository extends JpaRepository<Manhwa, Long>{
	
	List<Manhwa> findById(String id);
}
