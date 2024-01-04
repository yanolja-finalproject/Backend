package com.yanolja_final.domain.poll.controller.request;

import com.yanolja_final.domain.poll.entity.Poll;
import com.yanolja_final.domain.poll.entity.PollAnswer;
import com.yanolja_final.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;

public record PollAnswerRequest(

    @NotNull(message = "선택된 값이 없습니다.")
    Character choose
) {

    public PollAnswer toEntity(User user, Poll poll) {
        return PollAnswer.builder()
            .answer(choose)
            .user(user)
            .poll(poll)
            .build();
    }
}
