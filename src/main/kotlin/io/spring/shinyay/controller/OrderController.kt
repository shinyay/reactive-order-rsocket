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
class OrderController() {

    private val orderDataStore: MutableMap<Int, Collection<Order>> = ConcurrentHashMap()

    init {
        for (customerId in 1..8) {
            val listOfOrders = ArrayList<Order>()
            val max = (Math.random() * 100).toInt()

            for (orderId in 1..max) listOfOrders.add(Order(orderId, customerId))
            orderDataStore[customerId] = listOfOrders
        }
    }

    @MessageMapping("orders.{customerId}")
    fun getOrders(@DestinationVariable customerId: Int): Flux<Order> {
        return Flux.fromIterable(orderDataStore[customerId] ?: emptyList())
    }

}