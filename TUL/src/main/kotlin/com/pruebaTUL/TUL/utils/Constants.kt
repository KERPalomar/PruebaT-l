package com.pruebaTUL.TUL.utils

class Constants {
    companion object {
        private const val URL_API_BASE = "/api"
        private const val URL_API_VERSION = "/v1"
        private const val URL_BASE = URL_API_BASE + URL_API_VERSION
        //Base API endpoint para personas
        const val URL_BASE_PRODUCTS = "$URL_BASE/products"
        const val URL_BASE_CARS = "$URL_BASE/cars"
        const val URL_BASE_PRODUCTS_CARS = "$URL_BASE/list"
    }
}