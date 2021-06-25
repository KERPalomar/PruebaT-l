package com.pruebaTUL.TUL.business.car

import com.pruebaTUL.TUL.exception.BusinessException
import com.pruebaTUL.TUL.exception.NotFoundException
import com.pruebaTUL.TUL.model.car.Car
import com.pruebaTUL.TUL.repository.car.CarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CarBusiness : ICarBusiness {

    @Autowired
    val carRepository: CarRepository? = null

    @Throws(BusinessException::class)
    override fun list(): List<Car> {
        try {
            return carRepository!!.findAll();
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun load(idCarro: String): Car {
        val op: Optional<Car>
        try {
            op = carRepository!!.findById(idCarro)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!op.isPresent){
            throw NotFoundException("No se encontró el carro con el id:$idCarro")
        }
        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(carro: Car): Car {
        try {
            return carRepository!!.save(carro)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    override fun update(carro: Car): Car {
        val op: Optional<Car>
        try {
            op = carRepository!!.findById(carro.idCar)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!op.isPresent){
            throw NotFoundException("No se encontró el carro con el id:${carro.idCar}")
        }else {
            try {
                return carRepository!!.save(carro)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun remove(idCarro: String) {
        val op: Optional<Car>
        try {
            op = carRepository!!.findById(idCarro)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!op.isPresent){
            throw NotFoundException("No se encontró el carro con el id:$idCarro")
        }else {
            try {
                return carRepository!!.deleteById(idCarro)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }
}