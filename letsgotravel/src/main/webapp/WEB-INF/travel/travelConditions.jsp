<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>여행조건을 입력해주세요.</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    
    <!-- 폰트어썸 불러오기 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
    
</head>
<body>
    <div class="wrap flex flex-column">
      <%@ include file="/WEB-INF/header1.jsp" %>
	    <nav class="breadcrumb">
	      <span>홈</span> &gt; <span>여행일정</span> &gt; <span>조건입력</span>
	    </nav>
        
        <div class="inner travel" id="travel-conditions">
            
            <section class="contents">
                <ul class="step flex justify-content-between">
                    <li class="step-item on flex justify-content-center align-items-center"><i class="fa-solid fa-pencil"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                </ul>
            
                <h3 class="main-title center mb-70">🤔 여행 조건을 입력해주세요.</h3>

            	<form name="frm">
	                <div class="row flex justify-content-between mb-2">
	                    <div class="col">
	                        <label>
	                        	<span class="title bold">👥 인원</span><br>
	                        	<div class="input-box ml-3 mt-1">
		                            <input type="text" name="peopleCount" class="center"> 명
		                        </div>
	                        </label>
	                    </div>
	                    <div class="col" style="margin-left: -43px;">
	                        <label>
	                        	<span class="title bold">✈️️ 출발일</span><br>
		                        <div class="input-box ml-3 mt-1">
		                            <input type="month" name="departureMonth" class="center">
		                        </div>
	                        </label>
	                    </div>
	                    <div class="col mr-20">
	                        <label>
	                        	<span class="title bold">📅 기간</span><br>
		                        <div class="input-box ml-3 mt-1">
		                            <input type="text" name="duration" class="center"> 일
		                        </div>
	                        </label>
	                    </div>
	                </div>
	                <div class="row flex justify-content-between mb-2">
	                    <div class="col">
	                        <p class="title bold groupType">👨‍👩‍👧‍👦 구성원</p>
	                        <div class="badge-label-box ml-3 mt-1 flex">
	                            <input type="radio" name="groupType" value="가족" class="none" id="purpose-family">
	                            <label class="badge-label" for="purpose-family"> 가족</label>
	                            <input type="radio" name="groupType" value="친구" class="none" id="purpose-friend">
	                            <label class="badge-label" for="purpose-friend"> 친구</label>
	                            <input type="radio" name="groupType" value="연인" class="none" id="purpose-couple">
	                            <label class="badge-label" for="purpose-couple"> 연인</label>
	                            <input type="radio" name="groupType" value="혼자" class="none" id="purpose-alone">
	                            <label class="badge-label" for="purpose-alone"> 혼자</label>
	                        </div>
	                    </div>
	                    <div class="col">
	                        <label>
	                        	<span class="title bold">💰 예산</span><br>
		                        <div class="input-box ml-3 mt-1">
		                            <input type="text" name="budgetMin" class="center"> 만원 ~ <input type="text" name="budgetMax" class="center"> 만원
		                        </div>
		                    </label>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="col">
	                        <p class="title bold thema">🎮 테마(중복선택가능)</p>
	                        <div class="duplication badge-label-box ml-3 mt-1 flex flex-wrap">
	                            <input type="checkbox" name="themaInput" id="filial-piety" class="none" value="효도">
	                            <label class="badge-label" for="filial-piety"> 효도</label>
	                            <input type="checkbox" name="themaInput" id="healing" class="none" value="힐링">
	                            <label class="badge-label" for="healing"> 힐링</label>
	                            <input type="checkbox" name="themaInput" id="cost-effectiveness" class="none" value="가성비">
	                            <label class="badge-label" for="cost-effectiveness"> 가성비</label>
	                            <input type="checkbox" name="themaInput" id="staycation" class="none" value="호캉스">
	                            <label class="badge-label" for="staycation"> 호캉스</label>
	                            <input type="checkbox" name="themaInput" id="epicurism" class="none" value="식도락">
	                            <label class="badge-label" for="epicurism"> 식도락</label>
	                            <input type="checkbox" name="themaInput" id="shopping" class="none" value="쇼핑">
	                            <label class="badge-label" for="shopping"> 쇼핑</label>
	                            <input type="checkbox" name="themaInput" id="historic-site" class="none" value="유적지">
	                            <label class="badge-label" for="historic-site"> 유적지</label>
	                            <input type="checkbox" name="themaInput" id="landscape" class="none" value="자연경관">
	                            <label class="badge-label" for="landscape"> 자연경관</label>
	                            <input type="checkbox" name="themaInput" id="activity" class="none" value="액티비티">
	                            <label class="badge-label" for="activity"> 액티비티</label>
	                        	<input type="hidden" name="thema" id="thema" class="none" value="">
	                        </div>
	                    </div>
	                </div>
	
	                <div class="btn-box center mb-70 mt-50 flex justify-content-center">
	                    <button onClick="goTravelSelect();" class="btn blue next" type="button">다음</button>
	                    <button class="btn" type="button" onClick="history.back();">뒤로</button>
	                </div>
	                
	                <div id="loading" style="background: rgba(0,0,0,.1); position: fixed; top: 0; left: 0; width: 100%; height: 100%; z-index: 1001">
	                <style>
					svg path,
					svg rect{
					  fill: #FFD700;
					}</style>
	                	<!-- 3  -->
						<div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%,-50%);">
						  <svg version="1.1" id="loader-1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
						     width="60px" height="60px" viewBox="0 0 50 50" style="enable-background:new 0 0 50 50;" xml:space="preserve">
						  <path fill="#000" d="M43.935,25.145c0-10.318-8.364-18.683-18.683-18.683c-10.318,0-18.683,8.365-18.683,18.683h4.068c0-8.071,6.543-14.615,14.615-14.615c8.072,0,14.615,6.543,14.615,14.615H43.935z">
						    <animateTransform attributeType="xml"
						      attributeName="transform"
						      type="rotate"
						      from="0 25 25"
						      to="360 25 25"
						      dur="0.6s"
						      repeatCount="indefinite"/>
						    </path>
						  </svg>
						</div>
					</div>
			        <%-- <div id="loading" style="display: none; background: rgba(0,0,0,.3); position: fixed; top: 0; left: 0; width: 100%; height: 100%;">
			        	<img src="${pageContext.request.contextPath}/resources/images/loadingImage.gif" style="position: absolute; top: 50%; left: 50%; transform: translate(-50%,-50%);">
			        </div> --%>
	            </form>
            </section>
        </div>
        <%@ include file="/WEB-INF/footer.jsp" %>
    </div>
    
    <script>

    function goTravelSelect() { 

		// 선택된 목록 가져오기
    	const checked = 'input[name="themaInput"]:checked';
		const checkeds = document.querySelectorAll(checked);
		
	    // 유효성 검사하기
		let fm = document.frm;
		const regExp = /^[0-9]+$/;
		
		if (fm.peopleCount.value == "") {
			alert("인원을 입력해주세요");
			fm.peopleCount.focus();
			return;
		} else if (regExp.test(fm.peopleCount.value) == false) {
			alert("인원은 숫자만 입력해주세요");
			fm.peopleCount.focus();
			fm.peopleCount.value = "";
			return;
		} else if (fm.departureMonth.value == "") {
			alert("출발일을 입력해주세요");
			fm.departureMonth.focus();
			return;
		} else if (fm.duration.value == "") {
			alert("기간을 입력해주세요");
			fm.duration.focus();
			return;
		} else if (regExp.test(fm.duration.value) == false) {
			alert("기간은 숫자만 입력해주세요");
			fm.duration.focus();
			fm.duration.value = "";
			return;
		} else if (fm.groupType.value == "") {
			alert("구성원을 선택해주세요");
			document.querySelector(".groupType").focus();
			return;
		} else if (fm.budgetMin.value == "") {
			alert("최소예산을 입력해주세요");
			fm.budgetMin.focus();
			return;
		} else if (regExp.test(fm.budgetMin.value) == false) {
			alert("최소예산은 숫자만 입력해주세요");
			fm.budgetMin.focus();
			fm.budgetMin.value = "";
			return;
		} else if (fm.budgetMax.value == "") {
			alert("최대예산을 입력해주세요");
			fm.budgetMax.focus();
			return;
		} else if (regExp.test(fm.budgetMax.value) == false) {
			alert("최대예산은 숫자만 입력해주세요");
			fm.budgetMax.focus();
			fm.budgetMax.value = "";
			return;
		} else if (checkeds.length == 0) {
			alert("테마를 선택해주세요");
			document.querySelector(".thema").focus();
			return;
		}
		
		let ans = confirm("다음페이지로 이동합니다.");
		
		if (ans == true) {

			// 선택된 목록에서 value 찾기
			let themaValue = function () {
				let value = "";
				checkeds.forEach((el) => {
					value += el.value + ', ';
				});
				
				// 마지막 문자 자르기
				return value.slice(0, -2);
			}

			// sessionStorage에 저장
		    sessionStorage.setItem('peopleCount', fm.peopleCount.value);
		    sessionStorage.setItem('departureMonth', fm.departureMonth.value);
		    sessionStorage.setItem('duration', fm.duration.value);
		    sessionStorage.setItem('groupType', fm.groupType.value);
		    sessionStorage.setItem('budgetMin', fm.budgetMin.value);
		    sessionStorage.setItem('budgetMax', fm.budgetMax.value);
		    sessionStorage.setItem('thema', themaValue());
		    document.querySelector('#thema').value = themaValue();
		    
			fm.action="${pageContext.request.contextPath}/travel/travelSelect.do";
			fm.method="post";
			fm.submit();
			
			document.getElementById('loading').style.display = 'block';
			
		}
	  
		return;
	}
 
    </script>
</body>
</html>