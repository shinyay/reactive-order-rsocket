package io.spring.shinyay.controller

import io.spring.shinyay.entity.Order
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ResponseBody
import reactor.core.publisher.Flux
import java.util.concurrent.ConcurrentHashMap




@Controller
@ResponseBody
class OrderController {

    val orderDataStore: Map<Long, Collection<Order>> = ConcurrentHashMap()

    @MessageMapping("orders.{customerId}")
    fun getOrders(@DestinationVariable customerId: Long): Flux<Order> {
        return Flux.fromIterable(orderDataStore[customerId] ?: emptyList())
    }

}