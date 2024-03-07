package com.product.productservices.controllers

import com.product.productservices.models.Product
import com.product.productservices.services.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/products")
class ProductController(private val productService: ProductService) {

    // get all products
    @GetMapping
    fun getAllProducts(): List<Product> = productService.getAllProducts()

//    get product by id
    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Int): ResponseEntity<Product>{
        val product = productService.getProductById(id)
        return product.map {
            ResponseEntity.ok(it)
        }
            .orElseGet{
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
    }
    // Create product
    // Request Body:{
    //         "id": 1,
    //        "productName": "TestDB",
    //        "productPrice": 500.21,
    //        "productDescription": "product test for database",
    //        "productQuantity": 10,
    //        "createdAt": "2024-03-07T13:09:43.608718"
    // }
    @PostMapping
    fun createProduct(@RequestBody product: Product): ResponseEntity<Product>{
        val createdProduct = productService.createProduct(product)
        return ResponseEntity(createdProduct, HttpStatus.CREATED)
    }

    // Update product
    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id:Int, @RequestBody updateProduct: Product): ResponseEntity<Product>{
        val updateProduct = productService.updateProduct(id, updateProduct)
        return ResponseEntity(updateProduct, HttpStatus.CREATED)
    }

    //delete product
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Int): ResponseEntity<Void>{
        productService.deleteProduct(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}