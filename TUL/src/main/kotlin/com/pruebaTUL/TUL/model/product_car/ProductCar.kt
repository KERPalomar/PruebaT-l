package com.pruebaTUL.TUL.model.product_car

import com.pruebaTUL.TUL.model.product.Product
import com.pruebaTUL.TUL.model.car.Car
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "productCar")
data class ProductCar(val cantidad: Long = 0) {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    var idProdCar: String = "";

    @ManyToOne
    @JoinColumn(name = "product_idProd")
    var product: Product? = null

    @ManyToOne
    @JoinColumn(name = "product_idCar")
    var car: Car? = null

}