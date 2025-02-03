<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>여행지를 입력해주세요.</title>
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
        <div class="inner travle" id="travle-input">
            
            <section class="contents pt-30">
                <ul class="step flex justify-content-between">
                    <li class="step-item on flex justify-content-center align-items-center"><i class="fa-solid fa-pencil"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                    <li class="relative step-item flex justify-content-center align-items-center"><i class="fa-solid fa-star-of-life"></i></li>
                </ul>
            
                <h3 class="main-title center mb-70">🤔 여행지를 입력해주세요.</h3>

                <div class="mb-2 center">
                    <input type="text" placeholder="여행지를 입력해주세요." name="keyword" class="w-200">
                </div>

                <div class="map">

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