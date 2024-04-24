package com.example.sbpostgresqltutorial.Transaction;

import com.example.sbpostgresqltutorial.Transaction.Model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
