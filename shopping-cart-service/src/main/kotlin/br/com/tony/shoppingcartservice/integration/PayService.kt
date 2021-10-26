package br.com.tony.shoppingcartservice.integration

import br.com.tony.paymentservice.PaymentServiceGrpc
import br.com.tony.paymentservice.PaymentServiceOuterClass.PaymentGrpcRequest
import br.com.tony.shoppingcartservice.dto.PaymentRequest
import br.com.tony.shoppingcartservice.dto.PaymentResponse
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class PayService(
    @Inject
    private val payServiceGrpc: PaymentServiceGrpc.PaymentServiceBlockingStub
): IPayService {
    override fun pay(req: PaymentRequest): PaymentResponse {
        val request = PaymentGrpcRequest.newBuilder()
            .setName(req.name)
            .setCardNumber(req.cardNumber)
            .setValue(req.value)
            .build()
        val response = payServiceGrpc.pay(request)

        return PaymentResponse(response.status.name)
    }
}