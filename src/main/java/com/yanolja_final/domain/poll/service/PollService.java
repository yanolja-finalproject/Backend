package com.yanolja_final.domain.poll.service;

import com.yanolja_final.domain.poll.controller.request.PollAnswerRequest;
import com.yanolja_final.domain.poll.controller.response.NotVotedResponse;
import com.yanolja_final.domain.poll.controller.response.VotedResponse;
import com.yanolja_final.domain.poll.entity.Poll;
import com.yanolja_final.domain.poll.entity.PollAnswer;
import com.yanolja_final.domain.poll.exception.PollAnswerException;
import com.yanolja_final.domain.poll.exception.PollNotFoundException;
import com.yanolja_final.domain.poll.repository.PollAnswerRepository;
import com.yanolja_final.domain.poll.repository.PollRepository;
import com.yanolja_final.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PollService {

    private final PollRepository pollRepository;
    private final PollAnswerRepository pollAnswerRepository;

    @Transactional
    public void savePollAnswer(User user, PollAnswerRequest request) {
        Poll poll = findPollMaxId();

        if (pollAnswerRepository.existsByUserIdAndPollId(user.getId(), poll.getId())) {
            throw new PollAnswerException();
        }

        PollAnswer pollAnswer = request.toEntity(user, poll);
        pollAnswerRepository.save(pollAnswer);
    }

    public Object findActivePoll(User user) {
        Poll poll = findPollMaxId();
        if (pollAnswerRepository.existsByUserIdAndPollId(user.getId(), poll.getId())) {
            return VotedResponse.from(poll);
        } else {
            return NotVotedResponse.from(poll);
        }
    }

    private Poll findPollMaxId() {
        return pollRepository.findPollWithMaxId().orElseThrow(PollNotFoundException::new);
    }
}
