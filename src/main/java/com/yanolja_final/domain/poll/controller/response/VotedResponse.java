package com.yanolja_final.domain.poll.controller.response;

import com.yanolja_final.domain.poll.dto.PollResultDTO;
import com.yanolja_final.domain.poll.entity.Poll;

public record VotedResponse(
    boolean alreadySubmitted,
    String title,
    PollResultDTO A,
    PollResultDTO B
) {

    public static VotedResponse from(Poll poll) {
        return new VotedResponse(
            true,
            poll.getTitle(),
            getAPollResultInfo(poll),
            getBPollResultInfo(poll)
        );
    }

    private static PollResultDTO getAPollResultInfo(Poll poll) {
        return new PollResultDTO(
            poll.getAName(),
            poll.getAHashtag(),
            calculatePercentage(poll.getACount(), poll.getACount() + poll.getBCount())
        );
    }

    private static PollResultDTO getBPollResultInfo(Poll poll) {
        return new PollResultDTO(
            poll.getBName(),
            poll.getBHashtag(),
            calculatePercentage(poll.getBCount(), poll.getACount() + poll.getBCount())
        );
    }

    private static int calculatePercentage(int count, int totalCount) {
        if (totalCount == 0) {
            return 0;
        }
        return (int) Math.round(((double) count / totalCount) * 100.0);
    }
}
