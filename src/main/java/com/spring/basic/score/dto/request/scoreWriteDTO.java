package com.spring.basic.score.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.basic.score.entity.Score;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

// 성적 입력용 DTO
public class scoreWriteDTO {

    @JsonProperty("studentName")

    // 공백, null을 모두 확인해줌
    @NotEmpty(message = "학생이름은 필수입니다.")
    //@NotBlank()
    private String name;

    @JsonProperty("korean")
    @NotEmpty(message = "성적 입력은 필수입니다.")
    @Max(100)
    private Integer kor;

    @JsonProperty("english")
    @NotEmpty(message = "성적 입력은 필수입니다.")
    @Max(100)
    private Integer eng;

    @JsonProperty("math")
    @NotEmpty(message = "성적 입력은 필수입니다.")
    @Max(100)
    private Integer math;

    // dto -> score 엔티티 변환용 정적 팩토리 메소드
    public static Score from(scoreWriteDTO dto){
        return Score.builder()
                .name(dto.getName())
                .kor(dto.getKor())
                .eng(dto.getEng())
                .math(dto.getMath())
                .build();
    }
}
