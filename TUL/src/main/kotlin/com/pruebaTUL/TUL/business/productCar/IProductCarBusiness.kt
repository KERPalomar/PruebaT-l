package com.pruebaTUL.TUL.business.productCar

import com.pruebaTUL.TUL.model.product_car.ProductCar

interface IProductCarBusiness {
    fun list(): List<ProductCar>
    fun load(idCar:String): ProductCar
    fun save(productCarr: ProductCar): ProductCar
    fun remove(idProduct: String)
}