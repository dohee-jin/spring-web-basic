package com.spring.basic.chap5_5.service;

import com.spring.basic.chap5_5.dto.request.FeedCreateRequest;
import com.spring.basic.chap5_5.dto.response.FeedDetailResponse;
import com.spring.basic.chap5_5.dto.response.FeedListResponse;
import com.spring.basic.chap5_5.entity.Feed;
import com.spring.basic.chap5_5.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

// 데이터 변환 및 예외처리, 트랜잭션 담당
// Controller -> Service -> Repository
@RequiredArgsConstructor
@Service
public class FeedService {

    private final FeedRepository feedRepository;

    // 목록요청에 대한 중간 처리
    public List<FeedListResponse> listProcess() {
        // 저장소에게 접근해서 목록을 가져오라고 시킴(의존객체에 위임)
        List<Feed> feeds = feedRepository.getFeeds();
        // 원본으로 주지말고 가릴 건 가리고 예쁘게 만들어서 컨트롤러에게 전달
        List<FeedListResponse> responses = feeds.stream()
                .map(feed -> FeedListResponse.from(feed))
                .collect(toList());

        return responses;
    }

    // 개별조회 요청에 대한 중간 처리
    public FeedDetailResponse findOneProcess(Long id) {
        Feed feed = feedRepository.getFeed(id);

        if(feed == null) {
            throw new IllegalArgumentException("존재하지 않는 ID - " + id);
        }

        return FeedDetailResponse.from(feed);


    }

    // 생성 요청에 대한 중간 처리
    public void createProcess(FeedCreateRequest dto) {
        feedRepository.save(FeedCreateRequest.toEntity(dto));
    }

    // 삭제 요청에 대한 중간 처리
    public void removeProcess(Long id) {
        boolean flag = feedRepository.delete(id);
        if(!flag) {
            throw new IllegalArgumentException("존재하지 않는 ID - " + id);
        }
    }

}
