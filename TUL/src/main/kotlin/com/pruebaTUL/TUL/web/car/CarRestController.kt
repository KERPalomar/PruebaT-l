package com.pruebaTUL.TUL.web.car

import com.pruebaTUL.TUL.business.car.ICarBusiness
import com.pruebaTUL.TUL.exception.BusinessException
import com.pruebaTUL.TUL.model.car.Car
import com.pruebaTUL.TUL.utils.Constants
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@RestController
@RequestMapping(Constants.URL_BASE_CARS)
@Api(tags = ["Cars"])
class CarRestController {

    @Autowired
    val carroBusiness: ICarBusiness? = null


    @PostMapping("")
    @ApiOperation(value = "Crear un carro de compras", notes = "Servicio para crear un nuevo producto")
    @ApiResponses(
            ApiResponse(code = 201, message = "Carro creado correctamente"),
            ApiResponse(code = 400, message = "Solicitud inválida"))
    fun insert(@RequestBody carro: Car): ResponseEntity<Any>{
        return try {
            carroBusiness!!.save(carro)
            var responseHeader = HttpHeaders()
            responseHeader.set("locale", Constants.URL_BASE_CARS+"/"+carro.idCar)
            ResponseEntity(responseHeader, HttpStatus.CREATED)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @ApiOperation(value = "Actualizar un carrito", notes = "Servicio para actualizar un carro existente")
    @ApiResponses(
            ApiResponse(code = 200, message = "Carro actualizado correctamente"),
            ApiResponse(code = 400, message = "Solicitud inválida"))
    @PutMapping("")
    fun update(@RequestBody carro: Car): ResponseEntity<Any>{
        return try {
            carroBusiness!!.save(carro)
            var responseHeader = HttpHeaders()
            responseHeader.set("locale", Constants.URL_BASE_CARS+"/"+carro.idCar)
            ResponseEntity(responseHeader,HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @ApiOperation(value = "Eliminar un carrito", notes = "Servicio para eliminar un carro")
    @ApiResponses(
            ApiResponse(code = 200, message = "Carro eliminado correctamente"),
            ApiResponse(code = 400, message = "Solicitud inválida"))
    @DeleteMapping("/{idCarro}")
    fun delete(@PathVariable ("idCarro") idCarro: String ): ResponseEntity<Any>{
        return try {
            carroBusiness!!.remove(idCarro)
            ResponseEntity(HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @ApiOperation(value = "Listar carros", notes = "Listar todos los carros")
    @ApiResponses(
            ApiResponse(code = 200, message = "Carros encontrados"),
            ApiResponse(code = 400, message = "Solicitud inválida"))
    @GetMapping("")
    fun list(): ResponseEntity<List<Car>> {
        return try {
            ResponseEntity(carroBusiness!!.list(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @ApiOperation(value = "Listar un carro", notes = "Servicio para listar un carro")
    @ApiResponses(
            ApiResponse(code = 200, message = "Carro encontrado"),
            ApiResponse(code = 400, message = "Solicitud inválida"))
    @GetMapping("/{idCarro}")
    fun load(@PathVariable("idCarro") idCarro:String): ResponseEntity<Any> {
        return try {
            ResponseEntity(carroBusiness!!.load(idCarro), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}