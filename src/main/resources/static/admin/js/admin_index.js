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

    //日期
    laydate.render({
        elem: '#date'
    });
    laydate.render({
        elem: '#date1'
    });
    laydate.render({
        elem: '#date2',
        type: 'datetime'
    });


    //执行实例
    var uploadInst = upload.render({
        elem: '#test1' //绑定元素
        ,url: '/admin/test' //上传接口
        ,method:'POST'
        ,field:'image'
        ,done: function(res){
            //上传完毕回调
        }
        ,error: function(){
            //请求异常回调
        }
    });



    //创建一个编辑器
    var editIndex = layedit.build('LAY_demo_editor');

    //自定义验证规则
    form.verify({
        title: function(value){
            if(value.length < 5){
                return '标题至少得5个字符啊';
            }
        }
        ,pass: [/(.+){6,12}$/, '密码必须6到12位']
        ,content: function(value){
            layedit.sync(editIndex);
        }
    });

    $("input[name='username']").on('click',function(){
        layer.msg("aaa");
    });

    $(".btn-login").on('click',function(){
        layer.tips('aaa',this);
    });

    //监听指定开关
    form.on('switch(switchTest)', function(data){
        layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
            offset: '6px'
        });
        layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
    });

    //监听提交
    form.on('submit(login)', function(data){
        layer.alert(JSON.stringify(data.field), {
            title: '最终的提交信息'
        })
        return false;
    });


});
