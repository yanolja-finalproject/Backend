package com.yanolja_final.domain.poll.controller.response;

import com.yanolja_final.domain.poll.dto.PollResultDTO;
import com.yanolja_final.domain.poll.entity.Poll;

public record VotedResponse(
    boolean alreadySubmitted,
    String title,
    PollResultDTO A,
    PollResultDTO B,
    Integer totalCount
) {

    public static VotedResponse from(Poll poll) {
        return new VotedResponse(
            true,
            poll.getTitle(),
            PollResultDTO.getAPollResultInfo(poll),
            PollResultDTO.getBPollResultInfo(poll),
            poll.getACount()+ poll.getBCount()
        );
    }
}
