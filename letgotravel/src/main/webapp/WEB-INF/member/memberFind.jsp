<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!--     <title>아이디/비밀번호 찾기</title> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <style>

      /* 노말라이즈 */
      body,
      ul,
      li,
      h1 {
        margin: 0;
        padding: 0;
        list-style: none;
      }

      a {
        color: inherit;
        text-decoration: none;
      }

      label[for] {
        cursor: pointer;
        user-select: none;
      }

      /* 라이브러리 */
      .block {
        display: block;
      }

      .inline-block {
        display: inline-block;
      }

      .absolute-left {
        position: absolute;
        left: 0;
      }

      .absolute-right {
        position: absolute;
        right: 0;
      }

      .absolute-bottom {
        position: absolute;
        bottom: 0;
      }

      .absolute-top {
        position: absolute;
        top: 0;
      }

      .absolute-center {
        position: absolute;
        left: 50%;
        transform: translateX(-50%);
      }

      .absolute-middle {
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
      }

      .absolute-middle.absolute-center {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translateX(-50%) translateY(-50%);
      }

      .relative {
        position: relative;
      }

      .cell {
        float: left;
        box-sizing: border-box;
      }

      .cell-right {
        float: right;
        box-sizing: border-box;
      }

      .row::after {
        content: "";
        display: block;
        clear: both;
      }

      .clear-both {
        clear: both;
      }

      .img-box > img {
        width: 100%;
        display: block;
      }

      .con {
        margin-left: auto;
        margin-right: auto;
      }

      .margin-0-auto {
        margin-left: auto;
        margin-right: auto;
      }

      .text-align-center {
        text-align: center;
      }

      .text-align-right {
        text-align: right;
      }

      .line-height-0 {
        line-height: 0;
      }

      .line-height-0 > * {
        line-height: normal;
      }

      .width-30px {
        width: 30px;
      }

      .width-35px {
        width: 35px;
      }

      .width-40px {
        width: 35px;
      }

      .width-45px {
        width: 35px;
      }

      .width-50px {
        width: 50px;
      }

      .width-80px {
        width: 80px;
      }

      .width-100px {
        width: 100px;
      }

      .width-100p {
        width: 100%;
      }

      .width-60p {
        width: 60%;
      }

      .width-55p {
        width: 55%;
      }

      .width-50p {
        width: 50%;
      }

      .width-45p {
        width: 45%;
      }

      .width-40p {
        width: 40%;
      }

      .width-30p {
        width: 30%;
      }

      .width-35p {
        width: 35%;
      }

      .width-100p-except-30px {
        width: calc(100% - 30px);
      }

      .width-100p-except-35px {
        width: calc(100% - 35px);
      }

      .width-100p-except-40px {
        width: calc(100% - 40px);
      }

      .width-100p-except-50px {
        width: calc(100% - 50px);
      }

      .width-100p-except-80px {
        width: calc(100% - 80px);
      }

      .width-100p-except-100px {
        width: calc(100% - 100px);
      }

      .height-100p {
        height: 100%;
      }

      .height-50p {
        height: 50%;
      }

      .border-box {
        box-sizing: border-box;
      }

      .font-size-dot-8rem {
        font-size: 0.8rem;
      }

      .table-row {
        display: table;
      }

      .table-row.row::after {
        display: inline;
        clear: non;
      }

      .table-row > .cell {
        float: none;
        display: table-cell;
      }

      .border-red {
        border: 10px solid red;
      }

      .border-green {
        border: 10px solid green;
      }

      .border-blue {
        border: 10px solid blue;
      }

      .border-gold {
        border: 10px solid gold;
      }

      .border-pink {
        border: 10px solid pink;
      }

      /* 라이브러리 - flex */
      * > .flex {
        display: flex;
      }

      * > .flex-grow-1 {
        flex-grow: 1;
      }

      * > .flex-jc-c {
        justify-content: center;
      }

      * > .flex-jc-s {
        justify-content: flex-start;
      }

      * > .flex-jc-e {
        justify-content: flex-end;
      }

      * > .flex-jc-sb {
        justify-content: space-between;
      }

      * > .flex-jc-sa {
        justify-content: space-around;
      }

      * > .flex-ai-c {
        align-items: center;
      }

      * > .flex-ai-s {
        align-items: flex-start;
      }

      * > .flex-ai-e {
        align-items: flex-end;
      }

      * > .flex-as-s {
        align-self: stretch;
      }

      * > .flex-as-c {
        align-self: center;
      }

      * > .flex-as-s {
        align-self: flex-start;
      }

      * > .flex-as-e {
        align-self: flex-end;
      }

      * > .flex-column-nowrap {
        flex-flow: column nowrap;
      }

      * > .flex-column-wrap {
        flex-flow: column wrap;
      }

      * > .flex-row-wrap {
        flex-flow: row wrap;
      }

      .flex-ch-basis-0 > * {
        flex-basis: 0;
      }

      * > .flex-1-0-0 {
        flex: 1 0 0;
      }

      * > .bg-red {
        background-color: red;
      }

      /* 커스텀 */

      /* 고정형 .con 시작 */
      .con-min-width {
        min-width: 1370px;
      }

      .con {
        width: 1370px;
      }
      /* 고정형 .con 끝 */

      /* 반응형 타입 1 .con 시작 */
      /*
.con {
max-width:1370px;
}
*/
      /* 반응형 타입 1 .con 끝 */

      /* 반응형 타입 2(넓을때만 반응형) .con 시작 */
      /*
.con-min-width {
min-width:1370px;
}

.con {
margin-left:50px;
margin-right:50px;
}
*/
      /* 반응형 타입 2(넓을때만 반응형) .con 끝 */

      /* 공통 설정 */
      body {
        background-color: #f8f8ff;
        margin: 0;
        padding: 0;
        color: #333333;
      }

      h1,
      label {
        margin: 0;
      }

      .main-container {
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        padding: 20px;
      }

      /* 부모 요소 (content)의 overflow 속성을 visible로 변경 */
      .content {
        display: flex;
        max-width: 931px;
        width: 100%;
        background-color: rgba(217, 217, 217, 0.24); /* 🔥 투명도 24% 적용 */
        border-radius: 20px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        overflow: visible !important; /* 🔥 overflow를 visible로 변경 */
        position: relative; /* 절대 위치를 위한 부모 요소 설정 */
      }

      /* 왼쪽: 폼 섹션 (너비 525px 강제 적용) */
      .form-section {
        flex: 0 0 525px;
        max-width: 525px;
        width: 525px !important;
        display: flex;
        flex-direction: column;
        align-items: center;
        box-sizing: border-box;
      }

      .section-title1 {
        text-align: center;
        margin-top: 40px;
        margin-bottom: 35px;
        margin-left: 30px;
        margin-bottom: 20px;
        font-weight: normal;
        color: #333333;
        font-size: 22px;
      }

      .section-title2 {
        text-align: center;
        margin-top: 18px;
        margin-bottom: 35px;
        margin-left: 30px;
        margin-bottom: 20px;
        font-weight: normal;
        color: #333333;
        font-size: 22px;
      }

      .underline {
        display: inline-block;
        width: 160.11px;
        border-bottom: 1px solid black;
        padding-bottom: 5px;
        padding-left: 25px;
        padding-right: 25px;
      }

      /* 입력 필드 그룹 스타일 */
      .form-group-wrapper {
        width: 100%; /* 전체 폼 그룹 너비 동일 */
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 15px; /* 폼 요소 간격 */
      }

      /* 입력 필드 공통 스타일 */
      .form-group {
        width: 80%; /* 모든 입력 필드의 동일한 너비 */
        display: flex;
        flex-direction: column;
        align-items: flex-start;
      }

      .form-group label {
        width: 100%; /* 라벨 너비를 입력 필드와 동일하게 설정 */
        text-align: left;
        color: #333333;
      }

      .form-group input {
        width: 100%; /* 모든 입력 필드 동일 */
        padding: 10px;
        border: none;
        outline: none;
        font-size: 16px;
        border-bottom: 2px solid black; /* 입력 필드 하단 검정색 밑줄 */
        background: transparent;
      }

      /* 그룹 1의 입력 필드 너비와 라벨 너비를 그룹 2에 맞춤 */
      .group-1 .form-group label {
        width: 292px; /* 그룹 2 라벨 너비와 동일 */
        text-align: left;
      }

      .group-1 .form-group input {
        width: 237px; /* 그룹 2 입력 필드 너비와 동일 */
        padding: 10px;
        border: none;
        outline: none;
        font-size: 16px;
        border-bottom: 2px solid black; /* 입력 필드 하단 검정색 밑줄 */
        background: transparent;
      }

      /* 그룹 2는 변경하지 않음 */
      .group-2 .form-group label {
        width: auto; /* 그룹 2 라벨 유지 */
      }

      .group-2 .form-group input {
        width: auto; /* 그룹 2 입력 필드 유지 */
      }

      /* 이메일 텍스트 필드 + 버튼 정렬 */
      .email-group {
        width: 100%;
      }

      /* 이메일 입력 필드와 버튼을 한 줄에 정렬 */
      .input-btn-group {
        display: flex;
        align-items: center;
        gap: 10px; /* 입력 필드와 버튼 간격 */
        flex-wrap: nowrap; /* 줄 바꿈 방지 */
      }

      /* 이메일 입력 필드 너비 조정 */
      .input-btn-group input {
        width: 250.68px !important;
        padding: 10px;
        border: none;
        outline: none;
        font-size: 16px;
        border-bottom: 2px solid black; /* 하단 밑줄 */
        background: transparent;
      }

      /* 그룹 1과 그룹 2에서 이메일을 제외한 스타일 */
      .group-1 .form-group,
      .group-2 .form-group {
        width: 292px; /* 부모 컨테이너의 전체 너비 */
      }

      /* 입력 필드 설정 (고정 너비 292px) */
      .group-1 .form-group input,
      .group-2 .form-group input {
        width: 325.25px; /* 입력 필드 너비도 고정 */
        padding: 10px;
        border: none;
        outline: none;
        font-size: 16px;
        border-bottom: 2px solid black; /* 입력 필드 하단 밑줄 */
        background: transparent;
      }

      /* 입력 필드 내부 텍스트 (placeholder) */
      .form-group input::placeholder,
      .input-btn-group input::placeholder {
        color: #b6b6b6;
      }

      /* 입력 필드 및 제목 밑줄 색상 */
      .form-group input,
      .input-btn-group input,
      .underline {
        border-bottom: 2px solid #999999;
      }

      /* 이메일 발송 버튼 크기 유지 */
      .email-send-btn {
        width: 77px; /* 기존 크기 유지 */
        height: 22px;
        line-height: 22px;
        border-radius: 50px;
        background-color: #4fa3c4;
        color: white;
        text-align: center;
        border: none;
        cursor: pointer;
        font-size: 12px;
        padding: 0;
        flex-shrink: 0; /* 버튼 크기 유지 */
      }

      /* 이메일 발송 버튼 제외한 모든 버튼 */
      .find-id-btn,
      .find-password-btn,
      .cancel-btn {
        font-size: 20px;
      }

      /* 아이디 찾기 버튼 */
      .find-id-btn {
        display: block;
        margin: 20px auto;
        width: 132px;
        height: 32px;
        border-radius: 50px;
        background-color: #3b8eef;
        color: white;
        text-align: center;
        line-height: 32px;
        border: none;
        cursor: pointer;
        margin-top: 23px;
        margin-right: 70px;
        font-size: 18px;
      }

      /* 버튼 그룹 */
      .button-group {
        display: flex;
        justify-content: center; /* 중앙 정렬 */
        align-items: center;
        gap: 20px; /* 버튼 간격 */
        margin-top: 20px;
        margin-left: 30px;
      }


      /* 비밀번호 찾기 버튼 */
      .find-password-btn {
        width: 149px;
        height: 32px;
        border-radius: 50px;
        background-color: #3b8eef;
        color: white;
        text-align: center;
        line-height: 32px;
        border: none;
        cursor: pointer;
        margin-bottom: 52px;
        font-size: 18px;
      }

      /* 취소 버튼 */
      .cancel-btn {
        width: 78px;
        height: 32px;
        border-radius: 50px;
        color: #3b8eef;
        text-align: center;
        border: 1px solid #3b8eef;
        cursor: pointer;
        margin-bottom: 52px;
        font-size: 18px;
      }

      /* 오른쪽: 이미지 섹션 (너비 406px 유지) */
      .image-section {
        flex-shrink: 0; /* 자동 축소 방지 */
        flex-grow: 0; /* 크기 자동 증가 방지 */
        width: 406px; /* 고정된 너비 */
        height: 733px; /* 높이 유지 */
        display: flex;
        border-radius: 0 20px 20px 0;
        justify-content: center;
        align-items: center;
        background-color: black;
        position: relative;
        overflow: hidden;
        box-sizing: border-box; /* 내부 padding이 크기에 영향을 주지 않도록 설정 */
      }

      .thumbnail-img {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }

      /* 반투명 흰색 오버레이 */
      .image-section::after {
        content: "";
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(255, 255, 255, 0.08); /* 흰색 투명도 8% */
        pointer-events: none;
      }

      /* 이미지들을 감싸는 컨테이너 */
      .image-container {
        position: relative; /* 부모 요소 기준 */
        width: 100%; /* 전체 너비 */
        height: 100%; /* 전체 높이 */
        overflow: visible;
      }

      /* 리본 이미지 (좌측 상단) */
      .ribbon-img {
        position: absolute;
        top: 10px; /* 원하는 위치 조정 */
        left: -1030px;
        top: 600px;
        width: 238.18px; /* 이미지 크기 */
        height: auto;
      }

      /* 깃털 이미지 (중앙) */
      .feather-img {
        position: absolute;
        top: 0 px;
        left: -121px;
        transform: translateY(-50%); /* 중앙 정렬 */
        width: 231.77px;
        height: auto;
      }

      /* 수풀 이미지 (좌측 하단) */
      .grass-img {
        position: absolute;
        bottom: -100px;
        left: -650px;
        width: 231.77px;
        height: auto;
      }
    </style>
  </head>
  <body>
   <%@ include file="/WEB-INF/header1.jsp" %>
   <nav class="breadcrumb">
     <span>홈</span> &gt; <span>아이디/비밀번호 찾기</span>
   </nav>
    
    <main class="main-container">
      <div class="content">
        <!-- 왼쪽: 아이디 / 비밀번호 찾기 -->
        <section class="form-section">
          <!-- 아이디 찾기 -->
          <h1 class="section-title1">
            <span class="underline">아이디 찾기</span>
          </h1>
          <form>
            <div class="form-group-wrapper group-1">
              <!-- 이름 -->
              <div class="form-group">
                <label for="name">이름</label>
                <input
                  type="text"
                  id="name"
                  placeholder="회원정보상 입력된 이름을 입력해주세요"
                />
              </div>
              <!-- 연락처 -->
              <div class="form-group">
                <label for="contact">연락처</label>
                <input
                  type="text"
                  id="contact"
                  placeholder="회원정보상 입력된 연락처를 입력해주세요"
                />
              </div>
            </div>
            <button type="submit" class="find-id-btn">아이디 찾기</button>
          </form>

          <!-- 비밀번호 찾기 -->
          <h1 class="section-title2">
            <span class="underline">비밀번호 찾기</span>
          </h1>
          <form>
            <div class="form-group-wrapper group-2">
              <!-- 아이디 -->
              <div class="form-group">
                <label for="id">아이디</label>
                <input
                  type="text"
                  id="id"
                  placeholder="아이디를 입력해주세요"
                />
              </div>
              <!-- 이메일 -->
              <div class="form-group email-group">
                <label for="email">이메일</label>
                <div class="input-btn-group">
                  <input
                    type="email"
                    id="email"
                    placeholder="이메일을 입력해주세요"
                  />
                  <button type="button" class="email-send-btn">
                    이메일 발송
                  </button>
                </div>
              </div>
              <!-- 인증번호 -->
              <div class="form-group">
                <label for="auth-code">인증번호</label>
                <input
                  type="text"
                  id="auth-code"
                  placeholder="인증번호를 입력해주세요"
                />
              </div>
            </div>
            <div class="button-group">
              <button type="submit" class="find-password-btn">
                비밀번호 찾기
              </button>
              <button type="button" class="cancel-btn">취소</button>
            </div>
          </form>
        </section>

        <!-- 오른쪽: 이미지 -->
        <section class="image-section">
          <img
            src="https://drive.google.com/thumbnail?id=1k82Hp-4GV3C6FR94fqjsIAS5tJTb6W7p"
            alt="이미지"
            class="thumbnail-img"
          />
        </section>

        <!-- 리본, 깃털, 수풀 이미지 컨테이너 -->
        <div class="image-container">
          <img
            src="https://drive.google.com/thumbnail?id=1adqmv3G7bY8NZ2CbCv9hcpl-wk_ak2wm"
            alt="리본 이미지"
            class="ribbon-img"
          />
          <img
            src="https://drive.google.com/thumbnail?id=1nNeFQETVQaj1Yd6QWKuqMjFYLC9oGs0e"
            alt="깃털 이미지"
            class="feather-img"
          />
          <img
            src="https://drive.google.com/thumbnail?id=17JAxZ7KmoLVQb_07R5RCpHppsiI7LSvC"
            alt="수풀 이미지"
            class="grass-img"
          />
        </div>
      </div>
    </main>
    
	<%@ include file="/WEB-INF/footer.jsp" %>
  </body>
</html>
