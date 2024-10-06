package controller;


import dto.WalletDto;
import dto.WalletRequestDto;
import model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.WalletService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateBalance(@RequestBody WalletRequestDto walletRequestDto) {
        walletService.updateBalance(
                walletRequestDto.getWalletId(),
                walletRequestDto.getAmount(),
                walletRequestDto.getOperationType()
        );
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<?> getBalance(@PathVariable UUID walletId) {
        Wallet wallet = walletService.getBalance(walletId);
        return ResponseEntity.ok(wallet.getBalance());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody WalletDto dto) {
        walletService.create(dto.getBalance());
    }
}