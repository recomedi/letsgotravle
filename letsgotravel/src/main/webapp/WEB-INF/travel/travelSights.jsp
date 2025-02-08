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
	    
        <div class="inner travel" id="travel-sights">
            
            <section class="contents">
                <ul class="step flex justify-content-between">
                    <li class="step-item finished flex justify-content-center align-items-center"><i class="fa-solid fa-check"></i></li>
                    <li class="relative step-item finished flex justify-content-center align-items-center"><i class="fa-solid fa-check"></i></li>
                    <li class="relative step-item on flex justify-content-center align-items-center"><i class="fa-solid fa-pencil"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                </ul>
            
                <h3 class="main-title center mb-70">🤔 "일본 / 도쿄"의 관광지와 음식점을 선택해주세요.</h3>

            	<form name="frm">
	               <div class="flex">
	                   <div class="col">
	                       <div class="flex justify-content-between">                    
	                           <div class="btn-box flex">
	                               <label class="btn green" for="sights">관광지</label> <label class="btn btn2" for="restaurant">음식점</label>
	                           </div>
	                           <button class="btn blue">추가</button>
	                       </div>
						
					 	<input type="radio" name="sights-radio" id="sights" class="none" checked>
	                       <div class="check-box pt-10 sights">
	                           <input type="checkbox" id="ck1" class="none" name="sights"><label class="relative mt-20 pl-35 inline-block" for="ck1"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck2" class="none" name="sights"><label class="relative mt-20 pl-35 inline-block" for="ck2"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck3" class="none" name="sights"><label class="relative mt-20 pl-35 inline-block" for="ck3"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck4" class="none" name="sights"><label class="relative mt-20 pl-35 inline-block" for="ck4"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck5" class="none" name="sights"><label class="relative mt-20 pl-35 inline-block" for="ck5"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck6" class="none" name="sights"><label class="relative mt-20 pl-35 inline-block" for="ck6"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck7" class="none" name="sights"><label class="relative mt-20 pl-35 inline-block" for="ck7"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck8" class="none" name="sights"><label class="relative mt-20 pl-35 inline-block" for="ck8"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck9" class="none" name="sights"><label class="relative mt-20 pl-35 inline-block" for="ck9"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck10" class="none" name="sights"><label class="relative mt-20 pl-35 inline-block" for="ck10"> 디즈니랜드</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                       </div>
	                       
	                       <input type="radio" name="sights-radio" id="restaurant" class="none">
	                       <div class="check-box pt-10 none restaurant">
	                           <input type="checkbox" id="ck101" class="none" name="restaurant"><label class="relative mt-20 pl-35 inline-block" for="ck101"> 라멘</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck102" class="none" name="restaurant"><label class="relative mt-20 pl-35 inline-block" for="ck102"> 라멘</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck103" class="none" name="restaurant"><label class="relative mt-20 pl-35 inline-block" for="ck103"> 라멘</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck104" class="none" name="restaurant"><label class="relative mt-20 pl-35 inline-block" for="ck104"> 라멘</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck105" class="none" name="restaurant"><label class="relative mt-20 pl-35 inline-block" for="ck105"> 라멘</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck106" class="none" name="restaurant"><label class="relative mt-20 pl-35 inline-block" for="ck106"> 라멘</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck107" class="none" name="restaurant"><label class="relative mt-20 pl-35 inline-block" for="ck107"> 라멘</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck108" class="none" name="restaurant"><label class="relative mt-20 pl-35 inline-block" for="ck108"> 라멘</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck109" class="none" name="restaurant"><label class="relative mt-20 pl-35 inline-block" for="ck109"> 라멘</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
	                           <input type="checkbox" id="ck110" class="none" name="restaurant"><label class="relative mt-20 pl-35 inline-block" for="ck110"> 라멘</label><button class="ml-10 center plus-icon inline-block"><i class="fa-solid fa-plus"></i></button><br>
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
	                   <button type="button" onClick="goTravelModify()" class="btn blue">다음</button>
	                   <button class="btn">뒤로</button>
	               </div>
	            </form>
            </section>
        </div>
        <%@ include file="/WEB-INF/footer.jsp" %>
    </div>
    
    <script>
    function goTravelModify() { 
    	
    	// 선택된 목록 가져오기
    	function findChecked(sights) {
    		const checked = 'input[name="' + sights + '"]:checked';
    		const Checkeds = document.querySelectorAll(checked);
    		return Checkeds;
    	}		

		// 선택된 목록에서 value 찾기
    	function findCheckedValue(checkeds) {
    			let value = "";
    			checkeds.forEach((el) => {
    				value += el.value + ', ';
    			});
    			
    			// 마지막 문자 자르기
    			return value.slice(0, -2);
    	}

	    // 유효성 검사하기
		let fm = document.frm;	
		
		/* if (findChecked("sights").length == 0) {
			alert("관광지를 선택해주세요");
			document.querySelector("#sights").checked = true;
			window.scrollTo({top: 0, behavior: 'smooth'});
			return;
		} else if (findChecked("restaurant").length == 0) {
			alert("음식점을 선택해주세요");
			document.querySelector("#restaurant").checked = true;
			window.scrollTo({top: 0, behavior: 'smooth'});
			return;
		} */
		const bbb = findChecked(sights);
		console.log(bbb)
		const abs = sessionStorage.getItem('sights', findCheckedValue(bbb));
		console.log(abs);
		
		if (findChecked("sights").length == 0 && findChecked("restaurant").length == 0) {
			alert("관광지나 음식점을 선택해주세요");
			window.scrollTo({top: 0, behavior: 'smooth'});
			return;
		}
		
		let ans = confirm("다음페이지로 이동합니다.");
		if (ans == true) {

			// sessionStorage에 저장
		    sessionStorage.setItem('sights', findCheckedValue(findChecked(sights)));
		    sessionStorage.setItem('restaurant', findCheckedValue(findChecked(restaurant)));
		    
			fm.action="${pageContext.request.contextPath}/travel/travelModify.do";
			fm.method="post";
			fm.submit();
		}
	  
		return;
	}
 
    </script>
</body>
</html>