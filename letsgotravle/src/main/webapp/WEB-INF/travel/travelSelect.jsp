<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>도시를 선택해주세요.</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

    <!-- 폰트 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <!-- 폰트어썸 불러오기 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
</head>
<body>
    <div class="wrap flex flex-column">
      <%@ include file="/WEB-INF/header1.jsp" %>
	    <nav class="breadcrumb">
	      <span>홈</span> &gt; <span>여행일정</span> &gt; <span>도시선택</span>
	    </nav>
	    
        <div class="inner travle" id="travle-select">
            
            <section class="contents">
                <ul class="step flex justify-content-between">
                    <li class="step-item finished flex justify-content-center align-items-center"><i class="fa-solid fa-check"></i></li>
                    <li class="relative step-item on flex justify-content-center align-items-center"><i class="fa-solid fa-pencil"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                </ul>
            
                <h3 class="main-title center mb-70">🤔 도시를 선택해주세요.</h3>

                <div class="row">
                    <h4 class="title mb-1">1. 프랑스 / 파리 &nbsp; <input type="radio" name="city" id="paris1" class="none"><label class="btn" for="paris1">선택</label></h4>
                    <div class="col flex mb-1">
                        <div class="select-item"><img src="${pageContext.request.contextPath}/resources/images/image 175.png" alt="에펠탑"></div>
                        <div class="select-item"><img src="${pageContext.request.contextPath}/resources/images/image 178.png" alt="루브르박물관"></div>
                        <div class="select-item"><img src="${pageContext.request.contextPath}/resources/images/image 179.png" alt="파리지도"></div>
                    </div>                    
                    <div class="col flex mb-2">
                        <div class="select-item">
                            <p class="sub-title mb-1">🎡 대표관광지</p>
                            <p class="text">· 에펠탑</p>
                            <p class="text">· 루브르박물관</p>
                            <p class="text">· 개선문</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">🍴 대표음식</p>
                            <p class="text">· 바게트</p>
                            <p class="text">· 꼬꼬뱅</p>
                            <p class="text">· 수플레</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">💰 환율</p>
                            <p class="text">· 1,480원</p>
                        </div>
                    </div>
                    <div class="col flex mb-2">
                        <div class="select-item">
                            <p class="sub-title mb-1">🌞 평균날씨</p>
                            <p class="text">· 20˚C ~ 25˚C 선선한 날씨</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">💸 성수기여부</p>
                            <p class="text">· 성수기</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">🍔 물가</p>
                            <p class="text">· 한국 대비 비슷</p>
                        </div>
                    </div>
                    <div class="col flex mb-2">
                        <div class="select-item">
                            <p class="sub-title mb-1">👮 치안</p>
                            <p class="text">· 좋음</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">😷 위생</p>
                            <p class="text">· 좋음</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">🚌 교통</p>
                            <p class="text">· 지하철, 버스 등 편리한 편</p>
                        </div>
                    </div>
                    <div class="col flex">
                        <div class="select-item">
                            <p class="sub-title mb-1">🚨 주의해야 하는 기간 </p>
                            <p class="text">· 없음</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">🎆 축제 & 행사</p>
                            <p class="text">· 벚꽃축제</p>
                        </div>
                    </div>
                </div>
                
                <p class="border mt-2 mb-2"></p>

                <div class="row">
                    <h4 class="title mb-1">1. 프랑스 / 파리 &nbsp; <input type="radio" name="city" id="paris2" class="none"><label class="btn" for="paris2">선택</label></h4>
                    <div class="col flex mb-1">
                        <div class="select-item"><img src="${pageContext.request.contextPath}/resources/images/image 175.png" alt="에펠탑"></div>
                        <div class="select-item"><img src="${pageContext.request.contextPath}/resources/images/image 178.png" alt="루브르박물관"></div>
                        <div class="select-item"><img src="${pageContext.request.contextPath}/resources/images/image 179.png" alt="파리지도"></div>
                    </div>                    
                    <div class="col flex mb-2">
                        <div class="select-item">
                            <p class="sub-title mb-1">🎡 대표관광지</p>
                            <p class="text">· 에펠탑</p>
                            <p class="text">· 루브르박물관</p>
                            <p class="text">· 개선문</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">🍴 대표음식</p>
                            <p class="text">· 바게트</p>
                            <p class="text">· 꼬꼬뱅</p>
                            <p class="text">· 수플레</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">💰 환율</p>
                            <p class="text">· 1,480원</p>
                        </div>
                    </div>
                    <div class="col flex mb-2">
                        <div class="select-item">
                            <p class="sub-title mb-1">🌞 평균날씨</p>
                            <p class="text">· 20˚C ~ 25˚C 선선한 날씨</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">💸 성수기여부</p>
                            <p class="text">· 성수기</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">🍔 물가</p>
                            <p class="text">· 한국 대비 비슷</p>
                        </div>
                    </div>
                    <div class="col flex mb-2">
                        <div class="select-item">
                            <p class="sub-title mb-1">👮 치안</p>
                            <p class="text">· 좋음</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">😷 위생</p>
                            <p class="text">· 좋음</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">🚌 교통</p>
                            <p class="text">· 지하철, 버스 등 편리한 편</p>
                        </div>
                    </div>
                    <div class="col flex">
                        <div class="select-item">
                            <p class="sub-title mb-1">🚨 주의해야 하는 기간 </p>
                            <p class="text">· 없음</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">🎆 축제 & 행사</p>
                            <p class="text">· 벚꽃축제</p>
                        </div>
                    </div>
                </div>

                <p class="border mt-2 mb-2"></p>

                <div class="row">
                    <h4 class="title mb-1">1. 프랑스 / 파리 &nbsp; <input type="radio" name="city" id="paris3" class="none"><label class="btn" for="paris3">선택</label></h4>
                    <div class="col flex mb-1">
                        <div class="select-item"><img src="${pageContext.request.contextPath}/resources/images/image 175.png" alt="에펠탑"></div>
                        <div class="select-item"><img src="${pageContext.request.contextPath}/resources/images/image 178.png" alt="루브르박물관"></div>
                        <div class="select-item"><img src="${pageContext.request.contextPath}/resources/images/image 179.png" alt="파리지도"></div>
                    </div>                    
                    <div class="col flex mb-2">
                        <div class="select-item">
                            <p class="sub-title mb-1">🎡 대표관광지</p>
                            <p class="text">· 에펠탑</p>
                            <p class="text">· 루브르박물관</p>
                            <p class="text">· 개선문</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">🍴 대표음식</p>
                            <p class="text">· 바게트</p>
                            <p class="text">· 꼬꼬뱅</p>
                            <p class="text">· 수플레</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">💰 환율</p>
                            <p class="text">· 1,480원</p>
                        </div>
                    </div>
                    <div class="col flex mb-2">
                        <div class="select-item">
                            <p class="sub-title mb-1">🌞 평균날씨</p>
                            <p class="text">· 20˚C ~ 25˚C 선선한 날씨</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">💸 성수기여부</p>
                            <p class="text">· 성수기</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">🍔 물가</p>
                            <p class="text">· 한국 대비 비슷</p>
                        </div>
                    </div>
                    <div class="col flex mb-2">
                        <div class="select-item">
                            <p class="sub-title mb-1">👮 치안</p>
                            <p class="text">· 좋음</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">😷 위생</p>
                            <p class="text">· 좋음</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">🚌 교통</p>
                            <p class="text">· 지하철, 버스 등 편리한 편</p>
                        </div>
                    </div>
                    <div class="col flex">
                        <div class="select-item">
                            <p class="sub-title mb-1">🚨 주의해야 하는 기간 </p>
                            <p class="text">· 없음</p>
                        </div>
                        <div class="select-item">
                            <p class="sub-title mb-1">🎆 축제 & 행사</p>
                            <p class="text">· 벚꽃축제</p>
                        </div>
                    </div>
                </div>

                <div class="btn-box center mb-70 mt-50 flex justify-content-center">
                    <button class="btn green">더보기</button>
                    <a href="${pageContext.request.contextPath}/travel/travel-sights.do" class="btn blue">다음</a>
                    <button class="btn">뒤로</button>
                </div>
            </section>
        </div>
        <%@ include file="/WEB-INF/footer.jsp" %>
    </div>
</body>
</html>