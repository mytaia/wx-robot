//给jQuery ajax添加默认的error function
;
(function($) {
	var ajax = $.ajax;
	$.ajax = function(s) {
		var old = s.error;
		s.error = function(xhr, status, err) {
			var text = xhr.responseText;
			if (text && text.indexOf("sessionTimeout") != -1) {
				EIM.alertMsg("会话过期，请重新登录", function() {
					top.location.reload();
				});
			} else if (text && text.indexOf("kickoutSessionTimeout") != -1) {
				EIM.alertMsg("当前用户已经在别的地方登录，您被迫登出", function() {
					top.location.reload();
				});
			} else if (text && text.indexOf("error500") != -1) {
				EIM.alertMsg("500:系统发生内部错误", null);
			}
			if (typeof old === 'function') {
				old(xhr, status, err);
			}
		}
		return ajax(s);
	}
})(jQuery);
// 给jQuery load添加默认的error function
;
(function($) {
	$.fn.extload = $.fn.load;
	$.fn.load = function(url, data, callback) {
		var old = callback;
		if (!callback) {// 如果只有两个参数
			old = data;
			callback = function(rspText, status, xhr) {
				var text = xhr.responseText;
				if (text && text.indexOf("sessionTimeout") != -1) {
					EIM.alertMsg("会话过期，请重新登录", function() {
						top.location.reload();
					});
				} else if (text && text.indexOf("kickoutSessionTimeout") != -1) {
					EIM.alertMsg("当前用户已经在别的地方登录，您被迫登出", function() {
						top.location.reload();
					});
				} else if (text && text.indexOf("error500") != -1) {
					EIM.alertMsg("500:系统发生内部错误", null);
				}
				if (typeof old === 'function') {
					old(rspText, status, xhr);
				}
			}
			if (typeof data === 'function') {// 如果不存在data参数
				this.extload(url, callback);
			} else {
				this.extload(url, data, callback);
			}
		} else {
			old = callback;
			callback = function(rspText, status, xhr) {
				var text = xhr.responseText;
				if (text && text.indexOf("sessionTimeout") != -1) {
					EIM.alertMsg("会话过期，请重新登录", function() {
						top.location.reload();
					});
				} else if (text && text.indexOf("kickoutSessionTimeout") != -1) {
					EIM.alertMsg("当前用户已经在别的地方登录，您被迫登出", function() {
						top.location.reload();
					});
				} else if (text && text.indexOf("error500") != -1) {
					EIM.alertMsg("500:系统发生内部错误", null);
				}
				if (typeof old === 'function') {
					old(rspText, status, xhr);
				}
			}
			this.extload(url, data, callback);
		}
	}
})(jQuery);
// 定义一些常用的js
var EIM = {
	getCookie : function(sName) {
		var aCookie = document.cookie.split("; ");
		for (var i = 0; i < aCookie.length; i++) {
			var aCrumb = aCookie[i].split("=");
			if (sName == aCrumb[0])
				return unescape(aCrumb[1]);
		}
		return "";
	},
	htmlEsc : function(sHtml) {
		if (sHtml) {
			return sHtml.replace(/[<>&"]/g, function(c) {
				return {
					'<' : '&lt;',
					'>' : '&gt;',
					'&' : '&amp;',
					'"' : '&quot;'
				}[c];
			});
		} else {
			return "";
		}
	},
	alertMsg : function(msg, callBack) {
		var $body = jQuery(document.body);
		var alertModal = $body.find("div.alert_modal_dialog");
		if (!alertModal.length) {
			$body
					.append('<div class="modal fade alert_modal_dialog" role="dialog"><div class="modal-dialog modal-sm"><div class="modal-content"><div class="modal-header"><button type="button" class="close btn-sure"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button><h4 class="modal-title" id="uo-choose-label">信息提示框</h4></div><div class="modal-body"></div></div></div></div>');
			alertModal = $body.find("div.alert_modal_dialog");
			alertModal.find("button.btn-sure").click(function() {
				var callback = alertModal.data("callBack");
				if (null != callback) {
					callback();
				}
				alertModal.modal('hide');
			});
		}
		alertModal.find("div.modal-body").html(msg);
		alertModal.data("callBack", callBack);
		alertModal.modal({
			backdrop : 'static'
		});
	}
};

;+function(w,d) {
	if(!String.prototype.trim){
		// 添加js的trim
		String.prototype.trim = function() {
			return this.replace(/(^\s*)|(\s*$)/g, "");
		}
	}
}(window,document);
 