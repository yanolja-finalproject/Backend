package com.yanolja_final.domain.poll.repository;

import com.yanolja_final.domain.poll.entity.Poll;
import com.yanolja_final.domain.poll.entity.PollAnswer;
import com.yanolja_final.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollAnswerRepository extends JpaRepository<PollAnswer, Long> {
    boolean existsByUserIdAndPollId(Long userId, Long pollId);
}
