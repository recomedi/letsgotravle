<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 정보 변경</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/jejugothic.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/MyPage.css">
</head>
<body>
   <nav class="breadcrumb">
     <span>홈</span> &gt; <span>아이디/비밀번호 찾기</span>
   </nav>
   <div class="page">
        <%@ include file="/WEB-INF/header1.jsp" %>
    <div class="container">
        <div class="banner">
            <img class="icon" src="${pageContext.request.contextPath}/resources/images/Mypage_icon.png" alt="">
        </div>
        <div class="form">
            <h2>회원 정보 변경</h2>
        <div class="password-change">
            <label>비밀번호 변경</label>
            <input type="password" placeholder="비밀번호를 입력해주세요">
            <label>비밀번호 확인</label>
            <input type="password" placeholder="비밀번호를 다시한번 입력해주세요">
        </div>
        <div class="profile-modify">
            <label>닉네임 변경</label>
            <div style="display: flex; align-items: center; justify-content: center;">
                <input type="text" class="short-input">
                <button class="check-btn">중복확인</button>
            </div>
            <label>연락처 변경</label>
            <input type="text">
            <label>이메일 변경</label>
            <input type="email">
            <label>현재 비밀번호</label>
            <input type="password" placeholder="회원 정보를 변경 하려면 현재 비밀번호를 입력해주세요">
        </div>
        </div>
        <div class="buttons">
            <div class="update-back">
                <button class="update">수정</button>
                <button class="back">취소</button>
            </div>
            <button class="delete">회원탈퇴</button>
        </div>
        <div class="image-container">
            <img src="${pageContext.request.contextPath}/resources/images/Mypage_blue.png" alt="추가 이미지" style="position: absolute; top: 130px; right: -70px; width: 190px; height: 190px; transform:rotate(20deg);">
            <img src="${pageContext.request.contextPath}/resources/images/Mypage_green.png" alt="추가 이미지" style="position: absolute; top: -50px; left: -80px; width: 150px; height: 150px; transform:rotate(20deg);">
            <img src="${pageContext.request.contextPath}/resources/images/Mypage_pink.png" alt="추가 이미지" style="position: absolute; bottom: -50px; left: -100px; width: 180px; height: 180px; transform:rotate(40deg);">
        </div> 
      </div>
      <%@ include file="/WEB-INF/footer.jsp" %>
    </div>

</body>
</html>