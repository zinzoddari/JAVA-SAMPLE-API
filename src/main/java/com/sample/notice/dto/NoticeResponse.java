package com.sample.notice.dto;

import com.sample.notice.domain.Notice;

import java.time.LocalDateTime;

public record NoticeResponse(
        Long id,
        String title,
        String content,
        String banner,
        LocalDateTime createdDate) {

    public static NoticeResponse from(final Notice notice) {
        return new NoticeResponse(notice.getId(), notice.getTitle(), notice.getContent(), notice.getBanner(), notice.getCreatedDate());
    }
}
