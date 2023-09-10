package com.belyaeva.repository;

import com.belyaeva.model.Chat;
import com.belyaeva.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByUsersContains(User user);

}
