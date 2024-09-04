package com.payment.demo.client;
import com.payment.demo.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "user-service",url="http://localhost:7777")
public interface UserClient {
    @GetMapping("/users/user/{email}")
    Optional<UserResponse> getUserByEmail(@PathVariable String email);

    @GetMapping("/users")
    List<UserResponse> getUsers();
}