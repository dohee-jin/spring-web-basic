<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>투두리스트 만들기</title>
    <link rel="stylesheet" href="/css/main.css">
    <script src="/js/todo.js" defer></script>
</head>
<body>

    <!-- 전체 애플리케이션을 감싸는 컨테이너 -->
    <div class="todo-container">

        <!-- 헤더: 타이틀 -->
        <header class="app-header">
            <h1 class="main-title">투두리스트</h1>
        </header>

        <!-- 새로운 할 일을 추가하는 섹션 -->
        <section class="new-todo">
            <form id="todo-form">
                <input type="text" id="todo-input" placeholder="새로운 할 일을 입력하세요..." autocomplete="off">
                <button type="submit" class="add-button" title="할 일 추가">+</button>
            </form>
        </section>

        <!-- 할 일 목록이 표시되는 섹션 -->
        <section class="todos">
            <ul id="todo-list">
                <!--
                    자바스크립트로 동적으로 생성될 할 일 아이템 예시
                    <li class="todo-item" data-id="1">
                        <div class="item-container">
                            <input type="checkbox" class="todo-check">
                            <span class="todo-text">저녁 장보기</span>
                            <button class="delete-button">
                                <i class="fas fa-trash-alt"></i>
                            </button>
                        </div>
                    </li>
                 -->
            </ul>
        </section>

        <!-- 필터링 및 관리 기능을 담는 푸터 -->
        <footer class="app-footer">
            <div class="todos-left">
                남은 할 일: <span id="todos-left-count">0</span>개
            </div>
            <div class="filters">
                <button id="filter-all" class="active">모두 보기</button>
                <button id="filter-active">진행 중</button>
                <button id="filter-completed">완료됨</button>
            </div>
            <button id="clear-completed" class="clear-button">완료된 항목 삭제</button>
        </footer>

    </div>

</body>
</html>