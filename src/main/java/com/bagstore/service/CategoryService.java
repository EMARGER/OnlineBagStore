package com.bagstore.service;

import java.util.List;

import com.bagstore.dao.CategoryDAO;
import com.bagstore.dto.CategoryDTO;

public class CategoryService {
	private CategoryDAO categoryDAO;

	public CategoryService(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public int save(CategoryDTO categoryDTO) throws Exception {
		return categoryDAO.save(categoryDTO);
	}

	public int delete(int id) throws Exception {
		return categoryDAO.delete(id);
	}

	public List<CategoryDTO> findAll() throws Exception {
		return categoryDAO.findAll();
	}

	public CategoryDTO findById(int id) throws Exception {
		return categoryDAO.findById(id);
	}
	public CategoryDTO findByName(String name) throws Exception {
		return categoryDAO.findByName(name);
	}
}
