<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>일정을 확정해주세요.</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

    <!-- 폰트어썸 불러오기 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">

    <link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.css" rel="stylesheet">
      
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js'></script>

</head>
<body>
    <div class="wrap">
        <header class="hd"></header>
        <div class="inner travle" id="travle-modify">
            
            <section class="contents pt-30">
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
                    <button class="btn blue">다음</button>
                    <button class="btn">뒤로</button>
                </div>
            </section>
        </div>
        <footer class="ft"></footer>
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

    
    const calendarBox = document.querySelector(".calendar-box");
    const num = 18;
    calendarBox.style.width = 121 * num + 47 + "px";


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
            events: [
                {
                    title: '해리포터 스튜디오',
                    start: '2025-02-02T12:30:00',
                    end: '2025-02-02T13:30:00',
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
                    start: '2025-02-03T16:30:00',
                    end: '2025-02-03T17:30:00',
                    backgroundColor: '#DCEDF3',
                    borderColor: '#4FA3C4',
                    textColor: '#333'
                    // allDay : false
                }
            ]
        });
        calendar.render();

        // 11월1일부터 11월20일까지 범위 지정
        calendar.changeView('timeGrid', {
            start: '2025-02-02',
            end: '2025-02-20'
        });
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





    // document.addEventListener('DOMContentLoaded', function() {
    // let draggableEl = document.getElementById('travel-list');
    // let calendarEl = document.getElementById('mycalendar');

    // let calendar = new Calendar(calendarEl, {
    //     plugins: [ interactionPlugin ],
    //     droppable: true
    // });
    // calendar.render();

    // let draggable = new Draggable(draggableEl);

    // // when you're done...
    // // draggable.destroy();
    // });

    </script>
</body>
</html>