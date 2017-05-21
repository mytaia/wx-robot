+function ($) {
  'use strict';

  // ALERT CLASS DEFINITION
  // ======================

  var role = 'input[role="dict"]';
  var url = window.CTX +"/api/dict/all";
  var defOpt={"title":"选择项","showButtons":false}; 
  var dicts = undefined;  
  var $dialog;
  var $instance;
  
  var DataDict   = function (el) {  
	    $(el).on('click',this.show);
	    this.param = $.extend({},defOpt, $(el).data("dict-param")) ;  
	    this.el=el;
  } 
 
  DataDict.prototype.show = function (e){ 
	  $instance = $(this).data('ctp.dataDict')
	  var param = $instance.param;
	  var $input = $($instance.el);
	  $input.val() && (param.value = $input.val()); 
	  
	  $dialog.find("#commonModalLabel").text(param.title).end() ;
	  $dialog.find(".modal-footer").css("display",param.showButtons ? "":"none")
	  var dictKey = param.key;
	  console.log(dictKey);+
	  $dialog.modal("show"); 
	  if(!dicts ){ 
		  $.getJSON(url, function(data){ 
			  dicts = data.data;  
			  $instance.showItems(dicts[dictKey]);
		  });
	  }
	  else{
		  $instance.showItems(dicts[dictKey]);
	  } 
  } 

  DataDict.prototype.showItems=function(items){   
	  var param = this.param; 
	  var nodes=$("<ul class='dict'>");
	  $.each(items,function(index,txt){  
		 nodes.append($("<li>")
				 .text(txt)
				 .addClass( param.value && txt == param.value ?  "selected" :"")); 
	  }) 
	  $dialog.find(".modal-body").empty().append( nodes );
  }
    

  var selected = function (){  
	  var txt=$(this).text();  
	  $dialog.modal("hide"); 
	  $($instance.el).val(txt); 
	  $instance.param.selected && $instance.param.selected(txt);
  }

  function Plugin(option) {
	$dialog=$("#commonModal");
	$dialog.on("click","ul.dict li",selected );
	return this.each(function () {
	  var $this = $(this)
	  var data  = $this.data('ctp.dataDict');
	
	  if (!data) $this.data('ctp.dataDict', (data = new DataDict(this)))
	  if (typeof option == 'string') data[option].call($this)
	})
  } 

  $.fn.dataDict             = Plugin
  $.fn.dataDict.Constructor = DataDict 
  
}(jQuery);