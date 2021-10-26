package br.com.tony.shoppingcartservice.controller

import br.com.tony.shoppingcartservice.ShoppingCartServiceGrpc
import br.com.tony.shoppingcartservice.ShoppingCartServiceOuterClass.PaymentStatus.PAYMENT_ACCEPT
import br.com.tony.shoppingcartservice.ShoppingCartServiceOuterClass.PaymentStatus.PAYMENT_REFUSED
import br.com.tony.shoppingcartservice.ShoppingCartServiceOuterClass.ShoppingCartServiceReply
import br.com.tony.shoppingcartservice.ShoppingCartServiceOuterClass.ShoppingCartServiceRequest
import br.com.tony.shoppingcartservice.constants.PaymentStatus
import br.com.tony.shoppingcartservice.dto.PaymentRequest
import br.com.tony.shoppingcartservice.integration.IPayService
import io.grpc.stub.StreamObserver
import io.micronaut.grpc.annotation.GrpcService
import jakarta.inject.Inject

@GrpcService
class OrderController(
    @Inject
    private val payService: IPayService
): ShoppingCartServiceGrpc.ShoppingCartServiceImplBase() {

    override fun createOrder(
        request: ShoppingCartServiceRequest?,
        responseObserver: StreamObserver<ShoppingCartServiceReply>?
    ) {
        val toPay = PaymentRequest(request!!.name, request.value, request.cardNumber)

        val paymentResponse = payService.pay(toPay)

        when(paymentResponse.status) {
            PaymentStatus.PAYMENT_ACCEPT.name -> {
                responseObserver!!.onNext(
                    ShoppingCartServiceReply.newBuilder()
                    .setOrderStatus(PAYMENT_ACCEPT)
                    .build())
                responseObserver.onCompleted()
            }
            else -> {
                responseObserver!!.onNext(ShoppingCartServiceReply.newBuilder()
                    .setOrderStatus(PAYMENT_REFUSED)
                    .build())
                responseObserver.onCompleted()
            }
        }
    }
}