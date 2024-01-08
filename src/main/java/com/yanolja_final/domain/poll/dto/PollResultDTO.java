package com.yanolja_final.domain.poll.dto;

import com.yanolja_final.domain.poll.entity.Poll;

public record PollResultDTO(
    String linkName,
    String linkHashTag,
    Integer count,
    Integer percentage
) {

    public static PollResultDTO getAPollResultInfo(Poll poll) {
        return new PollResultDTO(
            poll.getAName(),
            poll.getAHashtag(),
            poll.getACount(),
            calculatePercentage(poll.getACount(), poll.getACount() + poll.getBCount())
        );
    }

    public static PollResultDTO getBPollResultInfo(Poll poll) {
        return new PollResultDTO(
            poll.getBName(),
            poll.getBHashtag(),
            poll.getBCount(),
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
