$(document).ready(function(){

    $("[data-hidden=false]").removeClass("hide_or_show");
    $("[data-hidden=true]").addClass("hide_or_show");
    $("#upload").on('click', function(e) {
        e.stopPropagation();
        $("#fileToUpload").trigger('click');
    })

    $("#fileToUpload").on('click', function(e) {
        e.stopPropagation();
    })

    $(".submit").on('click', function(e) {
        e.stopPropagation();
        $("#uploader").submit();
    })

})