<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관광지와 음식점을 선택해주세요.</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

    <!-- 폰트어썸 불러오기 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
</head>
<body>
    <div class="wrap flex flex-column">
      <%@ include file="/WEB-INF/header1.jsp" %>
	    <nav class="breadcrumb">
	      <span>홈</span> &gt; <span>여행일정</span> &gt; <span>장소선택</span>
	    </nav>
	    
        <div class="inner travle" id="travle-sights">
            
            <section class="contents">
                <ul class="step flex justify-content-between">
                    <li class="step-item finished flex justify-content-center align-items-center"><i class="fa-solid fa-check"></i></li>
                    <li class="relative step-item finished flex justify-content-center align-items-center"><i class="fa-solid fa-check"></i></li>
                    <li class="relative step-item on flex justify-content-center align-items-center"><i class="fa-solid fa-pencil"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                </ul>
            
                <h3 class="main-title center mb-70">🤔 "일본 / 도쿄"의 관광지와 음식점을 선택해주세요.</h3>

                <div class="flex">
                    <div class="col">
                        <div class="flex justify-content-between">                    
                            <div class="btn-box flex">
                                <button class="btn green">관광지</button> <button class="btn btn2">음식점</button>
                            </div>
                            <button class="btn blue">추가</button>
                        </div>

                        <div class="check-box pt-10">
                            <input type="checkbox" id="ck1" class="none"><label class="relative mt-20 pl-35 inline-block" for="ck1"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
                            <input type="checkbox" id="ck2" class="none"><label class="relative mt-20 pl-35 inline-block" for="ck2"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
                            <input type="checkbox" id="ck3" class="none"><label class="relative mt-20 pl-35 inline-block" for="ck3"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
                            <input type="checkbox" id="ck4" class="none"><label class="relative mt-20 pl-35 inline-block" for="ck4"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
                            <input type="checkbox" id="ck5" class="none"><label class="relative mt-20 pl-35 inline-block" for="ck5"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
                            <input type="checkbox" id="ck6" class="none"><label class="relative mt-20 pl-35 inline-block" for="ck6"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
                            <input type="checkbox" id="ck7" class="none"><label class="relative mt-20 pl-35 inline-block" for="ck7"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
                            <input type="checkbox" id="ck8" class="none"><label class="relative mt-20 pl-35 inline-block" for="ck8"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
                            <input type="checkbox" id="ck9" class="none"><label class="relative mt-20 pl-35 inline-block" for="ck9"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
                            <input type="checkbox" id="ck10" class="none"><label class="relative mt-20 pl-35 inline-block" for="ck10"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
                        </div>
                    </div>

                    <p class="border-vertical ml-25 mr-25"></p>
    
                    <div class="col info">
                        <div class="flex mb-20 justify-content-between">
                            <img src="${pageContext.request.contextPath}/resources/images/image 178.png" alt="루브르박물관">
                            <img src="${pageContext.request.contextPath}/resources/images/image 179.png" alt="파리지도">
                        </div>
                        <p>에펠탑(프랑스어: Tour Eiffel, [tuʁ ɛfɛl], 영어: Eiffel Tower)은 프랑스 파리 마르스 광장에 위치한 격자형 철골 타워이다. 1889년에 프랑스 혁명 100주년을 맞이하여 파리 만국 박람회를 개최하였는데 이 박람회를 상징할만한 기념물로 에펠탑을 건축하였다.[1] 박람회가 열린 마르스 광장 출입 관문에 위치해있다. 프랑스의 대표 건축물인 에펠탑은 격자 구조로 이루어져 파리에서 가장 높은 건축물이며, 매년 수백만 명이 방문할 정도로 파리에서 빼놓을 수 없는 세계적으로 유명한 관광명소이다. 이 탑은 공모전을 통해 선정된 프랑스 공학자 귀스타브 에펠의 작품으로 이를 디자인한 그의 이름을 따서 명명했다.</p>
                    </div>
                </div>
                
                <div class="btn-box center mb-70 mt-50 flex justify-content-center">
                    <a href="${pageContext.request.contextPath}/travel/travel-modify.do" class="btn blue">다음</a>
                    <button class="btn">뒤로</button>
                </div>
            </section>
        </div>
        <%@ include file="/WEB-INF/footer.jsp" %>
    </div>
</body>
</html>