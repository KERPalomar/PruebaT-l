package com.pruebaTUL.TUL.business.car

import com.pruebaTUL.TUL.model.car.Car

interface ICarBusiness {
    fun list(): List<Car>
    fun load(idCarro:String): Car
    fun save(carro: Car): Car
    fun update(carro: Car): Car
    fun remove(idCarro: String)
}