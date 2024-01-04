package com.yanolja_final.domain.poll.controller.request;

import jakarta.validation.constraints.NotNull;

public record PollAnswerRequest(

    @NotNull(message = "선택된 값이 없습니다.")
    Character choose
) {

}
