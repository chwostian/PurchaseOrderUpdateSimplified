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

    $("#lipnyLink").on("click", function(e) {
        e.stopPropagation();
        var dataContainer = {
            data: []
        };

        dataContainer.data.push({
            name: 'Joe',
            age: 33
        });

        dataContainer.data.push({
            name: 'Mary',
            age: 18
        });

        $.ajax({
            method: "POST",
            url: "update",
            contentType: "application/json",
            data: JSON.stringify(dataContainer)
        })
            .done(function( msg ) {
                alert( "Data Saved: " + msg );
            });
    })
})