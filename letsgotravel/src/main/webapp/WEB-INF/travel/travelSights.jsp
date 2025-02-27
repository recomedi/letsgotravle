<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Properties, java.io.InputStream, java.io.IOException" %>
<%
    Properties properties = new Properties();
    try {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("properties/googleMap.properties");
        if (input != null) {
            properties.load(input);
            input.close();
        } else {
            throw new IOException("googleMap.properties 파일을 찾을 수 없습니다.");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    String googleMapsApiKey = properties.getProperty("google.maps.api.key");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관광지와 음식점을 선택해주세요.</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<script src="https://maps.googleapis.com/maps/api/js?key=<%= googleMapsApiKey %>&libraries=places" async defer></script>
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
            
                <h3 class="main-title center mb-10">🤔 "${requestScope.destination}"에서 방문할 장소를 선택해주세요.</h3>
                <p class="center mb-70">(주어진 기간 내에 너무 많은 장소를 선택할 경우, 조건에 맞춰 가성비가 가장 뛰어난 일정으로 제공합니다.)</p>

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
	                   	   <input type="hidden" name="duration" id="duration">
	                   	   <input type="hidden" name="placeName" id="sightCk">
	                   	   <input type="hidden" name="placeName" id="restaurantCk">
	                       <div class="flex justify-content-between">                    
	                           <div class="btn-box flex">
	                               <label class="btn btn2 green" for="sights" onClick="btnClick(this);">관광지</label> <label class="btn btn2" for="restaurants" onClick="btnClick(this);">음식점</label>
	                           </div>
	                           <!-- <button class="btn blue">추가</button> -->
	                       </div>
						
					 	   <input type="radio" name="sights-radio" id="sights" class="none" checked>
	                       <div class="check-box pt-10 sights">
	                			<c:forEach var="sight" items="${requestScope.openAIResult1Array[0]['추천관광지']}" varStatus="status">
	                            <input type="checkbox" id="ck${status.index+1}" class="none" name="sightCk" value="${sight}">
	                            <label class="relative mt-20 pl-35 inline-block" for="ck${status.index+1}"> ${sight}</label>
								<button class="ml-5 center search-icon inline-block" type="button"
								    onClick="viewDetail(this, ${requestScope.sightListArray[status.index]['latitude']}, ${requestScope.sightListArray[status.index]['longitude']});">
								    <i class="fa-solid fa-magnifying-glass icon"></i><textarea class="none" data-value="ck${status.index+1}">${requestScope.sightListArray[status.index]["설명"]}</textarea></button><br>
	                       		</c:forEach>
	                       </div>
	                       
	                       <input type="radio" name="sights-radio" id="restaurants" class="none">
	                       <div class="check-box pt-10 none restaurants">
	                       		<c:forEach var="restaurant" items="${requestScope.openAIResult1Array[0]['추천음식점']}" varStatus="status">
	                            <input type="checkbox" id="ck10${status.index+1}" class="none" name="restaurantCk" value="${restaurant}">
	                            <label class="relative mt-20 pl-35 inline-block" for="ck10${status.index+1}"> ${restaurant}</label>
	                           <button class="ml-5 center search-icon inline-block" type="button" 
								    onClick="viewDetail(this, ${requestScope.restaurantListArray[status.index]['latitude']}, ${requestScope.restaurantListArray[status.index]['longitude']});">
								    <i class="fa-solid fa-magnifying-glass icon"></i><textarea class="none" data-value="ck10${status.index+1}">${requestScope.restaurantListArray[status.index]["설명"]}</textarea></button><br>
	                       		</c:forEach>
	                       </div>
	                   </div>
	
	                   <p class="border-vertical ml-25 mr-25"></p>
	   
	                   <div class="col info">
	                       <div class="flex mb-20 justify-content-between">
	                           <img src="${pageContext.request.contextPath}/resources/images/image 178.png" alt="루브르박물관">
	                           <div id="map-${status.index}" 
							    class="map" 
							    data-lat="${requestScope.sightListArray[status.index]['latitude']}" 
							    data-lng="${requestScope.sightListArray[status.index]['longitude']}" 
							    style="width:350px; height:350px; border-radius:10px;">
							</div>
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
        <%@ include file="/WEB-INF/loadingImage.jsp" %>
        <%@ include file="/WEB-INF/footer.jsp" %>
    </div>
    
    <script>
	// 최초 상세설명 입력
	let infoArea = document.querySelector(".info .text");
	let textValue = document.querySelector(".check-box .search-icon textarea").value;
    infoArea.innerText = textValue;
    
    function goTravelModify() {    	

    	// 선택된 목록 가져오기
    	function findChecked(sight) {
    		const checked = "input[name='" + sight + "Ck']:checked";
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
		
		if (findChecked("sight").length == 0 && findChecked("restaurant").length == 0) {
			alert("관광지나 음식점을 선택해주세요");
			window.scrollTo({top: 0, behavior: 'smooth'});
			return;
		}

		let ans = confirm("다음페이지로 이동합니다.");
		
		if (ans == true) {		    		    

		    function getCheckedValues(name) {
		    	var chkArray = {};
		    	
		    	const checkboxes = document.querySelectorAll('input[type="checkbox"][name="' + name + '"]:checked');
		    	checkboxes.forEach(function(checkbox) {
		    	    // 'checkbox'는 체크된 체크박스를 가리킴
		    	    const checkboxId = checkbox.id;
		    	    const textarea = document.querySelector('textarea[data-value="' + checkboxId + '"]');
		    	    const value = textarea.value;
		    	    alert(checkbox.value + " : " + value);
		    	    chkArray[checkbox.value] = value;
		    	});
		    	document.querySelector("#" + name).value = JSON.stringify(chkArray);  // JSON.stringify로 객체를 문자열로 변환하여 hidden input에 저장
		    }
		    
		    getCheckedValues("sightCk");
		    getCheckedValues("restaurantCk");
		    
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
		    sessionStorage.setItem('sights', findCheckedValue("sight"));
		    sessionStorage.setItem('restaurants', findCheckedValue("restaurant"));
		    
			// sessionStorage에서 불러오기
		    document.querySelector('#peopleCount').value = sessionStorage.getItem('peopleCount');
		    document.querySelector('#departureMonth').value = sessionStorage.getItem('departureMonth');
		    document.querySelector('#groupType').value = sessionStorage.getItem('groupType');
		    document.querySelector('#budgetMin').value = sessionStorage.getItem('budgetMin');
		    document.querySelector('#budgetMax').value = sessionStorage.getItem('budgetMax');
		    document.querySelector('#destination').value = sessionStorage.getItem('destination');
		    document.querySelector('#duration').value = sessionStorage.getItem('duration');
		    document.querySelector('#thema').value = sessionStorage.getItem('thema');
		            	   
		    document.querySelector('#sightsInput').value = findCheckedValue("sight");
		    document.querySelector('#restaurantsInput').value = findCheckedValue("restaurant");
	        
			fm.action="${pageContext.request.contextPath}/travel/travelModify.do";
			fm.method="post";
			fm.submit();

			document.getElementById('loading').style.display = 'block';
		}
	  
		return;
	}    
    
    // 상세 설명 + 장소 데이터 가져오
function viewDetail(button, latitude, longitude) {
    let infoArea = document.querySelector(".info .text");
    let textValue = button.querySelector("textarea").value;
    infoArea.innerText = textValue;
    console.log("위도:", latitude);
    console.log("경도:", longitude);

    // 지도 업데이트
    let mapDiv = document.querySelector(".map");
    mapDiv.setAttribute("data-lat", latitude);
    mapDiv.setAttribute("data-lng", longitude);

    initMap(latitude, longitude);
}
    
//Google Maps API가 로드될 때까지 기다렸다가 `initMap()` 실행
function loadGoogleMapsAPI(callback) {
    if (typeof google === "object" && typeof google.maps === "object") {
        /* console.log("initMap 실행"); */
        callback();
    } else {
        let script = document.createElement("script");
        script.src = "https://maps.googleapis.com/maps/api/js?key=<%= googleMapsApiKey %>&libraries=places";
        script.async = true;
        script.defer = true;

        script.onload = function () {
            console.log("googleMap 실행");
            callback();
        };

        script.onerror = function () {
            console.error("Google Maps API 키값 확인");
        };

        document.head.appendChild(script);
    }
}

window.onload = function () {

    loadGoogleMapsAPI(function () {
        let firstLat = parseFloat('${firstLatitude}');
        let firstLng = parseFloat('${firstLongitude}');

        if (!isNaN(firstLat) && !isNaN(firstLng) && firstLat !== 0 && firstLng !== 0) {
            console.log("초기 위도,경도값:", firstLat, firstLng);
            initMap(firstLat, firstLng);
        }
    });
};

function initMap(latitude = null, longitude = null) {
    console.log("initMap 위도 경도값", latitude, longitude);

    let mapDiv = document.querySelector(".map");

    if (!latitude || !longitude || isNaN(latitude) || isNaN(longitude)) {
        latitude = parseFloat(mapDiv.getAttribute("data-lat"));
        longitude = parseFloat(mapDiv.getAttribute("data-lng"));
    }

    // 좌표가 유효하지 않을 경우 예외 처리
    if (isNaN(latitude) || isNaN(longitude) || latitude === 0 || longitude === 0) {
        console.error("지도에 없습니.");
        return;
    }

    let latLng = { lat: latitude, lng: longitude };

    let map = new google.maps.Map(mapDiv, {
        center: latLng,
        zoom: 14, 
        zoomControl: true,
        cameraControl: false,
        mapTypeControl: false, //지도,위성
        scaleControl: true, // 밑에 보이는 맵 크기
        streetViewControl: true,
        rotateControl: false,
        fullscreenControl: false,
    });

    new google.maps.Marker({
        position: latLng,
        map: map,
        title: "선택한 장소"
    });
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