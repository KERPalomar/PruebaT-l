package com.pruebaTUL.TUL.web.product

import com.pruebaTUL.TUL.business.product.IProductBusiness
import com.pruebaTUL.TUL.exception.BusinessException
import com.pruebaTUL.TUL.exception.NotFoundException
import com.pruebaTUL.TUL.model.product.Product
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


@RestController
@RequestMapping(Constants.URL_BASE_PRODUCTS)
@Api(tags = ["Products"])
class ProductRestController {

    @Autowired
    val productoBusiness: IProductBusiness? = null


    @PostMapping("")
    @ApiOperation(value = "Crear un producto", notes = "Servicio para crear un nuevo producto")
    @ApiResponses(
            ApiResponse(code = 201, message = "Cliente creado correctamente"),
            ApiResponse(code = 400, message = "Solicitud inválida"))
    fun insert(@RequestBody producto: Product): ResponseEntity<Any> {
        return try {
            productoBusiness!!.save(producto)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_PRODUCTS + "/" + producto.idProd)
            ResponseEntity(responseHeader, HttpStatus.CREATED)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @ApiOperation(value = "Actualizar un producto", notes = "Servicio para actualizar un producto completo por su idProd")
    @ApiResponses(
            ApiResponse(code = 200, message = "Cliente actualizado correctamente"),
            ApiResponse(code = 400, message = "Solicitud inválida"))
    @PutMapping("")
    fun update(@RequestBody producto: Product): ResponseEntity<Any> {
        return try {
            productoBusiness!!.save(producto)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @ApiOperation(value = "Eliminar un producto", notes = "Servicio para eliminar un producto")
    @ApiResponses(
            ApiResponse(code = 200, message = "Producto eliminado correctamente"),
            ApiResponse(code = 400, message = "Solicitud inválida"))
    @DeleteMapping("/{idProd}")
    fun delete(@PathVariable("idProd") idProd: String): ResponseEntity<Any> {
        return try {
            productoBusiness!!.remove(idProd)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @ApiOperation(value = "Listar productos", notes = "Listar todos los productos")
    @ApiResponses(
            ApiResponse(code = 200, message = "Productos encontrados"),
            ApiResponse(code = 400, message = "Solicitud inválida"))
    @GetMapping("")
    fun list(): ResponseEntity<List<Product>> {
        return try {
            ResponseEntity(productoBusiness!!.list(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @ApiOperation(value = "Listar un producto", notes = "Servicio para listar un producto")
    @ApiResponses(
            ApiResponse(code = 200, message = "Producto encontrado"),
            ApiResponse(code = 400, message = "Solicitud inválida"))
    @GetMapping("/{idProd}")
    fun load(@PathVariable("idProd") idProducto: String): ResponseEntity<Any> {
        return try {
            ResponseEntity(productoBusiness!!.load(idProducto), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}