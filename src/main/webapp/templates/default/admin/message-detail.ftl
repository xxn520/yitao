<#include "layout.ftl">

<#macro overrideTitle>
    <#if model??>
    编辑消息 -
    <#else>
    添加消息 -
    </#if>
</#macro>

<#macro overrideStyle>
<link rel="stylesheet" href="${contextPath}/templates/default/admin/css/select2.min.css">
<style type="text/css">
    .touch .select2-results li:hover {
        background-color: #5897FB !important;
    }
    .select2-container--default .select2-selection--single {
        border-radius: 2px;
        border: 1px solid #E8ECF3;
        line-height: 34px;
        height: 34px;
    }
    .select2-container--default .select2-selection--single .select2-selection__rendered {
        line-height: 34px;
    }
    .select2-dropdown, .select2-container--default .select2-search--dropdown .select2-search__field {
        border: 1px solid #E8ECF3;
        border-radius: 2px;
    }
</style>
</#macro>

<#macro overrideContent>
<div class="row mg-b">
    <div class="col-xs-12">
        <h3 class="no-margin">
            <#if model??>
                编辑消息
            <#else>
                添加消息
            </#if>
        </h3>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <header class="panel-heading">填写消息信息</header>
            <div class="panel-body">
                <form class="form-horizontal" action="../message<#if model??>/${model.id!}</#if>.html<#if model??>?_method=PUT</#if>" method="post">
                    <#if model??>
                        <input type="hidden" name="id" value="${(model.id)!}">
                    </#if>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="title" id="title"  placeholder="请输入标题" rows="10" value="${(model.title)!}">
                            <label class="error-information"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">内容</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" name="detail" id="detail" type="text" placeholder="请输入内容" value="">${(model.detail)!}</textarea>
                            <label class="error-information"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">接收者</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="msgto_id" id="msgto_id" data-placeholder="请选择接收者"  style="width: 100%">
                                <option value="${(model.msgto.id)!}">${(model.msgto.name)!}</option>
                            </select>
                            <label class="error-information"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">消息类型</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="msgtype" id="msgtype" data-placeholder="请选择消息类型"  style="width: 100%">
                            </select>
                            <label class="error-information"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-success">提交</button>
                            <button type="reset" class="btn btn-reset btn-default">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </div>
</div>
</#macro>

<#macro overrideScript>
<script type="text/javascript" src="${contextPath}/templates/default/admin/js/select2.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".btn-reset").click(function() {
            location.href = "${contextPath}/admin/message.html";
        });

        $("select").select2({
            ajax: {
                url: "${contextPath}/admin/user.json",
                cache: "true",
                data: function (params) {
                    return {
                        page: params.page
                    };
                },
                processResults: function(data) {
                    var options = [{
                        id: 0,
                        text: "全部"
                    }];
                    $(data.content).each(function() {
                        options.push({
                            id: this.id,
                            text: this.username
                        });
                    });
                    return {
                        results: options,
                        pagination: {
                            more: !data.last
                        }
                    }
                }
            },
            minimumResultsForSearch: Infinity
        });

        var selectData = [{ id: 0, type: 'SYSTEM_MSG',text: '系统消息'}, {  id: 1, type: 'PRIVATE_MSG' ,text:'私信'}, {  id:2, type: 'COLLECT_PRODUCT_MSG',text:'折扣消息' }];

        $("select#msgtype").select2({
            data: selectData,
            minimumResultsForSearch: Infinity
        });

        //表单验证
        var detail = $("#detail");
        var title = $("input[name='title']");
        var msgto_id = $("#msgto_id");
        var validateFunc = function(dom){
            var validator = new Validator();
            validator.add(detail,[
                {strategy:'isNotEmpty',errorMsg:'内容不能为空!'},
            ]);
            validator.add(title,[
                {strategy:'isNotEmpty',errorMsg:'标题不能为空!'},
            ]);
            validator.add(msgto_id,[
                {strategy:'isNotEmpty',errorMsg:'接收者不能为空!'},
            ]);
            var error= validator.start(dom);
            return error;
        };

        $('input').blur(function(){
            validateFunc(this);
        });

        $('#detail').blur(function(){
            validateFunc(this);
        });

        $("#msgto_id").on('select2:close', (function(){
            validateFunc(this);
        }))

        $("form").submit(function(e) {
            var error = validateFunc();
            if(error){
                return true;
            }
            e.preventDefault();
            return false;
        });
    });
</script>
</#macro>

<#macro overrideNav>
<ol class="breadcrumb no-margin">
    <li><a href="${contextPath}/admin/index.html">首页</a></li>
    <li><a href="${contextPath}/admin/message.html">消息</a></li>
    <li class="active">
        <#if model??>
            编辑消息
        <#else>
            添加消息
        </#if>
    </li>
</ol>
</#macro>

<@layout title=overrideTitle style=overrideStyle content=overrideContent script=overrideScript nav=overrideNav>
</@layout>