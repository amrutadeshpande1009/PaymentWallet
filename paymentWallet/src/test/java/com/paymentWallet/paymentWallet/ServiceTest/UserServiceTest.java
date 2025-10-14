package com.paymentWallet.paymentWallet.ServiceTest;

import com.paymentWallet.paymentWallet.dto.UserRegistrationDTO;
import com.paymentWallet.paymentWallet.entity.User;
import com.paymentWallet.paymentWallet.entity.Wallet;
import com.paymentWallet.paymentWallet.exception.ResourceNotFoundException;
import com.paymentWallet.paymentWallet.repository.UserRepository;
import com.paymentWallet.paymentWallet.repository.WalletRepository;
import com.paymentWallet.paymentWallet.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private WalletRepository walletRepository;

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

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.registerUser(dto);

        assertNotNull(savedUser);
        assertEquals("amruta", savedUser.getUsername());
        assertEquals("secure123", savedUser.getPassword());
        assertEquals("amruta@example.com", savedUser.getEmailId());
    }

    @Test
    void testGetWalletBalance_Success() {
        Long userId = 1L;

        Wallet wallet = new Wallet();
        wallet.setId(101);
        wallet.setBalance(1500.0);

        when(walletRepository.findByUserId(userId)).thenReturn(Optional.of(wallet));

        double balance = userService.getWalletBalance(userId);

        assertEquals(1500.0, balance);
    }


    @Test
    void testGetWalletBalance_WalletNotFound() {
        Long userId = 2L;

        when(walletRepository.findByUserId(userId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.getWalletBalance(userId);
        });

        assertEquals("Wallet not found for userId: 2", exception.getMessage());
    }
}
