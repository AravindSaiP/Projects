package com.major_project.ewallet.pocketbook.repository;

import com.major_project.ewallet.pocketbook.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Optional<Wallet> findByUserId(Long userId);
}
