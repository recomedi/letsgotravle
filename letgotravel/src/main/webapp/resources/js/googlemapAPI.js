let map;
let markers = [];
let selectedPlace = null;
let autocomplete;
let placesService;

function initMap() {
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 37.5665, lng: 126.9782 }, // 서울 중심 좌표
        zoom: 15,
        mapTypeControl: false,
        zoomControl: false,
        scaleControl: false,
    });

    placesService = new google.maps.places.PlacesService(map);
    const input = document.getElementById("searchbox");

    autocomplete = new google.maps.places.Autocomplete(input);
    autocomplete.bindTo("bounds", map);

    // 자동완성에서 장소가 선택되었을 때
    autocomplete.addListener("place_changed", () => {
        let place = autocomplete.getPlace();
        if (!place.geometry || !place.geometry.location) {
            console.warn("자동완성된 장소에 대한 위치 정보 없음, 강제 검색 실행");
            searchFirstSuggestion(input.value);
            return;
        }
        processPlace(place);
    });

    // 엔터 키 입력 시 자동 검색
    input.addEventListener("keydown", (event) => {
        if (event.key === "Enter") {
            event.preventDefault(); 
            searchFirstSuggestion(input.value);
        }
    });
}

// 장소 정보 처리 함수
function processPlace(place) {
    if (!place.geometry || !place.geometry.location) {
        alert("선택한 장소에 대한 위치 정보를 찾을 수 없습니다.");
        return;
    }

    clearMarkers();
    const marker = new google.maps.Marker({
        map,
        position: place.geometry.location,
    });
    markers.push(marker);
    map.setCenter(place.geometry.location);
    map.setZoom(15);

    selectedPlace = {
        name: place.name,
        address: place.formatted_address || "",
        location: {
            lat: place.geometry.location.lat(),
            lng: place.geometry.location.lng(),
        },
    };

    document.getElementById("searchbox").value = place.name;
    document.getElementById("city").value = place.name; 
	console.log("이름:", selectedPlace.name);
	console.log("주소:", selectedPlace.address);
	console.log("위도:", selectedPlace.location.lat);
	console.log("경도:", selectedPlace.location.lng);
}


function searchFirstSuggestion(query) {
    if (!query) return;

    placesService.textSearch({ query }, (results, status) => {
        if (status === google.maps.places.PlacesServiceStatus.OK && results.length > 0) {
            processPlace(results[0]); 
        } else {
            alert("검색 결과를 찾을 수 없습니다.");
        }
    });
}

function clearMarkers() {
    markers.forEach((marker) => marker.setMap(null));
    markers = [];
}
