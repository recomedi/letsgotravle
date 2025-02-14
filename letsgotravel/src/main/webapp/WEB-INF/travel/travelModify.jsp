<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>일정을 확정해주세요.</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

    <!-- 폰트어썸 불러오기 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">

    <link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.css" rel="stylesheet">
      
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js'></script>

</head>
<body>
    <div class="wrap flex flex-column">
      <%@ include file="/WEB-INF/header1.jsp" %>
	    <nav class="breadcrumb">
	      <span>홈</span> &gt; <span>여행일정</span> &gt; <span>일정확정</span>
	    </nav>
		  
        <div class="inner travel" id="travel-modify">
            
            <section class="contents">
                <ul class="step flex justify-content-between">
                    <li class="step-item finished flex justify-content-center align-items-center"><i class="fa-solid fa-check"></i></li>
                    <li class="relative step-item finished flex justify-content-center align-items-center"><i class="fa-solid fa-check"></i></li>
                    <li class="relative step-item finished flex justify-content-center align-items-center"><i class="fa-solid fa-check"></i></li>
                    <li class="relative step-item on flex justify-content-center align-items-center"><i class="fa-solid fa-pencil"></i></li>
                </ul>
            
                <h3 class="main-title center mb-70">🤔 "일본 / 도쿄"의 일정을 확정해주세요.</h3>

                <div class="calendar-wrap">
                    <div class="calendar-box">
                        <div id="calendar"></div>
                    </div>
                </div>

                <div class="btn-box center mb-70 mt-50 flex justify-content-center">
                    <button type="button" onClick="goTravelDetails()" class="btn blue">다음</button>
                    <button class="btn" type="button" onClick="history.back();">뒤로</button>
                </div>
            </section>
        </div>
        
        <%@ include file="/WEB-INF/loadingImage.jsp" %>
	    <%@ include file="/WEB-INF/footer.jsp" %>
    </div>

    <script>
    // document.addEventListener('DOMContentLoaded', function() {
    //     var calendarEl = document.getElementById('calendar');
    //     var calendar = new FullCalendar.Calendar(calendarEl, {
    //         initialView: 'dayGridDay',
    //         events: [
    //             {
    //                 title  : 'event3',
    //                 start  : '2025-02-02T12:30:00',
    //                 end  : '2025-02-02T13:30:00',
    //                 allDay : false // will make the time show
    //             }
    //         ]
    //     });
    //     calendar.render();
    // });

    document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            locale: 'en',
            initialView: 'timeGridWeek',
            editable: true,
            eventOverlap : false,
            height: "auto",
            headerToolbar: {
                left: '',
                center: '',
                right: ''
            },
            titleRangeSeparator : ' ~ ',
            events: 
            	
            	
            [
                {
                    title: '해리포터 스튜디오',
                    start: '1900-01-01T12:30:00',
                    end: '1900-01-01T13:30:00',
                    backgroundColor: '#D8E8FC',
                    borderColor: '#3B8EEF',
                    // backgroundcolor: '#DCEDF3',
                    // borderColor: '#4FA3C4',
                    textColor: '#333',
                    extendedProps: {
                        description: "Additional information",
                        referenceId: 12345,
                        customData: { key: "value" }
                    },
                    description: "Additional information",
                },
                {
                    title: '라멘',
                    start: '1900-01-02T16:30:00',
                    end: '1900-01-02T17:30:00',
                    backgroundColor: '#DCEDF3',
                    borderColor: '#4FA3C4',
                    textColor: '#333'
                    // allDay : false
                }
            ],
            /* eventClick: function(e) { /* 일정 클릭 이벤트 */
    			/* var startDayStr = e.event.startStr;
    			var endDayStr = e.event.endStr;
    		
    			if(endDayStr == ''){
    				endDayStr = startDayStr;
    			}else{
    				var endDate = new Date(endDayStr);
    				endDate.setDate(endDate.getDate() - 1);
    				endDayStr = endDate.toISOString().slice(0, 10);	
    			}
    			
    			startDayStr = fn_formatDateString(startDayStr);
    			endDayStr = fn_formatDateString(endDayStr);

    	    	fn_schedulePop(startDayStr, endDayStr, e.event.id); */
        });
        calendar.render();		

        // 캘린더 크기조정
        const calendarBox = document.querySelector(".calendar-box");
        const duration = parseInt(sessionStorage.getItem("duration"));
        calendarBox.style.width = 120 * duration + 46 +"px";
                
        // 일정 설정
		let date = new Date('1900-01-01');
		date.setDate(date.getDate() + duration);
		const endDate = date.toISOString().split('T')[0];		
        calendar.changeView('timeGrid', {
            start: '1900-01-01',
            end: endDate
        });
		
        // 일차 텍스트 지정
        const daysAll = document.querySelectorAll(".fc-col-header-cell-cushion");
        daysAll.forEach(function(e, i) {
        	e.innerText = `\${i+1}일차`;  // 백틱 안될 때 앞에 '\' 붙이기
        })
        
    });


    // document.addEventListener('DOMContentLoaded', function() {
    //     var calendarEl = document.getElementById('calendar');
    //     var calendar = new FullCalendar.Calendar(calendarEl, {
    //         locale: 'en',
    //         initialView: 'timeGridWeek',
    //         headerToolbar: {
    //             left: 'prev,next',
    //             center: 'title',
    //             right: 'timeGridDay,timeGridWeek'
    //         },
    //         events: [
    //             {
    //                 title  : 'event3',
    //                 start  : '2025-02-02T12:30:00',
    //                 end  : '2025-02-02T13:30:00'
    //                 // allDay : false
    //             }
    //         ]
    //     });
    //     calendar.render();

    //     // 11월1일부터 11월20일까지 범위 지정
    //     calendar.changeView('timeGrid', {
    //         start: '2025-02-02',
    //         end: '2025-02-20'
    //     });
    // });


    // document.addEventListener('DOMContentLoaded', function() {
    //     var calendarEl = document.getElementById('calendar2');
    //     var calendar = new FullCalendar.Calendar(calendarEl, {
    //         locale: 'en',
    //         initialView: 'timeGridWeek',
    //         headerToolbar: {
    //             left: 'prev,next',
    //             center: 'title',
    //             right: 'timeGridDay,timeGridFourDay'
    //         },
    //         views: {
    //             timeGridFourDay: {
    //                 type: 'timeGrid',
    //                 duration: { days: 4 },
    //                 buttonText: '4 day'
    //             }
    //         },
    //         events: [
    //             {
    //                 title  : 'event3',
    //                 start  : '2025-02-02T12:30:00',
    //                 end  : '2025-02-02T13:30:00'
    //                 // allDay : false
    //             }
    //         ]
    //     });
    //     calendar.render();
    // });

    function goTravelDetails() { 
		
		let ans = confirm("다음페이지로 이동합니다.");
		if (ans == true) {

			// sessionStorage에 저장
		    // sessionStorage.setItem('city', fm.city.value);
		    
		    location.href = "${pageContext.request.contextPath}/travel/travelDetails.do";

			document.getElementById('loading').style.display = 'block';
		}
	  
		return;
	}
    </script>
</body>
</html>