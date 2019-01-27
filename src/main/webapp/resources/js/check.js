$(document).ready(function(){

    // Znajdźmy wszystkie, które mają atrybut contenteditable = true. Gdy zmieni się wartość oznaczymy to pole kolorem żółtym i zmienimy wartość
    // atrybuty data-hidden na false. Będziemy używać potem tego atrybutu, żeby chować wiersze, które nie zostały zmienione
    $("[contenteditable=true]").on("change", function(e) {
        e.stopPropagation();
        var vRow = $(this).closest("tr").eq(0);
        vRow.attr("data-hidden","false");
        $(this).css("background-color", "yellow");

    })
    //początek update
    $("#update").on("click", function(e) {
        e.stopPropagation();
        if ($("[data-has_been_updated='true']").length === 0) {
            var vRows = $("[data-hidden=false]");
            if (vRows.length > 0) {

                var purchaseOrdersFakeDTO = {purchaseOrdersFakeList: []}
                vRows.each(function (index, element) {
                    var purchaseOrdersFake = {
                        numer_zamowienia: $(this).data("numer_zamowienia").toString(),
                        numer_pozycji: $(this).data("numer_pozycji").toString(),
                        indeks_czesci: $(this).data("indeks_czesci").toString(),
                        indeks: $(this).data("indeks").toString(),
                        nazwa_czesci: $(this).data("nazwa_pelna").toString(),
                        kl_termin: $(this).find("[contenteditable=true]").eq(0).val().toString(),
                        pr_termin: $(this).find("[contenteditable=true]").eq(1).val().toString(),
                        ilosc_zlecona: $(this).find("[contenteditable=true]").eq(2).val().toString(),
                        ilosc_do_przyjecia: $(this).data("n_ilosc_do_przyjecia").toString(),
                        uwagi: $(this).find("[contenteditable=true]").eq(3).val().toString(),
                        numer_kontrahenta: $(this).data("numer_kontrahenta").toString()
                    }
                    purchaseOrdersFakeDTO.purchaseOrdersFakeList.push(purchaseOrdersFake);

                })


                $.ajax({
                    method: "POST",
                    url: "update",
                    contentType: "application/json",
                    data: JSON.stringify(purchaseOrdersFakeDTO)
                })
                    .done(function (msg) {
                        var selector;
                        var dblQuote = String.fromCharCode(34);
                        var sngQuote = String.fromCharCode(39);
                        alert("Data Saved: " + msg);
                        console.log(msg[0]);
                        if (msg.returnPurchaseOrdersFakeList.length > 0) {
                            msg.returnPurchaseOrdersFakeList.forEach(function (element, index) {
                                selector = "[data-numer_zamowienia=" + sngQuote + element.purchaseOrders.numer_zamowienia + sngQuote + "]"
                                selector += "[data-numer_pozycji=" + sngQuote + element.purchaseOrders.numer_pozycji + sngQuote + "]";
                                alert(selector);
                                var newElement = element.updated > 0 ? '<td class="center"><i class=\"glyphicon glyphicon-ok\"/></td>' : '<td class="center"><i class="glyphicon glyphicon-remove"/></td>';
                                $(selector).append(newElement);
                                $(selector).attr("data-has_been_updated", "true");
                            })
                        }
                        // window.location.reload(true);

                    });
            } else {
                alert("Nic się nie zmieniło. Nie ma pozycji do aktualizacji");
            }
        } else {
                alert("Na liście są pozycje, które próbowano zaktualizować. Ponowne wysłanie zapytania do bazy nie jest możliwe. Przeładuję stronę");
                window.location.reload(true);
        }
    // koniec update
    })

    $("#comparison").on("dblclick", function(){
            $("[data-hidden=true]").toggleClass("hide_or_show");
    })

    $(".remove").on("click", function(e) {
        e.stopPropagation();
        $(this).closest("tr").fadeOut("slow");
    })
    //Zdarzenia dla textarea. Chcemy, żeby rozszerzanie tej kontrolki odbywało się wg scenariusza
    $("textarea").on("keydown", function(e){
        e.stopPropagation();
        $(this).css("box-sizing","content-box");
        $(this).css("height", "auto");
        $(this).height($(this).prop("scrollHeight"));

    })

    //Export do excel

    $("#export_to_excel").click("on", function(){

        vRows = $('tr');

        if (vRows.length > 0) {
            var purchaseOrdersFakeDTO = {purchaseOrdersFakeList: []}
            vRows.each(function (index, element) {
                var purchaseOrdersFake = {
                    numer_zamowienia: $(this).data("numer_zamowienia").toString(),
                    numer_pozycji: $(this).data("numer_pozycji").toString(),
                    indeks_czesci: $(this).data("indeks_czesci").toString(),
                    indeks: $(this).data("indeks").toString(),
                    nazwa_czesci: $(this).data("nazwa_pelna").toString(),
                    kl_termin: $(this).find("[contenteditable=true]").eq(0).val().toString(),
                    pr_termin: $(this).find("[contenteditable=true]").eq(1).val().toString(),
                    ilosc_zlecona: $(this).find("[contenteditable=true]").eq(2).val().toString(),
                    ilosc_do_przyjecia: $(this).data("n_ilosc_do_przyjecia").toString(),
                    uwagi: $(this).find("[contenteditable=true]").eq(3).val().toString(),
                    numer_kontrahenta: $(this).data("numer_kontrahenta").toString(),
                    termin_dostawcy: $(this).data("termin_dostawcy").toString(),
                    ilosc_do_przyjecia_wg_dostawcy: $(this).data("n__ilosc_do_przyjecia_wg_dostawcy").toString()
                }
                purchaseOrdersFakeDTO.purchaseOrdersFakeList.push(purchaseOrdersFake);

            })
            alert("oj bedziemy sobie eksportowac do woli");

        }

    })
})