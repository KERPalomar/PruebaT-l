package com.pruebaTUL.TUL.business.product

import com.pruebaTUL.TUL.model.product.Product

interface IProductBusiness {
    fun list(): List<Product>
    fun load(idProducto:String): Product
    fun save(producto: Product): Product
    fun remove(idProducto: String)
}