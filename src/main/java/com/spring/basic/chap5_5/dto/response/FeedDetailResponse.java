package com.spring.basic.chap5_5.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.basic.chap5_5.entity.Feed;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedDetailResponse {

    private String writer;

    @JsonProperty("feed_content")
    private String content;

    private int view;

    // 엔티티를 DTO로 변환하는 유틸 메소드
    public static FeedDetailResponse from(Feed feed) {
        return FeedDetailResponse.builder()
                .writer(feed.getWriter())
                .content(feed.getContent())
                .view(feed.getViewCount())
                .build();
    }
}
