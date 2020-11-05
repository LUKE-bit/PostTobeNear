function registerClick() {
  let username = document.getElementById("username");
  let password = document.getElementById("password");
  let surePwd = document.getElementById("surePwd");
  let errorMsg = document.getElementsByClassName("errorMsg")[0];
  if (username.value == "") {
    errorMsg.innerHTML = "请输入用户名";
    setTimeout(() => {
      errorMsg.innerHTML = ""
    }, 3000);
    return false
  }
  if (password.value == "") {
    errorMsg.innerHTML = "请输入密码";
    setTimeout(() => {
      errorMsg.innerHTML = ""
    }, 3000);
    return false
  }
  if (surePwd.value == "") {
    errorMsg.innerHTML = "请输入确认密码";
    setTimeout(() => {
      errorMsg.innerHTML = ""
    }, 3000);
    return false
  } else if (surePwd.value != password.value) {
    errorMsg.innerHTML = "请输入相同密码";
    setTimeout(() => {
      errorMsg.innerHTML = ""
    }, 3000);
    return false
  }
  return true
}
