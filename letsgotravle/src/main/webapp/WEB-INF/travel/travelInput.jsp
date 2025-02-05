<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>여행지를 입력해주세요.</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

    <!-- 폰트어썸 불러오기 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
</head>
<body>
    <div class="wrap flex flex-column">
      <%@ include file="/WEB-INF/header1.jsp" %>
	    <nav class="breadcrumb">
	      <span>홈</span> &gt; <span>여행일정</span> &gt; <span>여행지입력</span>
	    </nav>
		  
        <div class="inner travle" id="travle-input">
            
            <section class="contents">
                <ul class="step flex justify-content-between">
                    <li class="step-item on flex justify-content-center align-items-center"><i class="fa-solid fa-pencil"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                </ul>
            
                <h3 class="main-title center mb-70">🤔 여행지를 입력해주세요.</h3>

                <div class="mb-2 center">
                    <input type="text" placeholder="여행지를 입력해주세요." name="keyword" class="w-200">
                </div>

                <div class="map">

                </div>

                <div class="btn-box center mb-70 mt-50 flex justify-content-center">
                    <a href="${pageContext.request.contextPath}/travel/travel-sights.do" class="btn blue">다음</a>
                    <button class="btn">뒤로</button>
                </div>
            </section>
        </div>
        <%@ include file="/WEB-INF/footer.jsp" %>
    </div>
</body>
</html>