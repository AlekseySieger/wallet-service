package service;

import exception.CommonException;
import exception.GlobalExceptionHandler;
import exception.InsufficientFundsException;
import exception.WalletNotFoundException;
import model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.WalletRepository;

import java.util.UUID;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Transactional
    public void updateBalance(UUID walletId, long amount, String operationType) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (operationType.equals("WITHDRAW")) {
            if (wallet.getBalance() < amount) {
                throw new InsufficientFundsException("Insufficient funds");
            }
            wallet.setBalance(wallet.getBalance() - amount);
        } else if (operationType.equals("DEPOSIT")) {
            wallet.setBalance(wallet.getBalance() + amount);
        } else {
            throw new CommonException("Wrong operation type");
        }

        walletRepository.save(wallet);
    }

    public Wallet getBalance(UUID walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
    }

    public void create(long balance) {
        Wallet wallet = new Wallet();
        wallet.setBalance(balance);
        walletRepository.save(wallet);
    }
}