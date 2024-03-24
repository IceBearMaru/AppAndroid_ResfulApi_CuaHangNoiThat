let cartItems = JSON.parse(localStorage.getItem("cart"));

function hiengiohang() {
  document.getElementById("cart").innerHTML = `
			<caption>Giỏ hàng của bạn</caption>
			<tr>
				<th>Tên sản phẩm</th>
				<th> Giá sản phẩm</th>
				<th> Số lượng</th>
				<th> Xóa</th>
			</tr>
		`;
  for (let i = 0; i < cartItems.length; i++) {
    const sp = cartItems[i]; //[1, 'sp1', 100000 , 2]
    const id = sp[0];
    const ten = sp[1];
    const gia = sp[2];
    const soluong = sp[3];
    document.getElementById("cart").innerHTML += /*html*/ ` 
				<tr>
					<td>${ten}</td>
					<td> ${gia}</td>
					<td> <input type='number' class='num' value='${soluong}' onchange="totalCost(${id}, this.value)"></td>
					<td> <button class="remove-item" type='button' onclick="xoa(${i})"> Xóa </button></td>
				</tr>
			`;
  }
  document.getElementById("cart").innerHTML += `
			<tr>
				<td colspan="4" class='tongket'></td>
			</tr>
		`;

  grandTotal();
}

function totalCost(id, num) {
  for (let i = 0; i < cartItems.length; i++) {
    if (cartItems[i][0] == id) {
      cartItems[i][3] = Number(num);
    }
  }
  localStorage.setItem("cart", JSON.stringify(cartItems));
  grandTotal();
}

function grandTotal() {
  let tongsoluong = 0;
  let tongtien = 0;
  for (let i = 0; i < cartItems.length; i++) {
    const sp = cartItems[i];
    const gia = sp[2];
    const soluong = sp[3];
    tongsoluong += soluong;
    tongtien += gia * soluong;
  }
  document.querySelector(".tongket").innerHTML = `
		Tổng sổ lượng: <b>${tongsoluong}</b> - Tổng tiền : <b>${tongtien.toLocaleString(
    "vi"
  )} VNĐ</b>   
	`;
}

function xoa(i) {
  cartItems.splice(i, 1);
  localStorage.setItem("cart", JSON.stringify(cartItems));
  hiengiohang();
}

hiengiohang();
