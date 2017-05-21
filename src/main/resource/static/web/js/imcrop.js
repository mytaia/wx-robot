/*
示例
$("#file").on("change",function(e){
    var selectedFile = e.target.files[0];
    imcrop(selectedFile,"","测试用例",500,300,function(json){
    json //json格式回调 里面一般含有 上传成功的 url
    })；
  /** imcrop 参数注释
 *file 文件
 * url 提交的地址
 *  titlte 标题
 * cropw 裁剪的宽度
 * croph 裁剪的高度
 * callback 上传成功回调
 imcrop(file, url, titlte, cropw, croph, callback);
})*/

(function ($) {
    /*弹出框*/
    var $css3Transform = function (element, attribute, value) {
        var arrPriex = ["O", "Ms", "Moz", "Webkit", ""], length = arrPriex.length;
        for (var i = 0; i < length; i += 1) {
            element.css(arrPriex[i] + attribute, value);
        }
    }
    $(function () {
        $(window).on("resize", function () {
            var pops = $(".imcrop-container");
            if (pops.length > 0) {
                pops.each(function () {
                    var pop = $(this);
                    var divpopbox = $("body");
                    var x = ($(window).width() - pop.width()) / 2;
                    var y = ($(window).height() - pop.height()) / 2;
                    var scaleX = divpopbox.width() / pop.width();
                    var scaleY = divpopbox.height() / pop.height();
                    pop.css("left", x + "px");
                    pop.css("top", y + "px");
                });
            }
        });
    });
    var imcrop = {};
    imcrop.stack = [];
    imcrop.zIndex = 12;
    imcrop.stackPop = [];
    imcrop.showPop = function (element, callback, iClose) {
        var divgame = $("body");
        if (imcrop.stack.length == 0) {
            divgame.append("<div id='imcrop-bg' class='imcrop-bg'></div>");
            imcrop.zIndex = 12;
            $("#imcrop-bg").css("zIndex", imcrop.zIndex);
        } else {
            imcrop.zIndex = imcrop.stack[imcrop.stack.length - 1] + 1;
            $("#imcrop-bg").css("zIndex", imcrop.zIndex);
        }
        var pop = $("<div class='imcrop-container'></div>");
        divgame.append(pop);
        var zPop = imcrop.zIndex + 1;
        pop.css("zIndex", zPop);
        imcrop.stack.push(zPop);
        imcrop.stackPop.push(pop);
        element = $(element);
        pop.append(element);
        var move = element.find(".title").css("cursor", "move");
        move.bind("mousedown", function () {
            var x0 = window.event.pageX;
            var y0 = window.event.pageY;
            var pop = $(this).parents(".imcrop-container");
            var ex0 = pop.css("left").replace("px", "") * 1;
            var ey0 = pop.css("top").replace("px", "") * 1;
            move.bind("mousemove", function () {
                var x1 = window.event.pageX;
                var y1 = window.event.pageY;
                var mx = x1 - x0;
                var my = y1 - y0;
                var ex1 = ex0 + mx;
                var ey1 = ey0 + my;
                pop.css("left", ex1 + "px");
                pop.css("top", ey1 + "px");

            });
        });
        move.bind("mouseup", function () {
            move.unbind("mousemove");
        });
        move.bind("mouseleave", function () {
            move.unbind("mousemove");
        });
        if (typeof callback == "function") callback(element[0]);
        var x = ($(window).width() - pop.width()) / 2;
        var y = ($(window).height() - pop.height()) / 2;
        pop.css("left", x + "px");
        pop.css("top", y + "px");
        pop.removeClass("imcrop-container-animate");
        setTimeout(function () {
            pop.addClass("imcrop-container-animate");
            $css3Transform(pop, "Transform", "scale(1, 1)");
            $css3Transform(pop, "opacity", "1");
            setTimeout(function () {
                pop.removeClass("imcrop-container-animate");
                if (!((typeof iClose != "undefined") && iClose === false)) {
                    pop.addClass("iClose");
                }
                $("#imcrop-bg").unbind("click").bind("click", function () {
                    var element = imcrop.stackPop[imcrop.stackPop.length - 1];
                    if (element.hasClass("iClose")) {
                        imcrop.closePop();
                    }
                })

            }, 300);
        }, 30);
    }
    imcrop.closePop = function () {
        var pop = imcrop.stackPop.pop();
        var zPop = imcrop.stack.pop();
        if (imcrop.stack.length == 0) {
            $("#imcrop-bg").remove();
            imcrop.zIndex = 12;
        } else {
            imcrop.zIndex = imcrop.stack[active.stack.length - 1] - 1;
            $("#imcrop-bg").css("zIndex", active.zIndex);
        }
        pop.addClass("imcrop-container-animate");
        $css3Transform(pop, "opacity", "0");
        $css3Transform(pop, "Transform", "scale(0, 0)");
        setTimeout(function () {
            pop.remove();
        }, 300);
    }
    window.imcrop = function (file, url, titlte, cropw, croph, callback, cancel) {

        var html = "";
        html += "<div class='imcrop'>";
        html += "<div class='title'>" + titlte + "</div>";
        html += "<div class='advice'>建议：选择比例为4:3尺寸的图片上传</div>";
        html += "<div class='middle'>";
        html += "<div class='imgbig0'>";
        html += "<img ondragstart='return false;' draggable='false' class='img1'>";
        html += "<div class='imgbig0-bg'></div>";
        html += "<div ondragstart='return false;' draggable='false' class='img2'>";
        html += "<img ondragstart='return false;' draggable='false'>";
        html += "<div class='rightup'></div>";
        html += "</div>";
        html += "</div>";
        html += "<div class='imgcrop-bg'>";
        html += "<div ondragstart='return false;' draggable='false' class='img3'>";
        html += "<img ondragstart='return false;' draggable='false'>";
        html += "</div>";
        html += "</div>";
        html += "</div>";
        html += "<div class='btns'>";
        html += "<div class='space'></div>";
        html += "<div class='btn cancel'>取消</div>";
        html += "<div class='btn ok'>保存</div>";
        html += "</div>";
        html += "<div>";
        imcrop.showPop($(html), function (element) {
            var reader = new FileReader();
            reader.onload = putImage2Canvas;
            reader.readAsDataURL(file);
            function putImage2Canvas(evnt) {
                var img = new Image();
                var src = evnt.target.result;
                img.src = src;
                img.onload = function () {
                    var scale = 1;
                    var scale0 = 1;
                    var w = img.width;
                    var h = img.height;
                    if ((w / h) > 1) {
                        if (w > 278) {
                            scale = 278 / w;
                        }
                    } else {
                        if (h > 278) {
                            scale = 278 / h;
                        }
                    }
                    w = w * scale;
                    h = h * scale;
                    var x = (278 - w) / 2;
                    var y = (278 - h) / 2;
                    var _scropw = cropw;
                    var _scroph = croph;
                    if ((_scropw / _scroph) > (w / h)) {
                        _scropw = w;
                        _scroph = (w / cropw) * croph;
                    } else {
                        _scroph = h;
                        _scropw = (h / croph) * cropw;
                    }
                    var k = _scropw;
                    var x2 = (278 - _scropw) / 2 - 1;
                    var y2 = (278 - _scroph) / 2 - 1;
                    var img1 = $(element).find(".img1");
                    var img2 = $(element).find(".img2");
                    var img3 = $(element).find(".img3");
                    img1.width(w).height(h).css("left", x + "px").css("top", y + "px").attr("src", src);
                    img2.width(_scropw).height(_scroph).css("left", x2 + "px").css("top", y2 + "px");
                    img2.find("img").width(w).height(h).css("left", (x - x2 - 1) + "px").css("top", (y - y2 - 1) + "px").attr("src", src);
                    img3.width(_scropw).height(_scroph).css("left", x2 + "px").css("top", y2 + "px");
                    img3.find("img").width(w).height(h).css("left", (x - x2 - 1) + "px").css("top", (y - y2 - 1) + "px").attr("src", src);
                    var _x2 = x2;
                    var _y2 = y2;
                    img2.bind("mousedown", function (event) {
                        var mx0 = event.pageX;
                        var my0 = event.pageY;
                        img2.bind("mousemove", function (e) {
                            var mx1 = e.pageX - mx0;
                            var my1 = e.pageY - my0;
                            if (mx1 > 0) {
                                if (mx1 + x2 + _scropw > x + w - 1) {
                                    _x2 = x + w - 1 - _scropw;

                                } else {
                                    _x2 = mx1 + x2;
                                }
                            } else {
                                if (mx1 + x2 < x - 1) {
                                    _x2 = x - 1;
                                } else {
                                    _x2 = mx1 + x2;
                                }
                            }
                            if (my1 > 0) {
                                if (my1 + y2 + _scroph > y + h - 1) {
                                    _y2 = y + h - 1 - _scroph;

                                } else {
                                    _y2 = my1 + y2;
                                }
                            } else {
                                if (my1 + y2 < y - 1) {
                                    _y2 = y - 1;
                                } else {
                                    _y2 = my1 + y2;
                                }
                            }
                            img2.css("left", _x2 + "px").css("top", _y2 + "px");
                            img2.find("img").css("left", (x - _x2 - 1) + "px").css("top", (y - _y2 - 1) + "px");
                            img3.find("img").css("left", (x - _x2 - 1) * scale0 + "px").css("top", (y - _y2 - 1) * scale0 + "px");
                        });
                    });
                    img2.bind("mouseleave", function () {
                        x2 = _x2;
                        y2 = _y2;
                        img2.unbind("mousemove");
                    });
                    img2.bind("mouseup", function () {
                        x2 = _x2;
                        y2 = _y2;
                        img2.unbind("mousemove");
                    });
                    var _scropw0 = _scropw, _scroph0 = _scroph;
                    var rightup = $(element).find(".rightup")
                    rightup.bind("mousedown", function (event) {
                        event.stopPropagation();
                        var mx0 = event.pageX;
                        $(document).bind("mousemove", function (e) {
                            var mx1 = e.pageX - mx0;
                            _scropw0 = mx1 + _scropw;
                            _scroph0 = (mx1 + _scropw) * (_scroph / _scropw);
                            if (_scropw0 + x2 > x + w - 1) {
                                _scropw0 = x + w - 1 - x2;
                                _scroph0 = _scropw0 * (_scroph / _scropw);
                            }
                            if (_scroph0 + y2 > y + h - 1) {
                                _scroph0 = y + h - 1 - y2;
                                _scropw0 = _scroph0 * (_scropw / _scroph);
                            }
                            if (_scropw0 < 50) {
                                _scropw0 = 50;
                                _scroph0 = _scropw0 * (_scroph / _scropw);
                            }
                            img2.width(_scropw0).height(_scroph0);
                            scale0 = k / _scropw0;
                            var imgtemp = img2.find("img");
                            img3.find("img").width(imgtemp.width() * scale0).height(imgtemp.height() * scale0);
                            var t = imgtemp.css("top").replace("px", "") * scale0;
                            var l = imgtemp.css("left").replace("px", "") * scale0;
                            img3.find("img").css("left", l + "px").css("top", t + "px");
                        });
                    });
                    $(document).bind("mouseup", function () {
                        _scropw = _scropw0;
                        _scroph = _scroph0;
                        $(document).unbind("mousemove");
                    });
                    $(element).find(".ok").bind("click", function () {
                        //scale
                        var x = -img2.find("img").css("left").replace("px", "") / scale;
                        var y = -img2.find("img").css("top").replace("px", "") / scale;
                        var w = img2.width() / scale;
                        var h = img2.height() / scale;
                        var canvas = $('<canvas width="' + cropw + '" height="' + croph + '"></canvas>')[0],
                            ctx = canvas.getContext('2d');

                        ctx.drawImage(img, x, y, w, h, 0, 0, cropw, croph);
                        var data = canvas.toDataURL().split(',')[1];
                        imcrop.closePop();
                        if (typeof callback == "function") {
                            callback(data);
                        }
//                      $.ajax({
//                          "url": url,
//                          "type": "post",
//                          "data": {"base64Code": data},
//                          timeout: 10000,
//                          dataType: "json",
//                          beforeSend: function () {
//                              $(element).append("<div class='post'></div>");
//                              $(element).append("<div class='ptext'>图片上传中..</div>")
//                          },
//                          success: function (u) {
//                          },
//                          error: function (xhr, status, err) {
//                              $(element).find(".ptext").html("图片上传失败");
//                              $(element).find(".ptext").css("color", "red")
//                              setTimeout(function () {
//                                  $(element).find(".ptext").remove();
//                                  $(element).find(".post").remove();
//                              }, 1500)
//                          }
//                      })
                    });
                    $(element).find(".cancel").bind("click", function () {
                        imcrop.closePop();
                        if (typeof cancel == "function") {
                            cancel();
                        }
//                      $("#file_upload").remove();
//                      $("#preview").remove();
//                      $("#add_pic_a").append("<input type='file' id='file_upload' style='display:none;' /><img id='preview' />");
                    });
                }
            }

        }, false)
    }
})(jQuery)
