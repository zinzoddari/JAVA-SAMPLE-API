package com.sample.notice.domain;

import com.sample.infra.orm.AuditInformation;
import com.sample.infra.orm.converter.BooleanYnConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.ObjectUtils;

@Getter
@Entity
@Table(name = "notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends AuditInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id", nullable = false)
    private Long id;

    @NotNull
    @Comment("제목")
    @Column(name = "title", nullable = false, length = 64)
    private String title;

    @NotNull
    @Comment("내용")
    @Column(name = "content", length = 2000)
    private String content;

    @NotNull
    @Comment("배너 이미지")
    @Column(name = "banner", nullable = false, length = 2000)
    private String banner;

    @NotNull
    @Comment("사용 여부")
    @Convert(converter = BooleanYnConverter.class)
    @Column(name = "use_yn", nullable = false, columnDefinition = "char(1)")
    private boolean isUse;

    @Builder(access = AccessLevel.PRIVATE)
    protected Notice(String title, String content, String banner) {
        this.title = title;
        this.content = content;
        this.banner = banner;
        this.isUse = true;
    }

    public static Notice from(final String title, final String content, final String banner) {
        return Notice.builder()
                .title(title)
                .content(content)
                .banner(banner)
                .build();
    }

    public void adminModify(final String title, final String content, final String banner) {
        if (!ObjectUtils.isEmpty(title)) {
            this.title = title;
        }

        if (!ObjectUtils.isEmpty(content)) {
            this.content = content;
        }

        if (!ObjectUtils.isEmpty(banner)) {
            this.banner = banner;
        }
    }

    public void adminRemove() {
        this.isUse = false;
    }
}
