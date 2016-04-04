/**
 * Created by yunpeng on 16/1/5.
 */
$(document).ready(function () {
    $('.nav-down').hover(function () {
        $('.nav').eq(1).toggleClass('hover')
        $(this).children('a').toggleClass('onselected');
        $(this).children('.drag-down').fadeToggle();
    });
    var rlchild_length = $('.article-related').children().length;
    $(window).resize(function () {
        if ($(window).width() > 543) {
            $(".nav ul").removeAttr('style').removeClass('nav-show');
        }
        else {
            $(".nav ul.nav-show").width($('.container').width());
        }
        $('.article-content').height($('.article-content').prev().height());
        $('.portfolio-related-header img').parent().width($('.container').width() - $('.portfolio-related-header h2').width() - $('.portfolio-related-header .btn-group').width());
        if ($(window).width() > 767) {
            if ((rlchild_length >= 4) && (rlchild_length - index) <= 4) {
                index = rlchild_length - 4;
                $('#article-btn-right').addClass('disabled');
            }else{
                if(rlchild_length<4) {
                    index = 0;
                    $('#article-btn-right').addClass('disabled');
                }
            }
            if(index==0){
                $('#article-btn-left').addClass('disabled');
            }
            $('.article-related').find('.project-card').width($('.article-related').parent().width() / 4 - 30);
            $('.article-related').width(($('.project-card').width() + 30) * (rlchild_length + 1));
            $('.article-related').height($('.project-card').height());
            $('.portfolio-related-projects').height($('.project-card').height());
            $('.article-related').css('left', -index * ($('.project-card').width() + 30));
            $('.article-related').css('top', 0);
        }
        else {
            if ((rlchild_length - index) <= 2) {
                $('#article-btn-right').addClass('disabled');
            } else {
                $('#article-btn-right').removeClass('disabled');
            }
            $('.article-related').find('.project-card').width($('.article-related').parent().width() - 30);
            // $('.article-related').height(($('.project-card').outerHeight(true)) * (rlchild_length + 1));
            if(rlchild_length<2){
                $('.portfolio-related-projects').height($('.project-card').outerHeight(true));
            }else{
                $('.portfolio-related-projects').height($('.project-card').outerHeight(true) * 2);
            }
            $('.article-related').css('top', -index * ($('.project-card').outerHeight(true)));
            $('.article-related').css('left', 0);
        }
    });

    if ($(window).width() > 767) {
        $('.article-related').find('.project-card').width($('.article-related').parent().width() / 4 - 30);
        $('.article-related').width(($('.project-card').width() + 30) * (rlchild_length + 1));
        $('.article-related').height($('.project-card').height());
        if (rlchild_length <= 4) {
            $('#article-btn-right').addClass('disabled');
        }
    }
    else {
        $('.article-related').find('.project-card').width($('.article-related').parent().width() - 30);
        // $('.article-related').height(($('.project-card').outerHeight(true)) * (rlchild_length + 1));
        if(rlchild_length<2){
            $('.portfolio-related-projects').height($('.project-card').outerHeight(true));
        }else{
            $('.portfolio-related-projects').height($('.project-card').outerHeight(true) * 2);
        }
        $('.article-related').css('top', -index * ($('.project-card').outerHeight(true)));
        if (rlchild_length <= 2) {
            $('#article-btn-right').addClass('disabled');
        }
    }


    $('.article-content').height($('.article-content').prev().height());
    $('.portfolio-related-header img').parent().width($('.container').width() - $('.portfolio-related-header h2').width() - $('.portfolio-related-header .btn-group').width());
    $('.hidden-sm-up').click(function () {
        $('.nav ul').slideToggle("nav-show");
        $(".nav ul").width($('.container').width());
    })

    var index = 0;
    $('#article-btn-left').click(function () {
        // var wid = -($('.project-card').width() + 30) * (rlchild_length - 4);
        if (index > 0) {
            $('#article-btn-right.disabled').removeClass('disabled');
            $('.article-related').css('position', 'relative');
            if ($(window).width() > 767) {
                $('.article-related').animate({
                    left: '+=' + ($('.project-card').width() + 30) + 'px'
                }, 800);
            }
            else {
                $('.article-related').animate({
                    top: '+=' + ($('.project-card').outerHeight(true)) + 'px'
                }, 800);
            }
            index--;
            if (index == 0) {
                $('#article-btn-left').addClass('disabled');
            }
        }
    })
    $('#article-btn-right').click(function () {
        $('.article-related').css('position', 'relative');
        if ($(window).width() > 767) {
            if (index < (rlchild_length - 4)) {
                $('#article-btn-left.disabled').removeClass('disabled');
                $('.article-related').animate({
                    left: '-=' + ($('.project-card').width() + 30) + 'px'
                }, 800);
                index++;
                if (index == (rlchild_length - 4)) {
                    $('#article-btn-right').addClass('disabled');
                }
            }
        }
        else {
            if (index < (rlchild_length - 2)) {
                $('#article-btn-left.disabled').removeClass('disabled');
                $('.article-related').animate({
                    top: '-=' + ($('.project-card').outerHeight(true)) + 'px'
                }, 800);
                index++;
                if (index == (rlchild_length - 2)) {
                    $('#article-btn-right').addClass('disabled');
                }
            }
        }
    })

})