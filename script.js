document.addEventListener("DOMContentLoaded", () => {
    const menuButton = document.getElementById("menu-toggle");
    const sideMenu = document.getElementById("side-menu");

    // 메뉴 버튼 클릭 이벤트
    menuButton.addEventListener("click", () => {
        sideMenu.classList.toggle("active"); // active 클래스 추가/제거
    });
});
