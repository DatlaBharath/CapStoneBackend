package com.payment.demo.client;

import com.payment.demo.config.RazorpayClientConfig;
import com.payment.demo.dto.RazorpayRequest;
import com.payment.demo.dto.RazorpayResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "razorpayClient", url = "https://api.razorpay.com/v1",configuration = RazorpayClientConfig.class)
public interface RazorpayClient {
    @PostMapping("/payment_links")
    RazorpayResponse createPaymentLink(@RequestBody RazorpayRequest request);
}