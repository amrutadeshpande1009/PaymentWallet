package com.paymentWallet.paymentWallet.ControllerTest;


import com.paymentWallet.paymentWallet.controller.UserController;
import com.paymentWallet.paymentWallet.dto.*;
import com.paymentWallet.paymentWallet.entity.User;
import com.paymentWallet.paymentWallet.service.UserService;
import com.paymentWallet.paymentWallet.service.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private WalletService walletService;

    @Test
    void testRegisterUser_Success() {
        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setUsername("amruta");
        dto.setPassword("secure123");
        dto.setEmailId("amruta@example.com");

        User user = new User();
        user.setId(1);
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmailId(dto.getEmailId());

        when(userService.registerUser(dto)).thenReturn(user);

        ResponseEntity<EntityModel<User>> response = userController.registerUser(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user.getId(), response.getBody().getContent().getId());
        assertTrue(response.getBody().getLinks().hasLink("check-balance"));
    }

    @Test
    void testGetBalance_Success() {
        Long userId = 1L;
        double balance = 1500.0;

        when(userService.getWalletBalance(userId)).thenReturn(balance);

        ResponseEntity<BalanceResponseDTO> response = userController.getBalance(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void testAddAmount_Success() {
        AddAmountDTO dto = new AddAmountDTO();
        dto.setUserId(1L);
        dto.setAmount(500.0);

        doNothing().when(walletService).addAmountToWallet(dto);

        ResponseEntity<EntityModel<String>> response = userController.addAmount(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Amount added successfully", response.getBody().getContent());
        assertTrue(response.getBody().getLinks().hasLink("check-balance"));
    }

    @Test
    void testTransferFunds_Success() {
        FundTransferRequest request = new FundTransferRequest();
        request.setToUserId(1L);
        request.setFromUserId(2L);
        request.setAmount(BigDecimal.valueOf(300));

        FundTransferResponse transferResponse = new FundTransferResponse();

        transferResponse.setMessage("value");

        transferResponse.setRemainingBalance(BigDecimal.valueOf(800.0));

        when(walletService.transferFunds(request)).thenReturn(transferResponse);

        ResponseEntity<FundTransferResponse> response = userController.transferFunds(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
