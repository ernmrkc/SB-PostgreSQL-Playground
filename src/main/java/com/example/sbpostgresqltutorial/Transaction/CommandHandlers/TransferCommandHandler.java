package com.example.sbpostgresqltutorial.Transaction.CommandHandlers;

import com.example.sbpostgresqltutorial.Event.TransferEventPublisher;
import com.example.sbpostgresqltutorial.Transaction.BankAccountRepository;
import com.example.sbpostgresqltutorial.Transaction.Model.BankAccount;
import com.example.sbpostgresqltutorial.Transaction.Model.TransferDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TransferCommandHandler {

    private final BankAccountRepository bankAccountRepository;
    private final TransferEventPublisher transferEventPublisher;

    public TransferCommandHandler(BankAccountRepository bankAccountRepository,
                                  TransferEventPublisher transferEventPublisher) {
        this.bankAccountRepository = bankAccountRepository;
        this.transferEventPublisher = transferEventPublisher;
    }

    public ResponseEntity<?> transfer(TransferDTO transfer){
        Optional<BankAccount> fromAccount = bankAccountRepository.findById(transfer.getFromUser());
        Optional<BankAccount> toAccount = bankAccountRepository.findById(transfer.getToUser());

        if(fromAccount.isEmpty() || toAccount.isEmpty()){
            throw new RuntimeException("User nor found");
        }

        BankAccount from = fromAccount.get();
        BankAccount to = toAccount.get();

        // Add & Deduct -- Always First Add Function
        add(to, transfer.getAmount());
        // At this point, will have added, but not checked other account
        System.out.println("After adding, before deducting");
        System.out.println(bankAccountRepository.findById(to.getName()));

        deduct(from, transfer.getAmount());
        // Never called Repository.save()
        // once we add @Transactional, will auto do this

        transferEventPublisher.publish(this, transfer);

        return ResponseEntity.ok("Success");
    }

    private void deduct(BankAccount bankAccount, double amount){
        if(bankAccount.getBalance() < amount){
            throw new RuntimeException("Not enough money");
        }

        bankAccount.setBalance(bankAccount.getBalance() - amount);
    }

    private void add(BankAccount bankAccount, double amount){
        bankAccount.setBalance(bankAccount.getBalance() + amount);
    }
}
