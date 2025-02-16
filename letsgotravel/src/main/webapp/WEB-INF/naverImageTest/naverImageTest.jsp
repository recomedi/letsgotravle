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

		        console.log("🔍 검색어 (프론트엔드):", query); // ✅ 프론트에서 검색어 확인

		        $.ajax({
		            url: "/travel/naverImageTest/search",
		            type: "GET",
		            data: { query: query }, 
		            dataType: "json",
		            success: function(response) {
		                console.log("✅ 서버 응답:", response); 
		                displayImages(response);
		            },
		            error: function(xhr, status, error) {
		                console.log("❌ AJAX 요청 오류:", xhr.responseText);
		            }
		        });
		    }

			    
			    function displayImages(response) {
			        let imageContainer = $("#imageResults");
			        imageContainer.empty();  // 기존 이미지 제거

			        if (!response.items || response.items.length === 0) {
			            imageContainer.html("<p>이미지를 찾을 수 없습니다.</p>");
			            return;
			        }

			        response.items.forEach(item => {
			            let img = $("<img>").attr("src", item.thumbnail).attr("alt", item.title);
			            imageContainer.append(img);
			        });
			    }
			    
			    
		    </script>

</body>
</html>