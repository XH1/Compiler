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

    $("#btn_grammar_submit").click(function () {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/grammar",
            data: $("#grammar_form").serialize(),
            success: function (data) {
                if (!data.hasOwnProperty("data")) {
                    $("#modal-body").text(data);
                    $(".modal").modal('show');
                    return;
                }

                var setting = {
                    data: {
                        key: {
                            name: "data",
                            children: "child"
                        }
                    }
                };
                $.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
            }
        })
    })


    $("#btn_lexer_file").click(function () {
        $("#file_lexer").click();
    })

    $("#btn_grammar_file").click(function () {
        $("#file_grammar").click();
    })

    $("#file_lexer").change(function () {
        var reader = new FileReader();
        var path = this.value;
        reader.readAsText(this.files[0]);
        reader.onload = function (ev) {
            if (!/\.txt/.test(path)) {
                $("#modal-body").text("请选择文本文件！");
                $(".modal").modal('show');
            } else {
                $("#txt_lexer").text(this.result);
            }
        }
    })

    $("#file_grammar").change(function () {
        var reader = new FileReader();
        var path = this.value;
        reader.readAsText(this.files[0]);
        reader.onload = function (ev) {
            if (!/\.txt/.test(path)) {
                $("#modal-body").text("请选择文本文件！");
                $(".modal").modal('show');
            } else {
                $("#txt_grammar").text(this.result);
            }
        }
    })
});

