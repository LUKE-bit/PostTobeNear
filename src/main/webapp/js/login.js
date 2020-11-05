function btnClick() {
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
  let errorMsg = document.getElementsByClassName("errorMsg")[0];
  if (username.length === 0) {
    errorMsg.innerHTML = "请输入用户名"
    setTimeout(() => {
      errorMsg.innerHTML = "";
    }, 4000)
    return false
  }
  if (!isPassword(password)) {
    errorMsg.innerHTML = '密码只能输入6-20个字母、数字、下划线'
    setTimeout(() => {
      errorMsg.innerHTML = "";
    }, 4000)
    return false
  }
  return true
}

function isPassword(s) {
  let patrn = /^(\w){6,20}$/;
  if (!patrn.exec(s)) return false;
  return true
}