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
            
                <h3 class="main-title center mb-70">🤔 "${requestScope.destination}"에서 방문할 장소를 선택해주세요.</h3>

            	<form name="frm">
	               <div class="flex">
	                   <div class="col">
	                   	   <input type="hidden" name="peopleCount" id="peopleCount">
	                   	   <input type="hidden" name="departureMonth" id="departureMonth">
	                   	   <input type="hidden" name="groupType" id="groupType">
	                   	   <input type="hidden" name="budgetMin" id="budgetMin">
	                   	   <input type="hidden" name="budgetMax" id="budgetMax">
	                   	   <input type="hidden" name="destination" id="destination">
	                   	   <input type="hidden" name="thema" id="thema">
	                   	   <input type="hidden" name="sights" id="sightsInput">
	                   	   <input type="hidden" name="restaurants" id="restaurantsInput">
	                   	   <input type="hidden" name="placeName" id="placeName">
	                       <div class="flex justify-content-between">                    
	                           <div class="btn-box flex">
	                               <label class="btn btn2 green" for="sights" onClick="btnClick(this);">관광지</label> <label class="btn btn2" for="restaurant" onClick="btnClick(this);">음식점</label>
	                           </div>
	                           <button class="btn blue">추가</button>
	                       </div>
						
					 	   <input type="radio" name="sights-radio" id="sights" class="none" checked>
	                       <div class="check-box pt-10 sights">
	                			<c:forEach var="sight" items="${requestScope.openAIResult1Array[0]['추천관광지']}" varStatus="status">
		                            <input type="checkbox" id="ck${status.index+1}" class="none" name="sightsCk" value="${sight}"><label class="relative mt-20 pl-35 inline-block" for="ck${status.index+1}"> ${sight}</label><button class="ml-10 center plus-icon inline-block" type="button" onClick="viewDetail(this);"><i class="fa-solid fa-plus"></i><textarea class="none">${requestScope.sightListArray[sight]}</textarea></button><br>
	                       		</c:forEach>
	                       </div>
	                       
	                       <input type="radio" name="sights-radio" id="restaurant" class="none">
	                       <div class="check-box pt-10 none restaurant">
	                       		<c:forEach var="restaurant" items="${requestScope.openAIResult1Array[0]['추천음식점']}" varStatus="status">
	                            <input type="checkbox" id="ck10${status.index+1}" class="none" name="restaurantCk" value="${restaurant}"><label class="relative mt-20 pl-35 inline-block" for="ck10${status.index+1}"> ${restaurant}</label><button class="ml-10 center plus-icon inline-block" type="button" onClick="viewDetail(this);"><i class="fa-solid fa-plus"></i><textarea class="none">${requestScope.restaurantListArray[restaurant]}</textarea></button><br>
	                       		</c:forEach>
	                       </div>
	                   </div>
	
	                   <p class="border-vertical ml-25 mr-25"></p>
	   
	                   <div class="col info">
	                       <div class="flex mb-20 justify-content-between">
	                           <img src="${pageContext.request.contextPath}/resources/images/image 178.png" alt="루브르박물관">
	                           <img src="${pageContext.request.contextPath}/resources/images/image 179.png" alt="파리지도">
	                       </div>
	                       <p class="text"></p>
	                   </div>
	               </div>
	               
	               <div class="btn-box center mb-70 mt-50 flex justify-content-center">
	                   <button type="button" onClick="goTravelModify();" class="btn blue">다음</button>
	                   <button class="btn" type="button" onClick="history.back();">뒤로</button>
	               </div>
	            </form>
            </section>
        </div>
        <%@ include file="/WEB-INF/footer.jsp" %>
    </div>
    
    <script>
	// 최초 상세설명 입력
	let infoArea = document.querySelector(".info .text");
	let textValue = document.querySelector(".check-box .plus-icon textarea").value;
    infoArea.innerText = textValue;
    
    function goTravelModify() {
    	

    	// 선택된 목록 가져오기
    	function findChecked(sights) {
    		const checked = "input[name='" + sights + "Ck']:checked";
    		const checkeds = document.querySelectorAll(checked);
    		return checkeds;
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
		
		if (findChecked("sights").length == 0 && findChecked("restaurant").length == 0) {
			alert("관광지나 음식점을 선택해주세요");
			window.scrollTo({top: 0, behavior: 'smooth'});
			return;
		}

		let ans = confirm("다음페이지로 이동합니다.");
		
		if (ans == true) {		    		    

	    	var chkArray = {};
	    	
		    function getCheckedValues(name) {		
		    	
		    	const checkboxes = document.querySelectorAll('input[type="checkbox"][name="{name}"]:checked');
		    	checkboxes.forEach(function(checkbox) {
		    		
		    	    // 'checkbox'는 체크된 체크박스를 가리킴
		    	    const button = checkbox.closest('div').querySelector('button');  // 부모 요소에서 button을 찾음
		    	    const textarea = button.querySelector('textarea');
		    	    const value = textarea.value;
		    	    
		    	    chkArray[checkbox.value] = value;
		    	});

		    }
		    
		    getCheckedValues("sightsCk");
		    getCheckedValues("restaurantCk");
		    document.querySelector("#placeName").value = JSON.stringify(chkArray);  // JSON.stringify로 객체를 문자열로 변환하여 hidden input에 저장
		    
	    	function findCheckedValue(sights) {
	    		
				// 선택된 목록에서 value 찾기
	   			let value = "";
	   			findChecked(sights).forEach((el) => {
	   				value += el.value + ', ';
	   			});
	   			
	   			// 마지막 문자 자르기
	   			return value.slice(0, -2);
	    	}

			// sessionStorage에 저장
		    sessionStorage.setItem('sights', findCheckedValue("sights"));
		    sessionStorage.setItem('restaurants', findCheckedValue("restaurants"));
		    
			// sessionStorage에서 불러오기
		    document.querySelector('#peopleCount').value = sessionStorage.getItem('peopleCount');
		    document.querySelector('#departureMonth').value = sessionStorage.getItem('departureMonth');
		    document.querySelector('#groupType').value = sessionStorage.getItem('groupType');
		    document.querySelector('#budgetMin').value = sessionStorage.getItem('budgetMin');
		    document.querySelector('#budgetMax').value = sessionStorage.getItem('budgetMax');
		    document.querySelector('#destination').value = sessionStorage.getItem('destination');
		    document.querySelector('#duration').value = sessionStorage.getItem('duration');
		    document.querySelector('#thema').value = sessionStorage.getItem('thema');
		            	   
		    document.querySelector('#sightsInput').value = findCheckedValue("sights");
		    document.querySelector('#restaurantsInput').value = findCheckedValue("restaurants");
	        		    
			fm.action="${pageContext.request.contextPath}/travel/travelModify.do";
			fm.method="post";
			fm.submit();
		}
	  
		return;
	}    
    
    // 상세 설명
    function viewDetail(button) {
    	infoArea = document.querySelector(".info .text");
    	textValue = button.querySelector("textarea").value;
        infoArea.innerText = textValue;
    }
    
    // 버튼이벤트
    function btnClick(button) {
    	const buttons = document.querySelectorAll(".btn-box .btn2");
    	buttons.forEach((btn) => {
    		btn.classList.remove('green');
    	});
    	
    	button.classList.add('green');
    }
 
    </script>
</body>
</html>