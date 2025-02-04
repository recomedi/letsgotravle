<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <script>
	// 메세지
	const msg = "${requestScope.msg}";
	if (msg != null && msg != "") {
	 alert(msg);
	}
	</script>

    <header class="header">
        <section class="allheaders">
        <a href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/resources/images/logo.png" class="logo" alt="LOGOTRIP Logo">
        </a>
        <nav id="side-menu" class="side-menu">
            <ul>
                <li><a href="login.html">로그인</a></li>
                <li><span>|</span></li> <!-- 구분자 추가 -->
                <li><a href="signup.html">회원가입</a></li>
                <li><span>|</span></li> <!-- 구분자 추가 -->
                <li><a href="planner.html">여행 일정 짜기</a></li>
                <li class="invisible-item"></li> <!-- 안 보이는 li 추가 -->
            </ul>
        </nav>
        <div class="menu-container">
            <button id="menu-toggle" class="menu-button">
                <span class="bar"></span>
                <span class="bar"></span>
                <span class="bar"></span>
            </button>
        </div>
 </section>
  </header> 