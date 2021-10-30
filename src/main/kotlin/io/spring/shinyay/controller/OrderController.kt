package io.spring.shinyay.controller

import io.spring.shinyay.entity.Order
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ResponseBody
import java.util.concurrent.ConcurrentHashMap




@Controller
@ResponseBody
class OrderController {

    val orderDataStore: Map<Int, Collection<Order>> = ConcurrentHashMap()


}