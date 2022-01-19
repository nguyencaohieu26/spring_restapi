package com.api.spring_restapi.Controller;

import com.api.spring_restapi.Entity.Order;
import com.api.spring_restapi.Entity.OrderDetail;
import com.api.spring_restapi.Entity.OrderDetailKey;
import com.api.spring_restapi.Response.RestPagination;
import com.api.spring_restapi.Response.RestResponse;
import com.api.spring_restapi.Service.OrderService;
import com.api.spring_restapi.Specification.FilterField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/orders")
public class OrderController {

    //Constructor injection
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService1){
        this.orderService = orderService1;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllOrder(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
            @RequestParam(name = "id", defaultValue = "-1") int id,
            @RequestParam(name = "account_id", defaultValue = "-1") int account_id,
            @RequestParam(name = "minPrice",defaultValue = "-1") int minPrice,
            @RequestParam(name = "maxPrice",defaultValue = "-1") int maxPrice,
            @RequestParam(name = "checkOut", defaultValue = "false") boolean checkOut,
            @RequestParam(name = "shipStatus",defaultValue = "-1") int shipStatus,
            @RequestParam(name = "dateStart",required = false) String dateStart,
            @RequestParam(name = "dateEnd",required = false) String dateEnd
    ){
        //convert to FilterFiled
        FilterField orderFilter = FilterField.FilterFieldBuilder.aFilterField()
                .withPage(page)
                .withPageSize(pageSize)
                .withId(id)
                .withAccountId(account_id)
                .withMinPrice(minPrice)
                .withMaxPrice(maxPrice)
                .withCheckOut(checkOut)
                .withShipStatus(shipStatus)
                .withDateStart(dateStart)
                .withDateEnd(dateEnd)
                .build();
        Page<?> orderPage = orderService.findAll(orderFilter);
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addDatas(orderPage.getContent())
                        .setPagination(new RestPagination(orderPage.getNumber() + 1,orderPage.getSize(),orderPage.getTotalElements()))
                        .setStatus(HttpStatus.OK.value())
                        .build()
                ,HttpStatus.OK);
    }

    //get order by id
    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public ResponseEntity<?> getOrder(@PathVariable(name="id") Integer id){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData(orderService.findById(id)).build()
                , HttpStatus.OK);
    }
    //create order
    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public ResponseEntity<?> createOrder(@RequestBody Order order){
//        Order order1 = orderService.save(order);
//        OrderDetailKey key = new OrderDetailKey();
//        Set<OrderDetail> newSet = new HashSet<>();
//        for (OrderDetail od: order.getOrderDetails()) {
//            OrderDetail nn = new OrderDetail();
//            nn.setQuanity(od.getQuanity());
//            key.setOrderID(order1.getId());
//            key.setProductID(nn.getProduct().getId());
//            nn.setUnitPrice(od.getUnitPrice());
//            nn.setOrderDetailKey(key);
//        }
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData(orderService.save(order)).build()
                ,HttpStatus.OK);
    }
    //update order status
    @RequestMapping(method = RequestMethod.PUT,value = "/edit")
    public ResponseEntity<?> updateOrderStatus(@RequestBody @Valid Integer id,int status){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData(orderService.updateStatus(id,status))
                        .build()
                ,HttpStatus.OK);
    }
    //delete order
    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name="id") Integer id){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData(orderService.delete(id))
                        .build()
                ,HttpStatus.OK);
    }
}
