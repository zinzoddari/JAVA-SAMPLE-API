package com.sample.notice.service;

import com.sample.global.domain.ListRequest;
import com.sample.global.domain.ListResponse;
import com.sample.notice.dto.NoticeListResponse;
import com.sample.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public ListResponse<NoticeListResponse> getList(final ListRequest request) {
        return ListResponse.from(noticeRepository.findByList(request.toPageableAndDateSorted()), NoticeListResponse::from);
    }
}
