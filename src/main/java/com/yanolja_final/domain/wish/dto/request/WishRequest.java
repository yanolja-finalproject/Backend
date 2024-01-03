package com.yanolja_final.domain.wish.dto.request;

import lombok.Getter;

@Getter
public record WishRequest (
    Long packageId
)
{ }
