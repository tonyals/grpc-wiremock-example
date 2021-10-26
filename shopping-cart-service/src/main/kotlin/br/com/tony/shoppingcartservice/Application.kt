package br.com.tony.shoppingcartservice

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("br.com.tony.shoppingcartservice")
		.start()
}

