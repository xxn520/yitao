<#include "layout.ftl">

<#macro overrideTitle>
    <#if model??>
    编辑商品 -
    <#else>
    添加商品 -
    </#if>
</#macro>

<#macro overrideStyle>
<link rel="stylesheet" href="${contextPath}/templates/default/admin/css/select2.min.css">
<link rel="stylesheet" href="${contextPath}/templates/default/admin/css/trumbowyg.min.css">
<link rel="stylesheet" href="${contextPath}/templates/default/admin/css/dropzone.min.css">
<style type="text/css">
    .touch .select2-results li:hover {
        background-color: #5897FB !important;
    }
    .trumbowyg-box {
        width: 100%;
        margin: 0;
        border-radius: 2px;
    }
    .trumbowyg-button-pane {
        background: none;
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
    .select2-dropdown, .select2-container--default .select2-search--dropdown .select2-search__field, .dropzone {
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
                编辑商品
            <#else>
                添加商品
            </#if>
        </h3>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <ul class="nav nav-tabs">
                <li class="active"><a href="javascript:void(0)">商品信息</a></li>
                <#if (model.id)??>
                    <li><a href="${contextPath}/admin/discount/${(model.discount.id)!}.html?product_id=${(model.id)!}">折扣信息</a></li>
                </#if>
            </ul>
            <div class="panel-body">
                <form class="form-horizontal" action="../product<#if model??>/${model.id!}</#if>.html<#if model??>?_method=PUT</#if>" method="post">
                    <#if model??>
                        <input type="hidden" name="id" value="${(model.id)!}">
                        <input type="hidden" name="discount_id" value="${(model.discount.id)!}">
                    </#if>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品名</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="name" id="name" type="text" placeholder="请输入商品名"  value="${(model.name)!}">
                            <label class="error-information"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商家</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="biz_id" id="biz_id" data-placeholder="请选择商家"  style="width: 100%">
                                <option value="${(model.biz.id)!}">${(model.biz.name)!}</option>
                            </select>
                            <label class="error-information"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品介绍</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" name="introduce" id="introduce" type="text" placeholder="请输入商品介绍" rows="10">${(model.introduce)!}</textarea>
                            <label class="error-information"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">封面图</label>
                        <div class="col-sm-10">
                            <div class="upload dropzone <#if (model.coverPhoto)??>dz-started</#if>" id="upload">
                                <#if (model.coverPhoto)??>
                                    <div class="dz-preview dz-processing dz-success dz-complete dz-image-preview">
                                        <div class="dz-image">
                                            <img src="${(model.coverPhoto)!}">
                                        </div>
                                        <div class="dz-details">
                                            <div class="dz-filename">
                                                <span data-dz-name="">${(model.coverPhoto)!}</span>
                                            </div>
                                        </div>
                                        <a class="dz-remove" href="javascript:undefined;" data-dz-remove="">删除文件</a>
                                        <input type="hidden" name="coverPhoto" value="${(model.coverPhoto)!}">
                                    </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">价格</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="price" type="text" placeholder="请输入价格" value="${(model.price)!}">
                            <label class="error-information"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">分类</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="categories" id="categories" data-placeholder="请选择分类" multiple="multiple" style="width: 100%">
                                <#if (model.categories)??>
                                <#list model.categories as category>
                                <option value="${category.id}" selected="selected">${category.name}</option>
                                </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">品牌</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="brand_id" id="brand_id" data-placeholder="请选择品牌"  style="width: 100%">
                                <option value="${(model.brand.id)!}">${(model.brand.name)!}</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否健康</label>
                        <div class="col-sm-10">
                            <input class="js-switch-green" type="checkbox" name="health" value="true"<#if (model.health)??&&(model.health)> checked</#if>/>
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
<script type="text/javascript" src="${contextPath}/templates/default/admin/js/dropzone.min.js"></script>
<script type="text/javascript" src="${contextPath}/templates/default/admin/js/switchery/switchery.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".btn-reset").click(function() {
            location.href = "${contextPath}/admin/product.html";
        });

        $("#biz_id").select2({
            ajax: {
                url: "${contextPath}/admin/biz.json",
                cache: "true",
                data: function (params) {
                    return {
                        page: params.page
                    };
                },
                processResults: function(data) {
                    var options = [];
                    $(data.content).each(function() {
                        options.push({
                            id: this.id,
                            text: this.name
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

        $("#brand_id").select2({
            ajax: {
                url: "${contextPath}/admin/brand.json",
                cache: "true",
                data: function (params) {
                    return {
                        page: params.page
                    };
                },
                processResults: function(data) {
                    var options = [];
                    $(data.content).each(function() {
                        options.push({
                            id: this.id,
                            text: this.name
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

        $("#categories").select2({
            ajax: {
                url: "${contextPath}/admin/category/children.json",
                cache: true,
                data: function (params) {
                    return {
                        page: params.page
                    };
                },
                processResults: function(data) {
                    var options = [];
                    $(data.content).each(function() {
                        options.push({
                            id: this.id,
                            text: this.name
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
            tags: true,
            minimumResultsForSearch: Infinity
        });

        Dropzone.options.upload = {
            url: "${contextPath}/admin/upload.json",
            maxFilesize: 2,
            addRemoveLinks: true,
            createImageThumbnails: false,
            acceptedFiles: "image/*",
            dictDefaultMessage: "拖放文件到此上传",
            dictRemoveFile: "删除文件",
            dictCancelUpload: "取消上传",
            dictRemoveFileConfirmation: "真的要删除这个文件吗？",
            init: function() {
                var myDropzone = this;
                myDropzone.on("success", function(file, data, e) {
                    var exists = false;
                    $(".upload img").each(function() {
                        if ($(this).attr("src") == data.url) {
                            toastr.error('这张图片已经上传过了哟。');
                            exists = true;
                        }
                    });
                    if (exists) {
                        myDropzone.removeFile(file);
                        return;
                    }
                    $(file.previewElement).removeClass("dz-file-preview").addClass("dz-image-preview").find("img").attr("src", data.url).attr("alt", data.file_name);
                    $(file.previewElement).find(".dz-filename>span").text(data.url);
                    $(file.previewElement).append("<input type=\"hidden\" name=\"coverPhoto\" value=\"" + data.url + "\">");
                });
            }
        };
        $(".dz-remove").click(function() {
            if(confirm(Dropzone.options.upload.dictRemoveFileConfirmation)) {
                $(this).parent().remove();
            }
        });
        $(".upload").on("click", ".dz-preview", function() {
            var src = $(this).find("img").attr("src");
            switch (imagePickerName) {
                case "cover":
                    $(".form-control[name=cover]").val(src);
                    $(".image-picker").hide();
                    break;
                case "content":
                    var t = $("textarea[name=content]").data("trumbowyg");
                    /* showImagePicker(src, t); */
// 			console.log(t.$box);
                    $("input[name=text]", t.$box).val(src);
                    break;
            }
        }).on("click", ".dz-filename>span", function(e) {
            e.stopPropagation();
        });
        $(".js-switch-green").each(function() {
            new Switchery(this, {
                color : '#2dcb73'
            });
        });
        //表单验证
        var name = $("input[name='name']");
        var biz_id = $("select[name='biz_id']");
        var introduce = $("textarea[name='introduce']");
        var price = $("input[name='price']");
        var validateFunc = function(dom){
            var validator = new Validator();
            validator.add(name,[
                {strategy:'isNotEmpty',errorMsg:'商品名不能为空!'},
            ]);
            validator.add(introduce,[
                {strategy:'isNotEmpty',errorMsg:'商品介绍不能为空!'},
            ]);
            validator.add(price,[
                {strategy:'isPrice',errorMsg:'价格必须是正整数,最多两位小数!'}
            ]);
            validator.add(biz_id,[
                {strategy:'isNotEmpty',errorMsg:'商品所属商家不能为空!'}
            ]);
            var error= validator.start(dom);
            return error;
        };

        $('input').blur(function(){
            validateFunc(this);
        })

        $("select[name='biz_id']").on('select2:close', (function(){
            validateFunc(this);
        }))

        $("textarea[name='introduce']").blur(function(){
            validateFunc(this);
        })

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
    <li><a href="${contextPath}/admin/product.html">商品</a></li>
    <li class="active">
        <#if model??>
            编辑商品
        <#else>
            添加商品
        </#if>
    </li>
</ol>
</#macro>

<@layout title=overrideTitle style=overrideStyle content=overrideContent script=overrideScript nav=overrideNav>
</@layout>