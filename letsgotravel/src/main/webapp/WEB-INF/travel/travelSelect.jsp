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
    
     <!-- 폰트어썸 불러오기 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
</head>
<body>
    <div class="wrap flex flex-column">
      <%@ include file="/WEB-INF/header1.jsp" %>
	    <nav class="breadcrumb">
	      <span>홈</span> &gt; <span>여행일정</span> &gt; <span>도시선택</span>
	    </nav>
	    
        <div class="inner travel" id="travel-select">
            
            <section class="contents">
                <ul class="step flex justify-content-between">
                    <li class="step-item finished flex justify-content-center align-items-center"><i class="fa-solid fa-check"></i></li>
                    <li class="relative step-item on flex justify-content-center align-items-center"><i class="fa-solid fa-pencil"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                </ul>
            
                <h3 class="main-title center mb-70">🤔 도시를 선택해주세요.</h3>
	
            	<form name="frm">
	            	<input type="hidden" name="peopleCount" id="peopleCount">
	            	<input type="hidden" name="departureMonth" id="departureMonth">
	            	<input type="hidden" name="groupType" id="groupType">
	            	<input type="hidden" name="budgetMin" id="budgetMin">
	            	<input type="hidden" name="budgetMax" id="budgetMax">
	            	<input type="hidden" name="thema" id="thema">
	                <c:forEach var="city" items="${requestScope.cityList}" end="2" varStatus="status">
	                <p class="border mt-2 mb-2"></p>
	                
	                <div class="row">
	                    <h4 class="title mb-1">${status.index+1}. ${city.get("나라/도시")} &nbsp; <input type="radio" name="destination" id="paris${status.index+1}" class="none" value="${city.get('나라/도시')}"><label class="btn" for="paris${status.index+1}">선택</label></h4>
	                    <div class="col flex mb-1">
	                        <div class="select-item"><img src="${pageContext.request.contextPath}/resources/images/image 175.png" alt="에펠탑"></div>
	                        <div class="select-item"><img src="${pageContext.request.contextPath}/resources/images/image 178.png" alt="루브르박물관"></div>
	                        <div class="select-item"><img src="${pageContext.request.contextPath}/resources/images/image 179.png" alt="파리지도"></div>
	                    </div>
	                    <div class="col flex mb-2">
	                        <div class="select-item">
	                            <p class="sub-title mb-1">🎡 대표관광지</p>
	                            <p class="text">· ${city.get("대표관광지")[0]}</p>
	                            <p class="text">· ${city.get("대표관광지")[1]}</p>
	                            <p class="text">· ${city.get("대표관광지")[2]}</p>
	                        </div>
	                        <div class="select-item">
	                            <p class="sub-title mb-1">🍴 대표음식</p>
	                            <p class="text">· ${city.get("대표음식")[0]}</p>
	                            <p class="text">· ${city.get("대표음식")[1]}</p>
	                            <p class="text">· ${city.get("대표음식")[2]}</p>
	                        </div>
	                        <div class="select-item">
	                            <p class="sub-title mb-1">💰 환율</p>
	                            <p class="text">· 1,480원</p>
	                        </div>
	                    </div>
	                    <div class="col flex mb-2">
	                        <div class="select-item">
	                            <p class="sub-title mb-1">🌞 평균날씨</p>
	                            <p class="text">· ${city.get("날씨(섭씨)")}</p>
	                        </div>
	                        <div class="select-item">
	                            <p class="sub-title mb-1">💸 성수기여부</p>
	                            <p class="text">· ${city.get("성수기여부")}</p>
	                        </div>
	                        <div class="select-item">
	                            <p class="sub-title mb-1">🍔 물가</p>
	                            <p class="text">· ${city.get("한국 대비 물가")}</p>
	                        </div>
	                    </div>
	                    <div class="col flex mb-2">
	                        <div class="select-item">
	                            <p class="sub-title mb-1">👮 치안</p>
	                            <p class="text">· ${city.get("치안")}</p>
	                        </div>
	                        <div class="select-item">
	                            <p class="sub-title mb-1">😷 위생</p>
	                            <p class="text">· ${city.get("위생")}</p>
	                        </div>
	                        <div class="select-item">
	                            <p class="sub-title mb-1">🚌 교통</p>
	                            <p class="text">· ${city.get("교통")}</p>
	                        </div>
	                    </div>
	                    <div class="col flex">
	                        <div class="select-item">
	                            <p class="sub-title mb-1">🎆 축제 & 행사</p>
	                            <c:forEach var="festival" items="${city.get('축제, 이벤트')}">
	                            <p class="text">· ${festival}</p>
	                            </c:forEach>
	                        </div>
	                        <div class="select-item">
	                            <p class="sub-title mb-1">🚨 주의해야 하는 기간 </p>
	                            <p class="text no-wrap">· ${city.get("주의해야 하는 기간")}</p>
	                        </div>
	                    </div>
	                </div>
	                </c:forEach>
	                
	                <div class="addition none">
	                
	                	<c:forEach var="city" items="${requestScope.cityList}" begin="3" end="5" varStatus="status">
		                <p class="border mt-2 mb-2"></p>
		                
		                <div class="row">
		                    <h4 class="title mb-1">${status.index+1}. ${city.get("나라/도시")} &nbsp; <input type="radio" name="destination" id="paris${status.index+1}" value="${city.get('나라/도시')}" class="none"><label class="btn" for="paris${status.index+1}">선택</label></h4>
		                    <div class="col flex mb-1">
		                        <div class="select-item"><img src="${pageContext.request.contextPath}/resources/images/image 175.png" alt="에펠탑"></div>
		                        <div class="select-item"><img src="${pageContext.request.contextPath}/resources/images/image 178.png" alt="루브르박물관"></div>
		                        <div class="select-item"><img src="${pageContext.request.contextPath}/resources/images/image 179.png" alt="파리지도"></div>
		                    </div>
		                    <div class="col flex mb-2">
		                        <div class="select-item">
		                            <p class="sub-title mb-1">🎡 대표관광지</p>
		                            <p class="text">· ${city.get("대표관광지")[0]}</p>
		                            <p class="text">· ${city.get("대표관광지")[1]}</p>
		                            <p class="text">· ${city.get("대표관광지")[2]}</p>
		                        </div>
		                        <div class="select-item">
		                            <p class="sub-title mb-1">🍴 대표음식</p>
		                            <p class="text">· ${city.get("대표음식")[0]}</p>
		                            <p class="text">· ${city.get("대표음식")[1]}</p>
		                            <p class="text">· ${city.get("대표음식")[2]}</p>
		                        </div>
		                        <div class="select-item">
		                            <p class="sub-title mb-1">💰 환율</p>
		                            <p class="text">· 1,480원</p>
		                        </div>
		                    </div>
		                    <div class="col flex mb-2">
		                        <div class="select-item">
		                            <p class="sub-title mb-1">🌞 평균날씨</p>
		                            <p class="text">· ${city.get("날씨(섭씨)")}</p>
		                        </div>
		                        <div class="select-item">
		                            <p class="sub-title mb-1">💸 성수기여부</p>
		                            <p class="text">· ${city.get("성수기여부")}</p>
		                        </div>
		                        <div class="select-item">
		                            <p class="sub-title mb-1">🍔 물가</p>
		                            <p class="text">· ${city.get("한국 대비 물가")}</p>
		                        </div>
		                    </div>
		                    <div class="col flex mb-2">
		                        <div class="select-item">
		                            <p class="sub-title mb-1">👮 치안</p>
		                            <p class="text">· ${city.get("치안")}</p>
		                        </div>
		                        <div class="select-item">
		                            <p class="sub-title mb-1">😷 위생</p>
		                            <p class="text">· ${city.get("위생")}</p>
		                        </div>
		                        <div class="select-item">
		                            <p class="sub-title mb-1">🚌 교통</p>
		                            <p class="text">· ${city.get("교통")}</p>
		                        </div>
		                    </div>
		                    <div class="col flex">
		                        <div class="select-item">
		                            <p class="sub-title mb-1">🎆 축제 & 행사</p>
		                            <c:forEach var="festival" items="${city.get('축제, 이벤트')}">
		                            <p class="text">· ${festival}</p>
		                            </c:forEach>
		                        </div>
		                        <div class="select-item">
		                            <p class="sub-title mb-1">🚨 주의해야 하는 기간 </p>
		                            <p class="text no-wrap">· ${city.get("주의해야 하는 기간")}</p>
		                        </div>
		                    </div>
		                </div>
		                </c:forEach>
		            </div>
	
	                <div class="btn-box center mb-70 mt-50 flex justify-content-center">
	                    <button class="btn green more" onClick="moreResult(this);" type="button">더보기</button>
	                    <button class="btn blue" type="button" onClick="goTravelSights();">다음</button>
	                    <button class="btn" type="button" onClick="history.back();">뒤로</button>
	                </div>
	            </form>
            </section>
        </div>
        <%@ include file="/WEB-INF/footer.jsp" %>
    </div>
    
    <script>

    function goTravelSights() {

	    // 유효성 검사하기
		let fm = document.frm;
		
		if (fm.destination.value == "") {
			alert("도시를 선택해주세요");
			window.scrollTo({top: 0, behavior: 'smooth'});
			return;
		}
		
		let ans = confirm("다음페이지로 이동합니다.");
		if (ans == true) {

			// sessionStorage에 저장
		    sessionStorage.setItem('destination', fm.destination.value);
			
			// sessionStorage에서 불러오기
		    document.querySelector('#peopleCount').value = sessionStorage.getItem('peopleCount');
		    document.querySelector('#departureMonth').value = sessionStorage.getItem('departureMonth');
		    document.querySelector('#groupType').value = sessionStorage.getItem('groupType');
		    document.querySelector('#budgetMin').value = sessionStorage.getItem('budgetMin');
		    document.querySelector('#budgetMax').value = sessionStorage.getItem('budgetMax');
		    document.querySelector('#thema').value = sessionStorage.getItem('thema');
		    
			fm.action="${pageContext.request.contextPath}/travel/travelSights.do";
			fm.method="post";
			fm.submit();
		}
	  
		return;
	}
    
    
    function moreResult() {
    	document.querySelector(".addition").style.display = "block";
    	document.querySelector(".more").style.display = "none";
    }
 
    </script>
</body>
</html>