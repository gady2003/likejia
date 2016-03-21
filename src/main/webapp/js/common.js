/**
 * Created by pan.wang on 2015-06-19.
 */
function showPop() {
    var t = $(document).height();

    if ($('#pop-share').length <= 0) {
        $('body').append(' <div class="mod-pop" id="pop-share" onclick="hidePop(\'#pop-share\')"> <span class="text-share"></span> </div>');
    };
    var $obj=$('#pop-share');
    $($obj).height(t).fadeIn();
}
function hidePop(e) {
    $(e).fadeOut()
}

$.fn.tips=function(content){
    var tips=$(this);
    $(tips).append('<div class="ui-poptips ui-poptips-success am-animation-fade" > <div class="ui-poptips-cnt"><i></i>'+content+'</div> </div>');
    setTimeout(function(){$("div.ui-poptips").remove();},3000);
}

$(".icon_251").on("click",function(){
    $('html, body').animate({scrollTop: 0}, '500');
})