package com.pruebaTUL.TUL.business.product

import com.pruebaTUL.TUL.exception.BusinessException
import com.pruebaTUL.TUL.exception.NotFoundException
import com.pruebaTUL.TUL.model.product.Product
import com.pruebaTUL.TUL.repository.product.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductBusiness: IProductBusiness {


    @Autowired
    val productoRepository: ProductRepository?=null;

    @Throws(BusinessException::class)
    override fun list(): List<Product> {
        try {
            return productoRepository!!.findAll();
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun load(idProducto: String): Product {
        val op: Optional<Product>
        try {
            op = productoRepository!!.findById(idProducto)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontró la persona con el id: $idProducto")
        }
        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(producto: Product): Product {
        try {
            return productoRepository!!.save(producto)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun remove(idProducto: String) {
        val op:  Optional<Product>
        try {
            op= productoRepository!!.findById(idProducto)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!op.isPresent){
            throw NotFoundException("No se encontró el producto con el id:$idProducto")
        }else{
            try {
                productoRepository!!.deleteById(idProducto)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

}