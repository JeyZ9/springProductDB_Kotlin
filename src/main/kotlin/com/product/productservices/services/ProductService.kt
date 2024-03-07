package com.product.productservices.services

import com.product.productservices.models.Product
import com.product.productservices.repository.ProductRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.Optional

@Service
class ProductService(private val productRepository: ProductRepository) {
    // Get all products
    fun getAllProducts(): List<Product> = productRepository.findAll()

//    getById
//    Optional มี ID ก็ได้ไม่มีก็ได้
    fun getProductById(id: Int): Optional<Product> = productRepository.findById(id)

    //Create Product
    fun createProduct(product: Product): Product = productRepository.save(product)

    //Update Product
    fun updateProduct(id: Int, updateProduct: Product): Product{
//        check id
        return if(productRepository.existsById(id)){
            updateProduct.id = id
            productRepository.save(updateProduct)
        }else{
            throw RuntimeException("Product not found with id = $id")
        }
    }

    //Delete Product
    fun deleteProduct(id: Int){
        return if (productRepository.existsById(id)){
            productRepository.deleteById(id)
        }else{
            throw RuntimeException("Product not found   with id = $id")
        }
    }
}