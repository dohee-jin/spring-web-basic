<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Web Study</title>
    <script src="/book.js" defer></script>
    <style>
        /* 기본 스타일 초기화 및 설정 */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            line-height: 1.6;
        }

        /* 전체를 감싸는 컨테이너 */
        .container {
            max-width: 800px;
            margin: 40px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #4a4a4a;
            margin-bottom: 30px;
        }

        h2 {
            color: #333;
            border-bottom: 2px solid #eaeaea;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        /* 폼 컨테이너 스타일 */
        .form-container {
            padding: 20px;
            background-color: #fafafa;
            border-radius: 8px;
            margin-bottom: 30px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
            font-size: 1rem;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1rem;
        }

        .form-group input:focus {
            outline: none;
            border-color: #007bff;
        }

        /* 버튼 공통 스타일 */
        button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1rem;
            font-weight: bold;
            transition: background-color 0.3s ease, transform 0.1s ease;
        }

        button:active {
            transform: scale(0.98);
        }

        /* 등록 버튼 스타일 */
        .add-btn {
            width: 100%;
            background-color: #007bff;
            color: white;
        }

        .add-btn:hover {
            background-color: #0056b3;
        }

        /* 목록 컨테이너 스타일 */
        .list-container {
            padding: 20px;
        }

        .book-list {
            list-style: none;
        }

        /* 각 책 아이템 스타일 */
        .book-list li {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px;
            background-color: #f9f9f9;
            border: 1px solid #eee;
            border-radius: 5px;
            margin-bottom: 10px;
            transition: box-shadow 0.3s ease;
        }

        .book-list li:hover {
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.08);
        }

        /* li 안의 제목과 저자 태그 스타일 */
        .book-list li h2 {
            font-size: 1.2rem;
            border: none; /* h2 기본 스타일 덮어쓰기 */
            margin: 0;
            padding: 0;
            color: #333;
        }

        /* li 안의 가격 태그 스타일 */
        .book-list li span {
            font-size: 1rem;
            color: #555;
            margin: 0 15px;
        }

        /* 삭제 버튼 스타일 */
        .del-btn {
            background-color: #dc3545;
            color: white;
        }

        .del-btn:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>
            📚 도서 관리 시스템 (총 <span class="book-count">0</span>권)
        </h1>

        <div class="form-container">
            <h2>📖 새 도서 등록</h2>
            <form id="book-form">
                <div class="form-group">
                    <label for="title">제목</label>
                    <input type="text" id="title" placeholder="책 제목을 입력하세요." required>
                </div>
                <div class="form-group">
                    <label for="author">저자</label>
                    <input type="text" id="author" placeholder="저자 이름을 입력하세요." required>
                </div>
                <div class="form-group">
                    <label for="price">가격</label>
                    <input type="number" id="price" placeholder="가격을 입력하세요." required>
                </div>
                <button type="submit" class="add-btn">등록하기</button>
            </form>
        </div>

        <div class="list-container">
            <h2>📑 등록된 도서 목록</h2>
            <ul class="book-list">
                <%-- 자바스크립트를 통해 동적으로 책 목록이 채워질 공간입니다. --%>
            </ul>
        </div>

    </div>

</body>
</html>