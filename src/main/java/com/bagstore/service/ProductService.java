package com.bagstore.service;

import java.util.List;

import com.bagstore.dao.ProductDAO;
import com.bagstore.dto.ProductDTO;

public class ProductService {
	private ProductDAO productDAO;
	public ProductService(ProductDAO productDAO) {
		this.productDAO=productDAO;
	}
	
	public int saveProduct(ProductDTO productDTO) throws Exception {
		int count= productDAO.addProductInfo(productDTO);
		return count;
	}
	
	public int updateProduct(ProductDTO productDTO) throws Exception {
		int count= productDAO.updateProductById(productDTO);
		return count;
	}

	
	public int deleteProduct(Integer id) throws Exception {
		int count= productDAO.deleteProductById(id);
		return count;
	}
	public ProductDTO findProductByID(Integer ID) throws Exception {
		return productDAO.findProductById(ID);
	}
	public List<ProductDTO> findAllProduct() throws Exception {
		return productDAO.findAllProduct();
	}
	public List<ProductDTO> findProductByCategoryId(Integer categoryId) throws Exception {
		return productDAO.findProductByCategoryId(categoryId);
	}
}
