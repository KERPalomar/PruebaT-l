package com.pruebaTUL.TUL.business.productCar

import com.pruebaTUL.TUL.exception.BusinessException
import com.pruebaTUL.TUL.exception.NotFoundException
import com.pruebaTUL.TUL.model.product_car.ProductCar
import com.pruebaTUL.TUL.repository.productCar.ProductCarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductCarBusiness: IProductCarBusiness {

    @Autowired
    val productCarRepository: ProductCarRepository? = null

    @Throws(BusinessException::class)
    override fun list(): List<ProductCar> {
        try {
            return productCarRepository!!.findAll();
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun load(idCarro: String): ProductCar {
        val op: Optional<ProductCar>
        try {
            op = productCarRepository!!.findById(idCarro)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!op.isPresent){
            throw NotFoundException("No se encontró el carro con el id:$idCarro")
        }
        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(productCar: ProductCar): ProductCar {
        try {
            return productCarRepository!!.save(productCar)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun remove(idCarro: String) {
        val op: Optional<ProductCar>
        try {
            op = productCarRepository!!.findById(idCarro)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!op.isPresent){
            throw NotFoundException("No se encontró el carro con el id:$idCarro")
        }else {
            try {
                return productCarRepository!!.deleteById(idCarro)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

}