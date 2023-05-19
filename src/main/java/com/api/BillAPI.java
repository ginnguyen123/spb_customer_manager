package com.api;

import com.service.bill.IBillService;
import com.service.billDetail.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bills")
public class BillAPI {

    @Autowired
    private IBillService billService;

    @Autowired
    private IBillDetailService billDetailService;


}
