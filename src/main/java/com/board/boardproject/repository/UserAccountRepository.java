package com.board.boardproject.repository;

import com.board.boardproject.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}
