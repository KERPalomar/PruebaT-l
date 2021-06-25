package com.pruebaTUL.TUL.repository.product

import com.pruebaTUL.TUL.model.product.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product,String>