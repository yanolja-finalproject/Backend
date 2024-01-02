package com.yanolja_final.domain.user.service;

import com.yanolja_final.domain.user.repository.UserRepository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduledTasks {

    private final UserRepository userRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredMembers() {

        LocalDateTime oneYearAgo = LocalDateTime.ofInstant(
            Instant.now().minusSeconds(60 * 60 * 24 * 365), ZoneId.of("UTC"));

        userRepository.deleteByDeletedAtBefore(oneYearAgo);
    }
}
