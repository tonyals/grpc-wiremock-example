package br.com.tony.shoppingcartservice.integration

import br.com.tony.shoppingcartservice.dto.PaymentRequest
import br.com.tony.shoppingcartservice.dto.PaymentResponse

interface IPayService {
    fun pay(req: PaymentRequest): PaymentResponse
}