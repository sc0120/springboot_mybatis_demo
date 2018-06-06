/**
 * Created by Administrator on 2017/10/9 0009.
 */
layui.use(['form', 'layedit', 'laydate','element','jquery','upload'], function(){
    var form = layui.form
        ,$ = layui.$ //重点处
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate
        ,element = layui.element
        ,upload = layui.upload;



    $(".btn-login").on("click",function(){
        var name =  $("input[name='name']").val();
        var password =  $("input[name='password']").val();
        if(name==""){
            layer.tips("请输入帐号名称",$("input[name='name']"));
            return;
        }
        if(password==""){
            layer.tips("请输入密码",$("input[name='password']"));
            return;
        }
         var data = new FormData();
         data.append("name",name);
        data.append("password",password);
        $.ajax({
            type: "POST",
            url:"/admin/do_login",
            data:data,
            contentType: false,
            processData: false,
            success: function(data) {
                if(data.code==0){
                    //layer.open({
                    //    title: '提示'
                    //    ,content: data.data.msg
                    //});
                    location.href = "/admin/index";
                }else{
                    layer.msg({
                        title: '提示'
                        ,content: data.msg
                    })
                }
                //console.log(data);

            }
        });

    });





});
