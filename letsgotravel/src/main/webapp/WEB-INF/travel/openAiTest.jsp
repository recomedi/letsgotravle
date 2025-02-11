<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONObject, org.json.JSONArray" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>AI 여행 추천 결과</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body { font-family: 'Arial', sans-serif; padding: 20px; background-color: #f9f9f9; }
        .container { max-width: 800px; margin: auto; background: white; padding: 20px; border-radius: 10px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); }
        h2 { color: #333; text-align: center; }
        .result-box { margin-top: 20px; padding: 15px; border: 1px solid #ddd; background: #fff; border-radius: 8px; }
        .json-output { white-space: pre-wrap; font-size: 14px; background: #eee; padding: 10px; border-radius: 5px; }
    </style>
</head>
<body>

<div class="container">
    <h2>📍 AI 여행 추천 결과</h2>

    <!-- JSON 원본 출력 -->
    <div class="result-box">
        <h3>📜 JSON 원본 데이터</h3>
        <pre class="json-output" id="jsonData">데이터를 불러오는 중...</pre>
    </div>

    <!-- 상세 결과 출력 -->
    <div class="result-box" id="formattedData">
        <h3>📋 상세 추천 내용</h3>
        <p>데이터를 불러오는 중...</p>
    </div>
<div>
    <textarea id="promptInput" placeholder="여행 추천 요청을 입력하세요"></textarea>
    <button onclick="getTravelRecommendation()">추천 요청</button>
</div>
<div id="result" style="margin-top: 20px; border: 1px solid #ccc; padding: 10px;">
    여기 여행 추천 결과가 표시됩니다.
</div>

</div>

<script>
function getTravelRecommendation() {
    const prompt = document.getElementById("promptInput").value;

    $.ajax({
        url: "/myapp/api/openai/recommend",
        type: "POST",
        contentType: "application/json; charset:UTF-8",
        dataType: "json",
        data: JSON.stringify({ prompt: prompt }),
        success: function (response) {
            console.log("✅ API 응답:", response);

            // JSON 데이터를 UTF-8로 변환
            let decodedContent = response.choices[0].message.content;
            
            // HTML 요소에 결과 표시
            document.getElementById("result").innerText = decodedContent;
        },
        error: function (xhr, status, error) {
            console.error("❌ API 호출 오류:", error);
            alert("API 호출 중 문제가 발생했습니다.");
        }
    });
}







    function formatJsonResponse(response) {
        try {
            let jsonData = JSON.parse(response);
            let html = "<ul>";
            html += `<li><strong>🌍 추천 여행지:</strong> ${jsonData.destinationName || "N/A"}</li>`;
            html += `<li><strong>📍 대표 관광지:</strong> ${jsonData.majorAttractions || "N/A"}</li>`;
            html += `<li><strong>🍽 대표 음식:</strong> ${jsonData.food || "N/A"}</li>`;
            html += `<li><strong>🌡 평균 기온:</strong> ${jsonData.avgWeather || "N/A"}℃</li>`;
            html += `<li><strong>📊 물가:</strong> ${jsonData.exchangeRate || "N/A"}</li>`;
            html += `<li><strong>🚦 치안:</strong> ${jsonData.safety || "N/A"}</li>`;
            html += `<li><strong>🛑 주의해야 할 기간:</strong> ${jsonData.warningPeriod || "N/A"}</li>`;
            html += `<li><strong>🎉 축제 & 행사:</strong> ${jsonData.festival || "N/A"}</li>`;
            html += `<li><strong>🗺 일정 추천:</strong> ${jsonData.itinerary || "N/A"}</li>`;
            html += "</ul>";
            return html;
        } catch (e) {
            return "<p style='color:red;'>❌ 데이터 파싱 오류! JSON 구조를 확인하세요.</p>";
        }
    }
</script>

</body>
</html>
