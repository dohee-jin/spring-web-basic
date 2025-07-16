package com.spring.basic.score.controller;

import com.spring.basic.score.dto.request.scoreWriteDTO;
import com.spring.basic.score.dto.response.ScoreResponseDTO;
import com.spring.basic.score.entity.Score;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v1/scores")
public class ScoreController {

    private Long id = 1L;
    Map<Long, Score> scoreStore = new HashMap<>();

    // 기본 데이터 생성
    public ScoreController() {
        scoreStore.put(id, Score.builder()
                        .id(id)
                        .name("박수포자")
                        .eng(95)
                        .kor(55)
                        .math(15)
                        .total((95+55+15))
                        .average((95+55+15)/3)
                        .build());
        id++;
    }

    // 전체 학생 성적 가져오기 - get
    @GetMapping
    public ResponseEntity<?> list(@RequestParam(value = "sort") String sort) {

        // 석차 계산하기
        List<Score> ranklist = scoreStore.values()
                .stream()
                .sorted(Comparator.comparing(Score::getAverage).reversed())
                .collect(toList());

        List<ScoreResponseDTO> scoreList = new ArrayList<>();

        for(int i = 0; i < ranklist.size(); i++) {
            Score score = ranklist.get(i);
            ScoreResponseDTO dto = ScoreResponseDTO.from(score);
            dto.setRanking(i+1);
            scoreList.add(dto);
        }

        List<ScoreResponseDTO> sortedScoreList = scoreList.stream()
                                                .sorted(getComparator(sort))
                                                .collect(toList());

       /* List<ScoreResponseDTO> scoreList = scoreStore.values()
                .stream()
                .map((Score score) -> ScoreResponseDTO.from(score))
                .collect(toList());*/

        return ResponseEntity
                .ok()
                .body(sortedScoreList);

    }

    private Comparator<ScoreResponseDTO> getComparator(String sort) {
        Comparator<ScoreResponseDTO> comparing = null;

        switch (sort){
            case "name":
                comparing = Comparator.comparing(dto -> dto.getName());
                break;

            case "average":
                comparing = Comparator.comparing(dto -> dto.getAverage());
                break;

            case "id":
            default:
                comparing = Comparator.comparing(dto -> dto.getId());
                break;
        }

        return comparing;
    }

    // 학생 성적 입력하기 - post
    @PostMapping
    public ResponseEntity<?> write(@RequestBody @Valid scoreWriteDTO dto,
                                   BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            // getFieldErrors 는 리턴값으로 리스트를 반환하기 때문에 forEach를 돌릴 수 있음
            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });

            return ResponseEntity
                    .badRequest()
                    .body(errorMap);
        }

        Score score = scoreWriteDTO.from(dto);
        score.setId(id++);
        score.setTotal((score.getEng() + score.getKor() + score.getMath()));
        score.setAverage((double) score.getTotal() / 3);

        scoreStore.put(score.getId(), score);

        return ResponseEntity
                .ok()
                .body(score.getName() + "의 성적이 입력되었습니다.");

    }

    // 학생 성적 데이터 삭제하기
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Score removed = scoreStore.remove(id);

        if(removed == null) {
            return ResponseEntity
                    .badRequest()
                    .body(removed.getName() + "의 성적을 찾을 수 없습니다.");
        }

        return ResponseEntity
                .ok()
                .body(removed.getName() + "의 성적이 삭제되었습니다.");
    }

}
