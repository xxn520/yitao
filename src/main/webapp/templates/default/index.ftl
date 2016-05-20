<#--<#include "layout.ftl">-->

<#--<#macro overrideTitle>-->
    <#--首页 --->
<#--</#macro>-->

<#--<#macro overrideContent>-->
<#--<div>content overriden</div>-->
<#--</#macro>-->

<#--<@layout title=overrideTitle style=defaultStyle content=overrideContent script=defaultScript>-->
<#--</@layout>-->
<#assign contextPath="http://m2mbob.cn:8080" />
<!doctype html>
<html lang="zn-CH">
<head>
    <meta charset="UTF-8">
    <title>Love</title>
    <!--引入开始-->
    <style type="text/css">
        @font-face {
            font-family: digit;
            src: url('digital-7_mono.ttf') format("truetype");
        }
    </style>
    <link href="${contextPath}/templates/default/css/default.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="${contextPath}/templates/default/js/jquery.min.js"></script>
    <script type="text/javascript" src="${contextPath}/templates/default/js/garden.js"></script>
    <script type="text/javascript" src="${contextPath}/templates/default/js/functions.js"></script>
    <!--引入结束-->
    <style>
        body{margin:0; padding:0;}
        ul#wimoban_nav{padding-left:50px; margin-bottom:10px; border-bottom:2px solid #ccc; overflow:hidden; _zoom:1;}
        ul#wimoban_nav li{float:left; display:inline; margin:10px;}
        ul#wimoban_nav li a{display:block; font-size:16px;}
        ul#wimoban_nav li a,#wimoban_p,#wimoban_p a{color:#000; font-family:"微软雅黑";}
        ul#wimoban_nav li a:hover,#wimoban_p a:hover{color:red;}
        #wimoban_p{text-align:center; font-size:14px; clear:both;}
    </style>
</head>
<body oncontextmenu='return false' ondragstart='return false'>
<!-- 代码开始 -->
<audio src="${contextPath}/templates/default/css/ILoveYou.mp3" autoplay="autoplay" loop="loop" ></audio>
<div id="mainDiv">
    <div id="content">
        <div id="code">
            <span class="comments">/**</span><br />
            <span class="space"></span><span class="comments">*2015—05-14,</span><br />
            <span class="space"></span><span class="comments">*2015—05-20</span><br />
            <span class="space"></span><span class="comments">*/</span><br />
            Boy name = <span class="keyword">Mr</span> ZHU<br />
            Girl name = <span class="keyword">Mrs</span> WANG<br />
            <span class="comments">// Fall in love river. </span><br />
            The boy love the girl;<br />
            <span class="comments">// They love each other.</span><br />
            The girl loved the boy;<br />
            <span class="comments">// AS time goes on.</span><br />
            The boy can not be separated the girl;<br />
            <span class="comments">// At the same time.</span><br />
            The girl can not be separated the boy;<br />
            <span class="comments">// Both wind and snow all over the sky.</span><br />
            <span class="comments">// Whether on foot or 5 kilometers.</span><br />
            <span class="keyword">The boy</span> very <span class="keyword">happy</span>;<br />
            <span class="keyword">The girl</span> is also very <span class="keyword">happy</span>;<br />
            <span class="comments">// Whether it is right now</span><br />
            <span class="comments">// Still in the distant future.</span><br />
            The boy has but one dream;<br />
            <span class="comments">// The boy wants the girl could well have been happy.</span><br />
            <br>
            <br>
            I want to say:<br />
            Baby, I love you forever;<br />
        </div>
        <div id="loveHeart">
            <canvas id="garden"></canvas>
            <div id="words">
                <div id="messages">
                    亲爱的，这是我们相爱在一起的时光。
                    <div id="elapseClock"></div>
                </div>
                <div id="loveu">
                    爱你直到永永远远。<br/>
                    <div class="signature">- 朱先生</div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var offsetX = $("#loveHeart").width() / 2;
    var offsetY = $("#loveHeart").height() / 2 - 55;
    var together = new Date();
    together.setFullYear(2015, 4, 14);
    together.setHours(08);
    together.setMinutes(0);
    together.setSeconds(0);
    together.setMilliseconds(0);

    if (!document.createElement('canvas').getContext) {
        var msg = document.createElement("div");
        msg.id = "errorMsg";
        msg.innerHTML = "Your browser doesn't support HTML5!<br/>Recommend use Chrome 14+/IE 9+/Firefox 7+/Safari 4+";
        document.body.appendChild(msg);
        $("#code").css("display", "none")
        $("#copyright").css("position", "absolute");
        $("#copyright").css("bottom", "10px");
        document.execCommand("stop");
    } else {
        setTimeout(function () {
            startHeartAnimation();
        }, 5000);

        timeElapse(together);
        setInterval(function () {
            timeElapse(together);
        }, 500);

        adjustCodePosition();
        $("#code").typewriter();
    }
</script>
</body>
</html>