<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>처방받은약</title>
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
	  html, body {
		height: 100%; 
	  }
	  body { 
		display : flex;
		flex-direction: column;
	  }
      button {
      	cursor: pointer;
      }
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

      /* 전체 페이지 너비 설정 */
      body {
        max-width: 1920px; /* 전체 페이지 너비를 1920px로 고정 */
        margin: 0 auto; /* 중앙 정렬 */
      }

      .title {
        width: 1200px; /* 너비를 강제로 1920px로 설정 */
        text-align: center;
        font-size: 32px;
        padding: 0px;
        margin: 0 auto; /* 수평 중앙 정렬 */
        margin-top: 50px;
        margin-bottom: 110px;
        color: #333333;
      }

      section:not(.allheaders) {
        width: 1200px;
        margin: 0 auto;
        font-size: 14px;
        color: #757575;
        text-align: left;
        margin-top: 15px;
      }

      /* 테이블을 감싸는 컨테이너 */
      .table-container {
        position: relative; /* 버튼 위치 기준점 설정 */
        width: 1200px; /* 테이블과 동일한 너비 */
        margin: 0 auto; /* 중앙 정렬 */
        flex: 1 1 auto;
      }

      /* 업데이트 버튼을 테이블 위 우측에 배치 */
      .custom-btn {
        background-color: #3b8eef;
        color: white; /* 흰색 글자 */
        border: none; /* 테두리 제거 */
        border-radius: 20px;
        width: 149px; /* 버튼 너비 */
        height: 40px; /* 버튼 높이 */
        font-size: 24px; /* 글자 크기 */
        position: absolute;
        top: -60px;
        right: 0;
      }

      /* 테이블 스타일 */
      table {
        margin: 0 auto;
        border-collapse: collapse;
        border-spacing: 0;
        width: 1200px;
        table-layout: fixed;
        color: #333333;
      }

      /* 열(컬럼)별 너비 설정 */
      thead tr:first-child th:nth-child(1), 
      tbody tr td:nth-child(1) {
      width: 145px; /* 번호 */
      }

      thead tr:first-child th:nth-child(2), 
      tbody tr td:nth-child(2) {
       width: 210px; /* 조제일자 */
      }

      thead tr:first-child th:nth-child(3), 
      tbody tr td:nth-child(3) {
      width: 475px; /* 처방기관 */
      }

      thead tr:first-child th:nth-child(4), 
      tbody tr td:nth-child(4) {
       width: 370px; /* 조제기관 */
      }

      /* 모든 행과 셀에 적용되는 기본 스타일 */
      th,
      td {
        padding: 0;
        margin: 0;
        border-top: 1px solid #d9d9d9;
        border-bottom: 1px solid #d9d9d9;
        text-align: center;
        box-sizing: border-box;
        font-size: 20px;
        vertical-align: middle;
      }

      thead tr th{
        font-weight: normal;
      }

      /* 테이블 헤더 높이 (51px) */
      thead tr:first-child th {
        height: 49.49px;
        line-height: 49.49px;
        border-top: 2px solid #333333;
        border-bottom: 1px solid #333333;
        box-sizing: border-box;
      }
      
      /* 본문 행 높이 (50px) */
      tbody tr {
        height: 48.5px;
        line-height: 48.5px;
        border-top: 1px solid #d9d9d9;
        border-bottom: 1px solid #d9d9d9;
        box-sizing: border-box;
        color: #333333;
      }

      /* 마지막 줄 높이 조정 */
      tbody tr:last-child td {
        height: 48.5px; /* 50px에서 1.5px 줄임 */
        line-height: 48.5px;
        border-bottom: 2px solid #333333;
        box-sizing: border-box;
      }

      /* 헤더 배경색 적용 */
      thead {
        background-color: #3b8eef;
        color: white;
      }

      .pagination {
        display: flex;
        justify-content: center; /* 버튼을 중앙 정렬 */
        align-items: center;
        gap: 2px; /* 버튼 간 간격을 2px로 고정 */
        width: 1200px;
        height: 25px;
        margin: 60px auto 0; /* 중앙 정렬 */
        font-size: 0; /* 공백 문자 문제 해결 */
        flex-wrap: nowrap; /* 줄바꿈 방지 */
      }

      .pagination a {
        display: inline-block;
        width: 25px;
        height: 25px;
        line-height: 25px;
        text-align: center;
        font-size: 20px;
        text-decoration: none;
        border: none;
        border-radius: 5px;
        color: #333333;
      }

      /* 클릭한 페이지 스타일 */
      .pagination a.active {
        background-color: #3b8eef;
        color: white;
      }
    </style>
  </head>
  <body>
    <%@ include file="/WEB-INF/header1.jsp" %>
    <nav class="breadcrumb">
      <span>홈</span> &gt; <span>처방받은약</span> &gt; <span>처방전 목록</span>
    </nav>
    
    <article class="title">처방받은약</article>

    <!-- 테이블을 감싸는 div 추가 -->
    <div class="table-container">
      <button class="custom-btn">업데이트</button>
      <table>
        <thead>
          <tr>
            <th>번호</th>
            <th>조제일자</th>
            <th>처방기관</th>
            <th>조제기관</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>2024-11-11</td>
            <td><a href="#">병원 이름</a></td>
            <td>약국 이름</td>
          </tr>
          <tr>
            <td>2</td>
            <td>2024-11-11</td>
            <td><a href="#">병원 이름</a></td>
            <td>약국 이름</td>
          </tr>
          <tr>
            <td>3</td>
            <td>2024-11-11</td>
            <td><a href="#">병원 이름</a></td>
            <td>약국 이름</td>
          </tr>
          <tr>
            <td>4</td>
            <td>2024-11-11</td>
            <td><a href="#">병원 이름</a></td>
            <td>약국 이름</td>
          </tr>
          <tr>
            <td>5</td>
            <td>2024-11-11</td>
            <td><a href="#">병원 이름</a></td>
            <td>약국 이름</td>
          </tr>
          <tr>
            <td>6</td>
            <td>2024-11-11</td>
            <td><a href="#">병원 이름</a></td>
            <td>약국 이름</td>
          </tr>
          <tr>
            <td>7</td>
            <td>2024-11-11</td>
            <td><a href="#">병원 이름</a></td>
            <td>약국 이름</td>
          </tr>
          <tr>
            <td>8</td>
            <td>2024-11-11</td>
            <td><a href="#">병원 이름</a></td>
            <td>약국 이름</td>
          </tr>
          <tr>
            <td>9</td>
            <td>2024-11-11</td>
            <td><a href="#">병원 이름</a></td>
            <td>약국 이름</td>
          </tr>
        </tbody>
      </table>
      <section>
	      ※처방기관명을 클릭하시면 처방받으신 내용을 확인하실 수 있습니다.
	    </section>
	
	    <div class="pagination">
	      <a href="#" id="prev" onclick="changePage('prev')" style="display: none">&lt;</a>
	      <!-- 이전 페이지 -->
	      <a href="#" class="page-btn active" onclick="changePage(1)">1</a>
	      <a href="#" class="page-btn" onclick="changePage(2)">2</a>
	      <a href="#" class="page-btn" onclick="changePage(3)">3</a>
	      <a href="#" class="page-btn" onclick="changePage(4)">4</a>
	      <a href="#" class="page-btn" onclick="changePage(5)">5</a>
	      <a href="#" class="page-btn" onclick="changePage(6)">6</a>
	      <a href="#" class="page-btn" onclick="changePage(7)">7</a>
	      <a href="#" class="page-btn" onclick="changePage(8)">8</a>
	      <a href="#" class="page-btn" onclick="changePage(9)">9</a>
	      <a href="#" class="page-btn" onclick="changePage(10)">10</a>
	      <a href="#" id="next" onclick="changePage('next')">&gt;</a>
	      <!-- 다음 페이지 -->
	    </div>
    </div>
    
    <%@ include file="/WEB-INF/footer.jsp" %>

    <script>
      let currentPage = 1; // 현재 페이지
      const totalPages = 10; // 전체 페이지 수

      function changePage(page) {
        if (page === "prev" && currentPage > 1) {
          currentPage--;
        } else if (page === "next" && currentPage < totalPages) {
          currentPage++;
        } else if (typeof page === "number") {
          currentPage = page;
        }

        updatePagination();
      }

      function updatePagination() {
        // 모든 버튼에서 active 클래스 제거
        document.querySelectorAll(".pagination .page-btn").forEach((btn) => {
          btn.classList.remove("active");
        });

        // 현재 페이지 버튼에 active 클래스 추가
        document
          .querySelector(`.pagination .page-btn:nth-child(${currentPage + 1})`)
          .classList.add("active");

        // 1페이지일 때 '<' 버튼 숨기기
        document.getElementById("prev").style.display =
          currentPage === 1 ? "none" : "inline-block";

        // 마지막 페이지일 때 '>' 버튼 숨기기
        document.getElementById("next").style.display =
          currentPage === totalPages ? "none" : "inline-block";
      }

      // 초기 업데이트 실행
      // updatePagination();
    </script>
  </body>
</html>
