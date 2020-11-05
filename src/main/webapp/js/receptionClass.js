$(document).ready(function () {
  $.get("PostOrder", function (response) {
    console.log(response);
    if (response.ok == 0) {
      alert("未登录，请您先登录");
    }
    else {
      for (let i = 0; i < response.orders.length; i++) {
        if (response.orders[i].type == '代取快递') {
          $('#home').append('<div class="smallCard"><p><strong>发单人：</strong>' + response.orders[i].postId + '</p><p><strong>快递描述：</strong>' + response.orders[i].description + '</p><p><strong>金钱：</strong>' + response.orders[i].money + '元</p><p> <strong>时间：</strong>' + response.orders[i].time + '以前</p></div>');
          $('.smallCard').attr('data-index', response.orders[i].orderId);
        }
    //     $('.smallCard').on('click', function () {
    //       $('.container').append('<div id="hide"><div id="content"></div><div id="bttn"><p class="warn">确认接收后请在我的订单内查看完整订单</p><button id="close">关闭</button><button id="confirm">确认接收</button></div></div>');
    //       // var temp = this;
    //       // var text = $(temp).html();
    //       // $('#content').html(text);

    //       $('#hide').css("display", "block");
    //       var params = { orderId: $(temp).attr('data-index'), isTake: 1 };
    //       $('#confirm').on('click', function () {
    //         $.ajax({
    //           url: 'TakeOrder',
    //           type: 'put',
    //           // data: {
    //           //     orderId : $(temp).attr('data-index') ,
    //           //     isTake : 1
    //           // },
    //           data: JSON.stringify(params),
    //           contentType: 'application/json',
    //           success: function (response) {
    //             if (response.ok != 1) {
    //               alert("状态更新失败！" + response.reason);
    //               return;
    //             }
    //             else {
    //               alert("状态更新成功！")
    //             }
    //           }
    //         })
    //         $('#hide').css("display", "none");
    //         $(temp).remove();
    //         $('#hide').remove();

    //       })
    //       $('#close').on('click', function () {
    //         $('#hide').css("display", "none");
    //         $('#hide').remove();
    //       })
    //     })



    //   }
     }
    }
  })
})
$('#hint').on('click', function () {
  $('#home').children('div').remove();
  $.get("PostOrder", function (response) {
    if (response.ok == 0) {
      alert("未登录，请您先登录");
    }
    else {
      for (let i = 0; i < response.orders.length; i++) {
        if (response.orders[i].type == '代取快递') {
          $('#home').append('<div class="smallCard"><p><strong>发单人：</strong>' + response.orders[i].postId + '</p><p><strong>快递描述：</strong>' + response.orders[i].description + '</p><p><strong>金钱：</strong>' + response.orders[i].money + '元</p><p> <strong>时间：</strong>' + response.orders[i].time + '以前</p></div>');
          $('.smallCard').attr('data-index', response.orders[i].orderId);
        }
        // $('.smallCard').on('click', function () {
        //   $('.container').append('<div id="hide"><div id="content"></div><div id="bttn"><p class="warn">确认接收后请在我的订单内查看完整订单</p><button id="close">关闭</button><button id="confirm">确认接收</button></div></div>');
        //   var temp = this;
        //   var text = $(temp).html();
        //   $('#content').html(text);

        //   $('#hide').css("display", "block");
        //   var params = { orderId: $(temp).attr('data-index'), isTake: 1 };
        //   $('#confirm').on('click', function () {
        //     $.ajax({
        //       url: 'TakeOrder',
        //       type: 'put',
        //       // data: {
        //       //     orderId : $(temp).attr('data-index') ,
        //       //     isTake : 1
        //       // },
        //       data: JSON.stringify(params),
        //       contentType: 'application/json',
        //       success: function (response) {
        //         if (response.ok != 1) {
        //           alert("状态更新失败！" + response.reason);
        //           return;
        //         }
        //         else {
        //           alert("状态更新成功！")
        //         }
        //       }
        //     })
        //     $('#hide').css("display", "none");
        //     $(temp).remove();
        //     $('#hide').remove();

        //   })
        //   $('#close').on('click', function () {
        //     $('#hide').css("display", "none");
        //     $('#hide').remove();
        //   })
        // })


      }
    }
  })
})


