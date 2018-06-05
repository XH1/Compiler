$(function () {
    $(".navbar-nav").find("a").each(function () {
        if ($(this).attr("href") === location.pathname) {
            $(this).addClass("active")
        }
    });

    $("textarea").keydown(function (e) {
        if (e.keyCode === 9) {
            var pos = this.selectionStart + "\t".length;
            this.value = this.value.substr(0, this.selectionStart) + "\t"
                + this.value.substr(this.selectionStart);

            this.selectionStart = pos;
            this.selectionEnd = pos;
            this.focus();
            e.preventDefault();
        }
    });

    $("#btn_lexer_submit").click(function () {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/lexer",
            data: $("#lexer_form").serialize(),
            success: function (data) {
                if (!data[0].hasOwnProperty("type")) {
                    $("#modal-body").text(data);
                    $(".modal").modal('show');
                    return;
                }

                var html = "";
                for (var i = 0; i < data.length; i++) {
                    html += "<tr>";
                    for (var key in data[i]) {
                        html += "<td>" + data[i][key] + "</td>>";
                    }
                    html += "</tr>";
                }
                $("#tbody").html(html);
            }
        })
    })
});
