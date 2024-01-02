package com.yanolja_final.domain.user.repository;

import com.yanolja_final.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long Id);

    void deleteByDeletedAtBefore(LocalDateTime dateTime);

    Optional<User> findByPhoneNumber(String phoneNumber);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.deletedAt > :dateTime")
    Optional<User> findSoftDeletedByEmail(@Param("email") String email,
        @Param("dateTime") LocalDateTime dateTime);
}
