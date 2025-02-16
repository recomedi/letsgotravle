<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>네이버 이미지 검색</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        .image-container {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
        }
        .image-container img {
            width: 200px;
            height: auto;
            border-radius: 10px;
            box-shadow: 2px 2px 10px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>

    <h2>🔍 네이버 이미지 검색</h2>
    <input type="text" id="searchQuery" placeholder="검색어 입력">
    <button onclick="searchImages()">검색</button>

    <div class="image-container" id="imageResults"></div>

    <script>
			    function searchImages() {
			        let query = $("#searchQuery").val();
			        if (!query) {
			            alert("검색어를 입력하세요!");
			            return;
			        }
			
			        $.ajax({
			            url: "/travel/naverImageTest/search",  // ✅ 프로젝트 context 고려하여 경로 확인
			            type: "GET",
			            data: { query: query },
			            dataType: "json",
			            success: function(response) {
			                console.log(response); // ✅ 응답 디버깅
			                displayImages(response);
			            },
			            error: function(xhr, status, error) {
			                console.log(xhr);
			                console.log(status);
			                console.log(error);
			                alert("이미지 검색 중 오류가 발생했습니다.");
			            }
			        });
			    }

    </script>

</body>
</html>
