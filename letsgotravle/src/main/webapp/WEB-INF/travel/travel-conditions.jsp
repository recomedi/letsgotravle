<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>여행조건을 입력해주세요.</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

    <!-- 폰트 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <!-- 폰트어썸 불러오기 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
</head>
<body>
    <div class="wrap">
        <header class="hd"></header>
        <div class="inner travle" id="travle-conditions">
            
            <section class="contents pt-30">
                <ul class="step flex justify-content-between">
                    <li class="step-item on flex justify-content-center align-items-center"><i class="fa-solid fa-pencil"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                </ul>
            
                <h3 class="main-title center mb-70">🤔 여행 조건을 입력해주세요.</h3>

                <div class="row flex justify-content-between mb-2">
                    <div class="col">
                        <label><span class="title bold">👥 인원</span><br>
                        <div class="input-box ml-3 mt-1">
                            <input type="text" name="peopleCount" class="center"> 명</label>
                        </div>
                    </div>
                    <div class="col" style="margin-left: -43px;">
                        <label><span class="title bold">✈️️ 출발일</span><br>
                        <div class="input-box ml-3 mt-1">
                            <input type="month" name="departureMonth" class="center"></label>
                        </div>
                    </div>
                    <div class="col mr-20">
                        <label><span class="title bold">📅 기간</span><br>
                        <div class="input-box ml-3 mt-1">
                            <input type="text" name="duration" class="center"> 일</label>
                        </div>
                    </div>
                </div>
                <div class="row flex justify-content-between mb-2">
                    <div class="col">
                        <p class="title bold">👨‍👩‍👧‍👦 구성원</p>
                        <div class="badge-label-box ml-3 mt-1 flex">
                            <input type="radio" name="purpose" value="family" class="none" id="purpose-family">
                            <label class="badge-label" for="purpose-family"> 가족</label>
                            <input type="radio" name="purpose" value="friend" class="none" id="purpose-friend">
                            <label class="badge-label" for="purpose-friend"> 친구</label>
                            <input type="radio" name="purpose" value="couple" class="none" id="purpose-couple">
                            <label class="badge-label" for="purpose-couple"> 연인</label>
                            <input type="radio" name="purpose" value="alone" class="none" id="purpose-alone">
                            <label class="badge-label" for="purpose-alone"> 혼자</label>
                        </div>
                    </div>
                    <div class="col">
                        <label><span class="title bold">💰 예산</span><br>
                        <div class="input-box ml-3 mt-1">
                            <input type="text" name="budgetMin" class="center"> 만원 ~ <input type="text" name="budgetMax" class="center"> 만원</label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <p class="title bold">🎮 테마(중복선택가능)</p>
                        <div class="duplication badge-label-box ml-3 mt-1 flex flex-wrap">
                            <input type="checkbox" name="styles" id="filial-piety" class="none">
                            <label class="badge-label" for="filial-piety"> 효도</label>
                            <input type="checkbox" name="styles" id="healing" class="none">
                            <label class="badge-label" for="healing"> 힐링</label>
                            <input type="checkbox" name="styles" id="cost-effectiveness" class="none">
                            <label class="badge-label" for="cost-effectiveness"> 가성비</label>
                            <input type="checkbox" name="styles" id="staycation" class="none">
                            <label class="badge-label" for="staycation"> 호캉스</label>
                            <input type="checkbox" name="styles" id="epicurism" class="none">
                            <label class="badge-label" for="epicurism"> 식도락</label>
                            <input type="checkbox" name="styles" id="shopping" class="none">
                            <label class="badge-label" for="shopping"> 쇼핑</label>
                            <input type="checkbox" name="styles" id="historic-site" class="none">
                            <label class="badge-label" for="historic-site"> 유적지</label>
                            <input type="checkbox" name="styles" id="landscape" class="none">
                            <label class="badge-label" for="landscape"> 자연경관</label>
                            <input type="checkbox" name="styles" id="activity" class="none">
                            <label class="badge-label" for="activity"> 액티비티</label>
                        </div>
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
</body>
</html>