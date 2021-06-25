package com.pruebaTUL.TUL.repository.productCar

import com.pruebaTUL.TUL.model.product_car.ProductCar
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductCarRepository: JpaRepository<ProductCar,String>