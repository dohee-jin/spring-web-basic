package com.spring.basic.score.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.basic.score.entity.Score;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

// 클라이언트에게 성적을 보내줄 때 사용할 응답 dto
public class ScoreResponseDTO {

    private Long id;

    // 이름의 첫글자와 마지막 글자를 제외하고 마스킹
    @JsonProperty("maskingName")
    private String name;

    @JsonProperty("avg")
    private double average;

    @JsonProperty("sum")
    private int total;

    @JsonProperty("rank")
    private int ranking;

    // 엔티티를 dto로 변환하는 정적 팩토리 메소드
    public static ScoreResponseDTO from(Score score){
        return ScoreResponseDTO.builder()
                .id(score.getId())
                .name(maskingName(score.getName()))
                .total(score.getTotal())
                .average(score.getAverage())
                .build();
    }

    // 이름 마스킹
    private static String maskingName(String originName) {
        String masking = originName.charAt(0) + "";
        for(int i = 1; i < originName.length(); i++) {
            if(i == originName.length() - 1) {
                masking += originName.charAt(i);
                break;
            }
            masking += '*';
        }

        return masking;
    }

}
