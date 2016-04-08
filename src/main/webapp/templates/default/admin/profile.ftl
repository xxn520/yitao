<#include "layout.ftl">

<#macro overrideTitle>
	账号设置 -
</#macro>

<#macro overrideStyle>
<link rel="stylesheet" href="${contextPath}/templates/default/admin/css/dropzone.min.css">
<style>
    .dropzone {
        border: 1px solid #E8ECF3;
        border-radius: 2px;
    }
</style>
</#macro>

<#macro overrideContent>
<div class="row mg-b">
    <div class="col-xs-12">
        <h3 class="no-margin">账号设置</h3>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <ul class="nav nav-tabs">
                <li class="active"><a href="javascript:void(0)">账号设置</a></li>
                <li><a href="${contextPath}/admin/reset.html">重设密码</a></li>
            </ul>
            <div class="panel-body">
                <form class="form-horizontal" action="profile.html" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="username" type="text" required="required"  placeholder="请输入用户名" value="${model.username!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">头像</label>
                        <div class="col-sm-10">
                            <div class="upload dropzone <#if (model.avatar)??>dz-started</#if>" id="upload">
                                <#if (model.avatar)??>
                                <div
                                        class="dz-preview dz-processing dz-success dz-complete dz-image-preview">
                                    <div class="dz-image">
                                        <img src="${model.avatar}">
                                    </div>
                                    <div class="dz-details">
                                        <div class="dz-filename">
                                            <span data-dz-name="">${model.avatar}</span>
                                        </div>
                                    </div>
                                    <a class="dz-remove" href="javascript:undefined;"
                                       data-dz-remove="">删除文件</a>
                                    <input type="hidden" name="avatar" value="${model.avatar}">
                                </div>
								</#if>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-success">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </div>
</div>
</#macro>

<#macro overrideScript>
<script type="text/javascript" src="${contextPath}/templates/default/admin/js/dropzone.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        var errorMessage = "${request.session.errorMessage!}";
        if (errorMessage != "") {
            toastr.error(errorMessage);
        }
        $("form").submit(function() {
            return true;
        });
        Dropzone.options.upload = {
            url: "${contextPath}/admin/upload.json",
            maxFilesize: 2,
            addRemoveLinks: true,
            createImageThumbnails: false,
            maxFiles: 1,
            acceptedFiles: "image/*",
            dictDefaultMessage: "拖放文件到此上传",
            dictRemoveFile: "删除文件",
            dictCancelUpload: "取消上传",
            dictMaxFilesExceeded: "只能有一个封面呀",
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
                    $(file.previewElement).append("<input type=\"hidden\" name=\"avatar\" value=\"" + data.url + "\">");
                }).on("addedfile", function(file) {
                    if ($(".dz-preview").length > 1) {
                        toastr.error('只能有一个封面呀。');
                        myDropzone.removeFile(file);
                    }
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
            $(".form-control[name=cover]").val(src);
        }).on("click", ".dz-filename>span", function(e) {
            e.stopPropagation();
        });
    });
</script>
</#macro>

<#macro overrideNav>
<ol class="breadcrumb no-margin">
    <li><a href="${contextPath}/admin/index.html">首页</a></li>
    <li class="active">账号设置</li>
</ol>
</#macro>

<@layout title=overrideTitle style=overrideStyle content=overrideContent script=overrideScript nav=overrideNav>
</@layout>