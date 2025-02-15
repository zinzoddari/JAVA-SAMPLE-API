package com.sample.notice.repository;

import com.sample.notice.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query(value = """
            SELECT n
            FROM Notice n
            WHERE n.isUse = true
            """
    )
    @Meta(comment = "NoticeRepository.findByList : 공지사항 리스트를 조회합니다.")
    Page<Notice> findByList(Pageable pageable);
}
