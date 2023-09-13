package com.project.dairyproject.Services;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dairyproject.Entities.ProductDetails;
import com.project.dairyproject.Repository.ProductRepository;
import com.project.dairyproject.UserDefinedExceptions.ProductNotFoundException;

@Service
@Transactional
public class ProductServices {

	@Autowired
	private ProductRepository proRepo;

	public Set<ProductDetails> getAllProductDetails() {
		return proRepo.findAllProductDetails();
	}

	public ProductDetails getProductDetailsByName(String name) {
		return proRepo.findProductDetailsByName(name);
	}

	public ProductDetails getProductDetailsByPid(Integer PID) {
		return proRepo.findProductDetailByPid(PID);
	}

	public String insertNewProductDetails(ProductDetails productDetails) {

		if (proRepo.save(productDetails) != null) {
			return "Product Added...";
		}

		return "Failed to add product. Something went wrong !";

	}

	public String updateProductDetailDetails(ProductDetails productDetails) {
		if (proRepo.save(productDetails) != null) {
			return "Product Updated Succesfully.";
		}
		return "Product update failed.";
	}

	public String removeProductByPID(Integer pid) {
		if (proRepo.deleteProductDetailsByPID(pid) == 1) {
			return "Product removed succesfully...";
		}

		throw new ProductNotFoundException("Product not found !");
	}
}
