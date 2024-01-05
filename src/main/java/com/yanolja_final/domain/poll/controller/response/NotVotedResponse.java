package com.yanolja_final.domain.poll.controller.response;

import com.yanolja_final.domain.poll.entity.Poll;

public record NotVotedResponse(
    boolean alreadySubmitted,

    String title,
    Long pollId,
    String[] A,
    String[] B
) {

    public static NotVotedResponse from(Poll poll) {
        return new NotVotedResponse(
            false,
            poll.getTitle(),
            poll.getId(),
            slideString(poll.getAQuestion()),
            slideString(poll.getBQuestion())
        );
    }

    private static String[] slideString(String pollQuestion) {
        return pollQuestion.split("\n");
    }
}
