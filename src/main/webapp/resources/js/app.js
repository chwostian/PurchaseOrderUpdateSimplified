$(document).ready(function(){

    // Znajdźmy wszystkie, które mają atrybut contenteditable = true. Gdy zmieni się wartość oznaczymy to pole kolorem żółtym i zmienimy wartość
    // atrybuty data-hidden na false. Będziemy używać potem tego atrybutu, żeby chować wiersze, które nie zostały zmienione
    $("[contenteditable=true]").on("change", function(e) {
        e.stopPropagation();
        var vRow = $(this).closest("tr").eq(0);
        vRow.attr("data-hidden","false");
        $(this).css("background-color", "yellow");

    })

    $("#comparison").on("dblclick", function(){
            $("[data-hidden=true]").toggleClass("hide_or_show");
    })
    //Zdarzenia dla textarea. Chcemy, żeby rozszerzanie tej kontrolki odbywało się wg scenariusza
    $("textarea").on("keydown", function(e){
        e.stopPropagation();
        $(this).css("box-sizing","content-box");
        $(this).css("height", "auto");
        $(this).height($(this).prop("scrollHeight"));

    })
})