function overbooking() {
  let money = document.getElementById("money");
  let time = document.getElementById("time");
  let describe = document.getElementById("describe");
  let errorMsg = document.getElementsByClassName("errorMsg")[0];
  if (money.value == "") {
    errorMsg.innerHTML = "请输入金额"
    setTimeout(() => {
      errorMsg.innerHTML = ""
    }, 3000)
    return false
  } else {
    if (money.value > 5) {
      errorMsg.innerHTML = "金额最大值为5元"
      setTimeout(() => {
        errorMsg.innerHTML = ""
        money.value = 5
      }, 3000)
      return false;
    }
  }
  if (time.value == "") {
    errorMsg.innerHTML = "请输入时间"
    setTimeout(() => {
      errorMsg.innerHTML = ""
    }, 3000)
    return false
  }
  if (describe.value == "") {
    errorMsg.innerHTML = "请输入描述"
    setTimeout(() => {
      errorMsg.innerHTML = ""
    }, 3000)
    return false
  }
  return true
}