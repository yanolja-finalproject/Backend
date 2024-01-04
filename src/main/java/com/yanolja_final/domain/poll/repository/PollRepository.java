package com.yanolja_final.domain.poll.repository;

import com.yanolja_final.domain.poll.entity.Poll;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PollRepository extends JpaRepository<Poll,Long> {

    @Query("SELECT e FROM Poll e WHERE e.id = (SELECT MAX(p.id) FROM Poll p)")
    Optional<Poll> findPollWithMaxId();
}
