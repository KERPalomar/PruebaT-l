package com.pruebaTUL.TUL.web.productCar

import com.pruebaTUL.TUL.business.productCar.IProductCarBusiness
import com.pruebaTUL.TUL.exception.BusinessException
import com.pruebaTUL.TUL.model.car.Car
import com.pruebaTUL.TUL.model.product_car.ProductCar
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
@RequestMapping(Constants.URL_BASE_PRODUCTS_CARS)
@Api(tags = ["Products-Cars"])
class ProductCarRestController {

    @Autowired
    val productCarBusiness: IProductCarBusiness? = null

    @ApiOperation(value = "Agregar producto", notes = "Servicio para agregar un nuevo producto al carrito")
    @ApiResponses(
            ApiResponse(code = 201, message = "Item agregado correctamente"),
            ApiResponse(code = 400, message = "Solicitud inválida"))
    @PostMapping("")
    fun insert(@RequestBody productCar: ProductCar): ResponseEntity<Any> {
        return try {
            productCarBusiness!!.save(productCar)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_PRODUCTS + "/" + productCar.idProdCar)
            ResponseEntity(responseHeader, HttpStatus.CREATED)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @ApiOperation(value = "Eliminar un item del carro", notes = "Servicio para eliminar un item del carro")
    @ApiResponses(
            ApiResponse(code = 200, message = "Item eliminado correctamente"),
            ApiResponse(code = 400, message = "Solicitud inválida"))
    @DeleteMapping("/{idProd}")
    fun delete(@PathVariable("idProd") idProd: String ): ResponseEntity<Any>{
        return try {
            productCarBusiness!!.remove(idProd)
            ResponseEntity(HttpStatus.OK)
        }catch (e: Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: Exception){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @ApiOperation(value = "Listar productos", notes = "Listar todos los productos")
    @ApiResponses(
            ApiResponse(code = 200, message = "Listas de productos encontradas encontrados"),
            ApiResponse(code = 400, message = "Solicitud inválida"))
    @GetMapping("")
    fun list(): ResponseEntity<List<ProductCar>> {
        return try {
            ResponseEntity(productCarBusiness!!.list(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @ApiOperation(value = "Listar items de un carro", notes = "Servicio para listar todos los productos de un carro")
    @ApiResponses(
            ApiResponse(code = 200, message = "Listado encontrado"),
            ApiResponse(code = 400, message = "Solicitud inválida"))
    @GetMapping("/{idCarro}")
    fun load(@PathVariable("idCarro") idCarro:String): ResponseEntity<Any> {
        return try {
            ResponseEntity(productCarBusiness!!.load(idCarro), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}