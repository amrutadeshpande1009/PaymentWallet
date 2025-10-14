package com.paymentWallet.paymentWallet.controller;

import com.paymentWallet.paymentWallet.dto.*;
import com.paymentWallet.paymentWallet.entity.User;
import com.paymentWallet.paymentWallet.service.UserService;
import com.paymentWallet.paymentWallet.service.WalletService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basePath/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private WalletService walletService;


    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })

    @PostMapping
    public ResponseEntity<EntityModel<User>> registerUser(@RequestBody UserRegistrationDTO userDto) {
        User user = userService.registerUser(userDto);
        EntityModel<User> resource = EntityModel.of(user);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getBalance(user.getId())).withRel("check-balance"));
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<BalanceResponseDTO> getBalance(@PathVariable Long userId) {
        double balance = userService.getWalletBalance(userId);
        return ResponseEntity.ok(new BalanceResponseDTO(userId, balance));
    }

    @PatchMapping("/wallet")
    public ResponseEntity<EntityModel<String>> addAmount(@RequestBody AddAmountDTO dto){
        walletService.addAmountToWallet(dto);

        EntityModel<String> resource = EntityModel.of("Amount added successfully");

        // Add a HATEOAS link to check the user's balance
        resource.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(UserController.class).getBalance(dto.getUserId()))
                .withRel("check-balance"));

        return ResponseEntity.ok(resource);
    }

    @PostMapping("/transfer")
    public ResponseEntity<FundTransferResponse> transferFunds(@RequestBody FundTransferRequest request) {
        FundTransferResponse response = walletService.transferFunds(request);
        return ResponseEntity.ok(response);
    }

}