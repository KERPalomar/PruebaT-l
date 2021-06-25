package com.pruebaTUL.TUL.repository.car

import com.pruebaTUL.TUL.model.car.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository: JpaRepository<Car,String>