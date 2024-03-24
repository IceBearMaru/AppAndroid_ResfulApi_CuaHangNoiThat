function doihinh(obj) {
  var diachi = obj.src;
  document.getElementById("hinh").src = diachi;
}

function themvaogio(id, tensp, gia) {
  console.log(document.getElementById('phanloai').value);
  if (document.getElementById('phanloai').value == 0) {
    alert('Vui lòng chọn quà tặng')
    return
  }

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
