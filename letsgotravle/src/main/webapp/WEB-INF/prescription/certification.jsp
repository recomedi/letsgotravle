<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>본인인증</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/auth.css">
</head>
<body>
  <header>
    <h1>본인인증</h1>
    <div id="header-placeholder1"></div>
  <nav class="breadcrumb">
    <a href="#">Home</a> &gt; <a href="#">Projects</a> &gt; <a href="#">Flowable</a>
  </nav>
  </header>
  <main>
    <!-- Step 1 -->
    <div id="step-1" class="step step-1">
      <label for="id-front">주민번호</label>
      <input type="text" id="id-front" class="id-front" placeholder="주민번호 앞자리" maxlength="6">
      -
      <input type="password" id="id-back" class="id-back" placeholder="주민번호 뒷자리" maxlength="7">
      <label for="name">이름</label>
      <input type="text" id="name" class="name" placeholder="이름입력">
    </div>

    <!-- Step 2 -->
    <div id="step-2" class="step hidden">
      <label for="tel-company">통신사</label>
      <select id="tel-company">
        <option value="skt">SKT</option>
        <option value="kt">KT</option>
        <option value="uplus">U+</option>
        <option value="etc">알뜰폰</option>
      </select>
      <label for="phone-number">전화번호</label>
      <input type="text" id="phone-number" placeholder="전화번호 입력">
    </div>

    <!-- Step 3 -->
    <div id="step-3" class="step hidden">
      <label for="captcha">보안문자</label>
      <div class="captcha-container">
        <img src="${pageContext.request.contextPath}/resources/images/captcha.png" alt="보안문자">
        <input type="text" id="captcha" placeholder="보안문자 입력">
        <button id="check-captcha" class="check-captcha">확인</button>
      </div>
    </div>

    <!-- Step 4 -->
    <div id="step-4" class="step hidden">
      <label for="sms-code">SMS인증 번호</label>
      <input type="text" id="sms-code" placeholder="인증번호 입력">
      <button id="verify-sms" class="verify-sms">SMS인증확인</button>
    </div>
  </main>
   <!-- Include GSAP and Plugins -->
   <script src="https://cdn.jsdelivr.net/npm/gsap@3.12.5/dist/gsap.min.js"></script>
   <script src="https://cdn.jsdelivr.net/npm/gsap@3.12.5/dist/ScrollTrigger.min.js"></script>
   <script src="https://unpkg.com/lenis@1.1.14/dist/lenis.min.js"></script>
   <!-- 풋터 -->
         <div id="footer-placeholder"></div>
  <script src="${pageContext.request.contextPath}/resources/js/auth.js"></script>
</body>
</html>
