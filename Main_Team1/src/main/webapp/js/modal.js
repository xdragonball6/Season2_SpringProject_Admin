
// 모달 열기
function openModal(fid) {
	var modalContent = document.getElementById("modalContent");
    var productInfo = document.getElementById("info-" + fid);

    // 모달 창에 제품 정보를 표시
    modalContent.innerHTML = productInfo.innerHTML;
    // 스크롤 막기
    document.body.style.overflow = "hidden";
    // 모달 창 열기
    var modal = document.getElementById("myModal");
    modal.style.display = "flex"; // display 속성을 flex로 변경
    modal.style.alignItems = "center"; // 수직 가운데 정렬
    modal.style.justifyContent = "center"; // 수평 가운데 정렬
}

function openModal2(fid) {
	var modalContent = document.getElementById("modalContent");
    var reviewInfo = document.getElementById("info-" + fid);

    // 모달 창에 리뷰 정보를 표시
    modalContent.innerHTML = reviewInfo.innerHTML;
    // 스크롤 막기
    document.body.style.overflow = "hidden";
    // 모달 창 열기
    var modal = document.getElementById("myModal");
    modal.style.display = "flex";
    modal.style.alignItems = "center";
    modal.style.justifyContent = "center";
}
function closeModal() {
  var modal = document.getElementById("myModal");
  // 스크롤 허용하기
  document.body.style.overflow = "auto";
  // 모달 창 닫기
  modal.style.display = "none";
  }


function hideAllInfos() {
    // 모든 상품 및 리뷰 정보 숨기기
    var allInfos = document.querySelectorAll("[id^='productInfo-'], [id^='reviewInfo-']");
    for (var i = 0; i < allInfos.length; i++) {
        allInfos[i].style.display = "none";
    }
    }