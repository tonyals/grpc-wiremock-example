package br.com.tony.shoppingcartservice.config

import br.com.tony.paymentservice.PaymentServiceGrpc
import br.com.tony.shoppingcartservice.ShoppingCartServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel

@Factory
class ChannelConfig {

    @Bean
    fun cartService(
        @GrpcChannel("shoppingcartservice")
        channel: ManagedChannel
    ): ShoppingCartServiceGrpc.ShoppingCartServiceBlockingStub {
        return ShoppingCartServiceGrpc.newBlockingStub(channel)
    }
}
