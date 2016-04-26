<#macro defaultStyle>
</#macro>

<#macro defaultContent>
</#macro>

<#macro defaultScript>
</#macro>

<#macro defaultTitle>
</#macro>

<#macro layout title=defaultTitle style=defaultStyle content=defaultContent script=defaultFooter>
    <#assign contextPath="http://localhost:8080" />
    <!DOCTYPE html>
    <html lang="zh-cn">
    <head>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
        <meta charset="UTF-8">
        <title><@title />${(app.name)!}</title>

        <link rel="stylesheet" href="${contextPath}/templates/default/css/bootstrap.min.css">
        <link rel="stylesheet" href="${contextPath}/templates/default/css/modus-style.css">
        <@style />
    </head>
    <body>
    <div class="header">
        <div class="container clearfix">
            <div class="logo">
                <p>${(app.name)!}</p>
            </div>
            <div class="nav">
                <a href="javascript:;" class="hidden-sm-up">
                    &#9776;
                </a>
            </div>
            <div class="nav">
                <ul class="visible-hidden">
                </ul>
            </div>
        </div>
    </div>
    <@content />
    <footer>
        <div class="container clearfix">
            <div class="about-us">
                <div class="logo">
                    <p>${(app.name)!}</p>
                </div>
                <p class="about-us-text">${(app.metaDescription)!}</p>
                <p class="about-us-phone">电话: <span>${(app.phone)!}</span></p>
                <p class="about-us-e-mail">E-mail: <span>${(app.email)!}</span></p>
            </div>
        </div>
    </footer>
    <div class="footer-bar">
        <div class="container clearfix">
            <p>${(app.copyright)!}</p>

            <div class="footer-bar-link">
                <a href="javascript:;"><img src="${contextPath}/templates/default/img/fb.png" alt=""></a>
                <a href="javascript:;"><img src="${contextPath}/templates/default/img/g+.png" alt=""></a>
                <a href="javascript:;"><img src="${contextPath}/templates/default/img/tw.png" alt=""></a>
                <a href="javascript:;"><img src="${contextPath}/templates/default/img/rss.png" alt=""></a>
            </div>
        </div>
    </div>
    </body>
    </html>
    <script src="${contextPath}/templates/default/js/jquery.min.js"></script>
    <script src="${contextPath}/templates/default/js/portfolio.js"></script>
    <script src="${contextPath}/templates/default/js/bootstrap.min.js"></script>
    <#if model??>
    <script type="text/javascript"
            src="${contextPath}/templates/default/js/jquery.twbsPagination.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.portfolio-page').twbsPagination({
                totalPages: ${(model.totalPages)!},
                startPage: ${(model.number)!} + 1,
                initiateStartPageClick: false,
                first: "首页",
                prev: "上一页",
                next: "下一页",
                last: "末页",
                onPageClick : function(event, page) {
                    var href = location.search;
                    if(href!=""){
                        href = href.indexOf("page=") > 0 ? href.replace(/page=[\d]+/, "page=" + (page - 1)) : href + "&page=" + (page - 1);
                    }else{
                        href = location.href + "?page=" + (page - 1);
                    }
                    location.href = href;
                }
            });
        });
    </script>
    </#if>
    <@script />
</#macro>