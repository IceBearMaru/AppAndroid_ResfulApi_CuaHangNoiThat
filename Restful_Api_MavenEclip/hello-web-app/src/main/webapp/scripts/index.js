var slideItem = document.querySelector(".slide-show").children;
var slideLength = slideItem.length;
var nextBtn = document.querySelector("#next");
var prevBtn = document.querySelector("#prev");
var number = document.querySelector("#number");
var index = 0;

nextBtn.onclick = function () {
  handleChangeImage("next");
};
prevBtn.onclick = function () {
  handleChangeImage("prev");
};

function handleChangeImage(direction) {
  if (direction == "next") {
    index++;
    if (index == slideLength) {
      index = 0;
    }
  } else {
    index--;
    if (index < 0) {
      index = slideLength - 1;
    }
  }

  for (var i = 0; i < slideLength; i++) {
    slideItem[i].classList.remove("active");
  }
  slideItem[index].classList.add("active");
}

function themvaogio(id, tensp, gia) {
  let cartItems = JSON.parse(localStorage.getItem("cart"));
  if (cartItems == null) cartItems = [];
  var dacosanphamtronggio = false;
  for (i = 0; i < cartItems.length; i++) {
    var sp = cartItems[i];
    if (sp[0] == id) {
      sp[3]++;
      dacosanphamtronggio = true;
    }
  }
  if (dacosanphamtronggio == false) {
    cartItems.push([id, tensp, gia, 1]);
  }
  localStorage.setItem("cart", JSON.stringify(cartItems));
  alert("Thêm vào giỏ hàng thành công");
}
