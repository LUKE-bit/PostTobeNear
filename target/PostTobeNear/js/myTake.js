$(function(){
    $.get("SearchMyTakeOrder", function(response){
        console.log(response);
        for(let i=0; i<response.length; i++)
        {
            if(response[i].type == '代取快递'){
                $('#home').append('<div class="smallCard"><p><strong>发单人：</strong>'+ response[i].postId + '</p><p><strong>快递描述：</strong>'+ response[i].description +'</p><p><strong>金钱：</strong>'+ response[i].money +'元</p><p> <strong>时间：</strong>'+ response[i].time +'以前</p></div>');
                $('.smallCard').attr('data-index', response[i].orderId);
                $('.smallCard').on('click',function(){
                    var temp = this;
                    if(temp.isTake == 1)
                    {
                        $('.container').append('<div id="hide"><div id="content"></div><div id="bttn"><button id="close">关闭</button><button id="confirm">确认完成</button></div></div>');
                    
                        var text = $(temp).html();
                        $('#content').html(text);
                        
                        $('#hide').css("display","block");
                        var params = {orderId : $(temp).attr('data-index'), isTake : 2};
                        $('#confirm').on('click',function(){
                            $.ajax({
                                url:'TakeOrder',
                                type: 'put',
                            
                                data: JSON.stringify(params),
                                contentType: 'application/json',
                                success: function(response){
                                    if(response.ok != 1)
                                    {
                                        alert("状态更新失败！" + response.reason);
                                        return;
                                    }
                                    else{
                                        alert("状态更新成功！")
                                    }
                                }
                            })
                            
                            this.text("已完成");
                            this.css("background", "orangered");
                
                        })
                        $('#close').on('click', function(){
                            $('#hide').css("display","none");
                            $('#hide').remove();
                        })
                    }
                    else if(temp.isTake == 2)
                    {
                        $('.container').append('<div id="hide"><div id="content"></div><div id="bttn"><button id="close">关闭</button><button id="confirm" style="background-color: orangered;">已完成</button></div></div>');
                        var text = $(temp).html();
                        $('#content').html(text);                     
                        $('#hide').css("display","block");
                        $('#close').on('click', function(){
                            $('#hide').css("display","none");
                            $('#hide').remove();
                        })
                    }
                    
                })
            }
       
        
        }
    })
    $('#hint').on('click',function(){
        $('#home').children('div').remove();
        $.get("SearchMyTakeOrder", function(response){
            console.log(response);
            for(let i=0; i<response.length; i++)
            {
                if(response[i].type == '代取快递'){
                    $('#home').append('<div class="smallCard"><p><strong>发单人：</strong>'+ response[i].postId + '</p><p><strong>快递描述：</strong>'+ response[i].description +'</p><p><strong>金钱：</strong>'+ response[i].money +'元</p><p> <strong>时间：</strong>'+ response[i].time +'以前</p></div>');
                    $('.smallCard').attr('data-index', response[i].orderId);
                    $('.smallCard').on('click',function(){
                        var temp = this;
                        if(temp.isTake == 1)
                        {
                            $('.container').append('<div id="hide"><div id="content"></div><div id="bttn"><button id="close">关闭</button><button id="confirm">确认完成</button></div></div>');
                        
                            var text = $(temp).html();
                            $('#content').html(text);
                            
                            $('#hide').css("display","block");
                            var params = {orderId : $(temp).attr('data-index'), isTake : 2};
                            $('#confirm').on('click',function(){
                                $.ajax({
                                    url:'TakeOrder',
                                    type: 'put',
                                
                                    data: JSON.stringify(params),
                                    contentType: 'application/json',
                                    success: function(response){
                                        if(response.ok != 1)
                                        {
                                            alert("状态更新失败！" + response.reason);
                                            return;
                                        }
                                        else{
                                            alert("状态更新成功！")
                                        }
                                    }
                                })
                                this.text("已完成");
                                this.css("background", "orangered");
                    
                            })
                            $('#close').on('click', function(){
                                $('#hide').css("display","none");
                                $('#hide').remove();
                            })
                        }
                        else if(temp.isTake == 2)
                        {
                            $('.container').append('<div id="hide"><div id="content"></div><div id="bttn"><button id="close">关闭</button><button id="confirm" style="background-color: orangered;">已完成</button></div></div>');
                            var text = $(temp).html();
                            $('#content').html(text);                     
                            $('#hide').css("display","block");
                            $('#close').on('click', function(){
                                $('#hide').css("display","none");
                                $('#hide').remove();
                            })
                        }
                        
                    })
                }
           
            
            }
        })
    })
})

    