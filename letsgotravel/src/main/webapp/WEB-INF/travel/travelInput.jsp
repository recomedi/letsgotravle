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
		  
        <div class="inner travel" id="travel-input">
            
            <section class="contents">
                <ul class="step flex justify-content-between">
                    <li class="step-item on flex justify-content-center align-items-center"><i class="fa-solid fa-pencil"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                </ul>
            
                <h3 class="main-title center mb-70">🤔 여행지를 입력해주세요.</h3>
				<form name="frm">
	                <div class="mb-2 center">
	                    <input type="text" name="city" placeholder="여행지를 입력해주세요." class="w-200">
	                </div>
	
	                <div class="map">
	
	                </div>
	
	                <div class="btn-box center mb-70 mt-50 flex justify-content-center">
	                    <button class="btn blue" type="button" onClick="goTravelSights();">다음</button>
	                    <button class="btn">뒤로</button>
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
		
		if (fm.city.value == "") {
			alert("여행지를 입력해주세요");
			fm.city.focus();
			return;
		}
		
		let ans = confirm("다음페이지로 이동합니다.");
		if (ans == true) {

			// sessionStorage에 저장
		    sessionStorage.setItem('city', fm.city.value);
		    
		    location.href = "${pageContext.request.contextPath}/travel/travelSights.do";
		}
	  
		return;
	}
    </script>
</body>
</html>