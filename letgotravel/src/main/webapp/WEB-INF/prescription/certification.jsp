<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>본인인증</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/auth.css">
</head>
<body>
   <%@ include file="/WEB-INF/header1.jsp" %>
   
  <nav class="breadcrumb">
    <a href="#">Home</a> &gt; <a href="#">Projects</a> &gt; <a href="#">Flowable</a>
  </nav>
   
   <h1 class="auth">본인인증</h1>
  <main class="authmain">
    <!-- Step 1 -->
  <div class="identy">
    <div id="step-1" class="step step-1">
      <label for="id-front">주민번호</label>
      <div class="id-number">
        <input type="text" id="id-front" class="id-front" placeholder="주민번호 앞자리" maxlength="6">
        <p class="hyphen">⎯</p>
        <input type="password" id="id-back" class="id-back" placeholder="주민번호 뒷자리" maxlength="7">
      </div>
      <label for="name">이름</label>
      <input type="text" id="name" class="name" placeholder="이름입력">
    </div>
  </div>

    <!-- Step 2 -->
    <div id="step-2" class="step hidden step-2">
      <label for="phone-number">전화번호</label>
      <select id="tel-company" class="tel-company">
        <option value="skt">SKT</option>
        <option value="kt">KT</option>
        <option value="uplus">U+</option>
        <option value="etc">알뜰폰</option>
      </select>
      
      <input type="text" id="phone-number" class="phone-number" placeholder="전화번호 입력">
    </div>

    <!-- Step 3 -->
    <div id="step-3" class="step hidden">
      <label for="captcha" class="captcha">보안문자</label>
      <div class="captcha-container">
        <img src="${pageContext.request.contextPath}/resources/images/captcha.png" alt="보안문자">
        <button class="reflash"><img src="${pageContext.request.contextPath}/resources/images/reflash.png" class="reflash-img"></button>
        <input type="text" id="captcha" class="captcha-input" placeholder="보안문자 입력">
      </div>
      <button id="check-captcha" class="check-captcha">확인</button>
    </div>

    <!-- Step 4 -->
    <div id="step-4" class="step hidden step-4">
      <label for="sms-code">SMS인증 번호</label>
      <input type="text" id="sms-code" class="sms-code" placeholder="인증번호 입력">
      <button id="verify-sms" class="verify-sms">SMS인증확인</button>
      <p class="auth-text-title">인증번호 문자를 못 받으셨나요?</p>

      <p class="auth-text-body">•입력하신 인증정보가 일치하지 않을 경우, 인증번호 문자는 발송 되지 않습니다.</p>
        
      <p class="auth-text-body">•인증번호가 문자로 수신되지 않을 경우 정확한 정보로 재시도 해 주시기 바랍니다.</p>
    </div>
    
  </main>
   <!-- Include GSAP and Plugins -->
   <script src="https://cdn.jsdelivr.net/npm/gsap@3.12.5/dist/gsap.min.js"></script>
   <script src="https://cdn.jsdelivr.net/npm/gsap@3.12.5/dist/ScrollTrigger.min.js"></script>
   <script src="https://unpkg.com/lenis@1.1.14/dist/lenis.min.js"></script>
   <!-- 풋터 -->
   <%@ include file="/WEB-INF/footer.jsp" %>
  <script src="${pageContext.request.contextPath}/resources/js/auth.js"></script>
</body>
</html>
