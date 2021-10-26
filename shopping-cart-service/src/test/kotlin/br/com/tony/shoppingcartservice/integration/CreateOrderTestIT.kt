package br.com.tony.shoppingcartservice.integration

import br.com.tony.shoppingcartservice.ShoppingCartServiceGrpc
import br.com.tony.shoppingcartservice.ShoppingCartServiceOuterClass.ShoppingCartServiceRequest
import br.com.tony.shoppingcartservice.constants.PaymentStatus
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@MicronautTest
class CreateOrderTestIT(
    @Inject
    val blockingStub: ShoppingCartServiceGrpc.ShoppingCartServiceBlockingStub
) {

    @Nested
    inner class SuccessTestIT {
        @Test
        fun `when valid data is received, should order status is PAYMENT_ACCEPT`() {
            val req = ShoppingCartServiceRequest.newBuilder()
                .setName("any name")
                .setCardNumber("CARD-NUMBER-HERE")
                .setValue(100.00)
                .build()

            val res = blockingStub.createOrder(req)

            assertEquals(PaymentStatus.PAYMENT_ACCEPT.name, res.orderStatus.name)
        }
    }

    @Nested
    inner class ErrorTestIT {
        @Test
        fun `when invalid data is received, should order status is PAYMENT_REFUSED`() {
            val req = ShoppingCartServiceRequest.newBuilder()
                .setName("invalid name")
                .setCardNumber("INVALID-CARD-NUMBER-HERE")
                .setValue(50.00)
                .build()

            val res = blockingStub.createOrder(req)

            assertEquals(PaymentStatus.PAYMENT_REFUSED.name, res.orderStatus.name)
        }
    }
}