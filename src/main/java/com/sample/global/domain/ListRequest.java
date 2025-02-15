package com.sample.global.domain;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@ParameterObject
public class ListRequest {

    private static final int DEFAULT_LIST_SIZE = 10;
    private static final int DEFAULT_PAGE = 1;

    @Parameter(description = "한 페이지 아이템 갯수", example = "10")
    private Integer size;

    @Min(1)
    @Parameter(description = "조회 페이지", example = "1")
    private Integer currentPage;

    public ListRequest() {
        this.size = DEFAULT_LIST_SIZE;
        this.currentPage = DEFAULT_PAGE;
    }

    public Pageable toPageableAndDateSorted() {
        final Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        return PageRequest.of(currentPage - 1, size, sort);
    }

    public Pageable toPageable() {
        return PageRequest.of(currentPage - 1, size);
    }
}
