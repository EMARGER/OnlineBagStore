package com.bagstore.servlet;

import java.util.List;


import com.bagstore.dao.CategoryDAO;
import com.bagstore.dto.CategoryDTO;
import com.bagstore.service.CategoryService;
import com.bagstore.util.DBUtil;

public class CategoryServletMain {
	private CategoryService categoryService;
	
	public CategoryServletMain(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public void save() {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setName("Wallet");
		categoryDTO.setDescription("It is very attractive ");
		
		try {
			int count = categoryService.save(categoryDTO);

			if(count>0) {
				System.out.println("Category Saved SuccesFully");
			}
			else {
				System.out.println("Failed To Save Category");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public void delete() {
		int id = 1;
		try {
			int count = categoryService.delete(id);
			if(count>0) {
				System.out.println("Category Delete SuccesFully");
			}
			else {
				System.out.println("Failed To Delete Category");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void findById() {
		int id = 2;
		try {
			CategoryDTO categoryDTO = categoryService.findById(id);
			
			if(categoryDTO != null) {
				System.out.println("Category Found");
				System.out.println("id : " + categoryDTO.getId());
				System.out.println("name : " + categoryDTO.getName());
				System.out.println("Description: " + categoryDTO.getDescription());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findByName() {
		String name="Backpack";
		try {
			CategoryDTO categoryDTO = categoryService.findByName(name);
			
			if(categoryDTO != null) {
				System.out.println("Category Found");
				System.out.println("id : " + categoryDTO.getId());
				System.out.println("name : " + categoryDTO.getName());
				System.out.println("Description: " + categoryDTO.getDescription());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void findAll() {
		try {
			List<CategoryDTO> categoryDTOs = categoryService.findAll();
			
			if(categoryDTOs != null) {
				System.out.println("Categories Found");
				for ( CategoryDTO categoryDTO : categoryDTOs) {
					System.out.println("id : " + categoryDTO.getId());
					System.out.println("name : " + categoryDTO.getName());
					System.out.println("Description: " + categoryDTO.getDescription());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DBUtil dbUtil = new DBUtil();
		CategoryDAO categoryDAO = new CategoryDAO(dbUtil);
		CategoryService categoryService = new CategoryService(categoryDAO);
		CategoryServletMain mainServlet = new CategoryServletMain(categoryService);
		//mainServlet.save();
//		mainServlet.delete();
//		mainServlet.findById();
//		mainServlet.findAll();
		mainServlet.findByName();
	}
	
}
