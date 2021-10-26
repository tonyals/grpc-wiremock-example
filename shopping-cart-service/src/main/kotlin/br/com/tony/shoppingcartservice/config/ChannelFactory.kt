package br.com.tony.shoppingcartservice.config

import br.com.tony.paymentservice.PaymentServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel

@Factory
class ChannelFactory {

    @Bean
    fun payService(
        @GrpcChannel("paymentservice")
        channel: ManagedChannel
    ): PaymentServiceGrpc.PaymentServiceBlockingStub {
        return PaymentServiceGrpc.newBlockingStub(channel)
    }
}