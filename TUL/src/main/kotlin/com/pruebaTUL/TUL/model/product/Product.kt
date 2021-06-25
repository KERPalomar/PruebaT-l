package com.pruebaTUL.TUL.model.product

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "product")
data class Product(val nombre: String = "", val sku: String = "", val descripcion: String = "", val precio: Long = 0) {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    var idProd: String = "";
}