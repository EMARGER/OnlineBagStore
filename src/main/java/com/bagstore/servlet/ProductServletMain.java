package com.bagstore.servlet;

import java.util.List;

import com.bagstore.dao.ProductDAO;
import com.bagstore.dto.ProductDTO;
import com.bagstore.service.ProductService;
import com.bagstore.util.DBUtil;

public class ProductServletMain {
	private ProductService productService;

	public ProductServletMain(ProductService productService) {
		this.productService = productService;
	}

	public void saveProduct() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName("Fjällräven Kånken");
		productDTO.setPrice(99770.00);
		productDTO.setQuantityAvailable(5);
		productDTO.setDescription("A durable and iconic Scandinavian backpack popular among students and travelers.");
		productDTO.setImg("img/Cbag8.png");
		productDTO.setCategoryId(9);
		try {
			int count = productService.saveProduct(productDTO);
			if (count > 0) {
				System.out.println("Product Information saved successfully");
			} else {
				System.err.println("product failed to save");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void updateProduct() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName("Product6");
		productDTO.setPrice(22563.30);
		productDTO.setQuantityAvailable(2);
		productDTO.setDescription("This is vary priminum feel bag");
		productDTO.setCategoryId(8);
		productDTO.setImg("img/bag6.png");
		productDTO.setId(13);

		try {
			int count = productService.updateProduct(productDTO);
			if (count > 0) {
				System.out.println("Product Information updated successfully");
			} else {
				System.err.println("product failed to update");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void deleteProduct() {
		Integer id = 9;

		try {
			int count = productService.deleteProduct(id);
			if (count > 0) {
				System.out.println("Product Information deleted successfully");
			} else {
				System.err.println("product failed to save");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void productFindById() {

		Integer id = 2;
		try {
			ProductDTO productDTO = productService.findProductByID(id);

			if (productDTO != null) {
				System.out.println("id:" + productDTO.getId());
				System.out.println("Name:" + productDTO.getName());

				System.out.println("Price :" + productDTO.getPrice());
				System.out.println("Product Description:" + productDTO.getDescription());

				System.out.println("Produvt Quantity:" + productDTO.getQuantityAvailable());
				System.out.println("Product Category:" + productDTO.getCategoryId());
				System.out.println("Created Date:" + productDTO.getCreatedAt());
			} else {
				System.err.println("Product not find");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void productFindByCategoryId() {

		Integer categoryId = 8;
		try {
			List<ProductDTO> productDTOs = productService.findProductByCategoryId(categoryId);

			if (productDTOs != null) {
				for (ProductDTO productDTO : productDTOs) {
					System.out.println("id:" + productDTO.getId());
					System.out.println("Name:" + productDTO.getName());

					System.out.println("Price :" + productDTO.getPrice());
					System.out.println("Product Description:" + productDTO.getDescription());

					System.out.println("Product Quantity:" + productDTO.getQuantityAvailable());
					System.out.println("Product Category:" + productDTO.getCategoryId());
					System.out.println("Created Date:" + productDTO.getCreatedAt());
				}
			} else {
				System.out.println("Product not find");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void findAllProduct() {

		try {
			List<ProductDTO> productDTOs = productService.findAllProduct();

			if (productDTOs != null) {
				for (ProductDTO productDTO : productDTOs) {
					System.out.println("id:" + productDTO.getId());
					System.out.println("Name:" + productDTO.getName());

					System.out.println("Price :" + productDTO.getPrice());
					System.out.println("Product Description:" + productDTO.getDescription());

					System.out.println("Product Quantity:" + productDTO.getQuantityAvailable());
					System.out.println("Product Category:" + productDTO.getCategoryId());
					System.out.println("Created Date:" + productDTO.getCreatedAt());
				}
			} else {
				System.err.println("Product not find");

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void main(String[] args) {
		DBUtil dbUtil = new DBUtil();
		ProductDAO productDAO = new ProductDAO(dbUtil);
		ProductService productService = new ProductService(productDAO);
		ProductServletMain productMainServlet = new ProductServletMain(productService);
		productMainServlet.saveProduct();
//	productMainServlet.updateProduct();
//	productMainServlet.deleteProduct();
//	productMainServlet.productFindById();
//		productMainServlet.productFindByCategoryId();	
//	productMainServlet.findAllProduct();
	}

}
