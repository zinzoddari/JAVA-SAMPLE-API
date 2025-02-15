package com.sample.notice.service;

import com.sample.global.domain.ListRequest;
import com.sample.global.domain.ListResponse;
import com.sample.global.exception.NotFoundException;
import com.sample.notice.domain.Notice;
import com.sample.notice.dto.NoticeListResponse;
import com.sample.notice.dto.NoticeResponse;
import com.sample.notice.dto.NoticeSaveRequest;
import com.sample.notice.dto.NoticeUpdateRequest;
import com.sample.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public ListResponse<NoticeListResponse> getList(final ListRequest request) {
        return ListResponse.from(noticeRepository.findByList(request.toPageableAndDateSorted()), NoticeListResponse::from);
    }

    @Transactional(readOnly = true)
    public NoticeResponse getDetail(final Long id) {
        return NoticeResponse.from(noticeRepository.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    @Transactional
    public void register(final NoticeSaveRequest request) {
        noticeRepository.save(request.toEntity());
    }

    @Transactional
    public void modify(final Long id, final NoticeUpdateRequest request) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        notice.adminModify(request.title(), request.content(), request.banner());
    }

    @Transactional
    public void remove(final Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        notice.adminRemove();
    }
}
