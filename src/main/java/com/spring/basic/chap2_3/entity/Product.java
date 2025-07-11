package com.spring.basic.chap2_3.entity;


import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 전체 초기화 생성자

// @Data - getter, setter, 생성자, toString, equals, hashcode
// 한번에 다 만들어 주지만 필요에 따라서 사용하지 않는 것들도 있어
// 사용하지 않는 것이 좋다
public class Product {

    private Long serialNo;
    private String name;
    private int price;

}
