requirejs.config({
	urlArgs: "v=1.0.0",
	baseUrl: (window.CTX || '')+'/',
	paths: {
		base:"web/js/base",
		bs:"libs/bootstrap/js/bootstrap.min",
		compents:"web/js/compents",
		listchoose:"user/listchoose/ui.listchoose",
		uichoose:"user/choose/ui.choose",//选择组件 放在gzch-common中
		fileupload:"ueditor1_4_3/fileupload",//文件上传组件 放在gzch-common中
		datetimepicker:"libs/datetime/jquery.datetimepicker.full.min",//日期选择
		jquerytmpl:"js/jquery.tmpl.min",//jquery模板 
		underscore: 'libs/underscore/underscore.min',
		backbone: 'libs/backbone/backbone-min', 
		jquery: "libs/jquery/jquery-1.11.3",
		jqueryui: "libs/jqueryui/js/jquery-ui-1.10.3.custom.min",
		"jquery-mousewheel": "libs/jquery/jquery.mousewheel.min",
		angular: "libs/angular/angular.min",
		ztree:"libs/jquery-ztree/js/jquery.ztree.all-3.5.min",
		treetable:"libs/treeTable/jquery.treeTable.min",
		md5:"libs/md5/md5.min",
		dragsort:"libs/dragsort/jquery.dragsort-0.5.1.min",/**可拖拽组件**/  
		ionic: "libs/ionic/release/js/ionic.min", 
		validate: "libs/jquery-validation/1.11.1/messages_bs_zh",
		_validate_core:"libs/jquery-validation/1.11.1/jquery.validate.min",
		ztree:"libs/jquery-ztree/js/jquery.ztree.all-3.5.min",
		treetable:"libs/treeTable/jquery.treeTable.min",
		fastclick: "libs/fastclick/fastclick.min",
		imgareaselect: "libs/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.min",
		ajaxfileupload: "libs/ajaxfileupload/ajaxfileupload.min",
		alertify: "libs/alertify.js-0.3.11/lib/alertify.min",
		ZeroClipboard : "libs/zeroclipboard/ZeroClipboard.min",
		icheck:"libs/icheck/icheck.min",
		amazeui:"libs/amazeui/js/amazeui.min",
		vue:"libs/vue/vue",
		sortablejs:"libs/sortable/sortable.min",
		"vuedraggable":"libs/vuedraggable/vuedraggable.min", 
		lightbox:"libs/lightbox/js/lightbox.min",
		layer:"libs/layer/layer.min", 
		materialtags:"libs/materialtags/js/materialize-tags.min",
//		_ueditor_parse:"ueditor/ueditor.parse.min",
		ueditor:"web/ueditor/lang/zh-cn/zh-cn",
		_ueditor_config:"web/ueditor/ueditor.config", 
		_ueditor_all:"web/ueditor/ueditor.all", 
		list_scroll:"js/scroll" //列表滚动
	},
	map: {
	  '*': {
	    'css': 'libs/require-css/css.min' // or whatever the path to require-css is
	  }
	}, 
	shim: {
		'jquery': {
			//deps: ['libs/iefix'],
			exports: 'jQuery'
		},  
		'base': {
			deps: ['jquery' ,'compents'],
			exports: 'jQuery'
		},   
		'compents': {
			deps: ['jquery'],
			exports: 'jQuery'
		},  
		'ajaxfileupload': {
			deps: ['jquery']
		},  
		'validate': {
			deps: ['jquery','_validate_core','css!libs/jquery-validation/1.11.1/validate.css'],
			exports: 'jQuery'
		},
		'_validate_core': {
			deps: ['jquery']
		},
		'md5':{
			exports:'MD5'
		},
		'backbone': {
			deps: ['underscore', 'jquery'],
			exports: 'Backbone'
		},  
		'bs': {
			deps: ['jquery','base'],
			exports: 'jQuery'
		},
		'ztree':{
			deps:['css!libs/jquery-ztree/css/zTreeStyle/zTreeStyle','jquery'],
			exports: 'jQuery'
		},
		'dragsort':{
			deps:['jquery'],
			exports: 'jQuery'
		},
		'underscore': {
			exports: '_'
		},
		"treetable": {
			deps: ['css!libs/treeTable/themes/vsStyle/treeTable.min',"jquery"],
			exports: 'jQuery'
		},
		"jqueryui": {
			deps: ['css!libs/jqueryui/css/redmond/jquery-ui-1.10.3.custom.min',"jquery"],
			exports: 'jQuery'
		},
		"jquery_mousewheel": {
			deps: ["jquery"],
			exports: 'jQuery'
		}, 
		'angular': {
            exports: 'angular'
        },
		'ratchet': {
			deps: ['css!libs/ratchet/dist/ratchet','css!libs/ratchet/dist/ios-theme']
        },
		'fastclick': {
            exports: 'FastClick'
        },
        'uichoose':{
        	deps: ['css!user/choose/ui.choose','dragsort','ztree'],
        	exports: 'jQuery'
        },
        'listchoose':{
        	deps: ['css!user/listchoose/ui.listchoose','dragsort'],
			exports: 'jQuery'
        },
        'fileupload':{
        	deps: ['bs'],
        	exports: 'jQuery'
        },
        'datetimepicker':{
        	deps: ['css!libs/datetime/jquery.datetimepicker.min','jquery'],
        	exports: 'jQuery'
        },
        'jquerytmpl':{
        	deps: ['jquery'],
        	exports: 'jQuery'
        },
        'imgareaselect':{
        	deps: ['jquery',"css!libs/jquery.imgareaselect-0.9.10/css/imgareaselect-default"]
        },
        'alertify':{
        	deps: ['jquery',"css!libs/alertify.js-0.3.11/themes/alertify.core","css!libs/alertify.js-0.3.11/themes/alertify.default"],
			exports: 'alertify'
        },
        'ueditor':{
        	deps: ['_ueditor_all']
        },
        '_ueditor_all':{
        	deps: ['_ueditor_config']
        },
        'list_scroll':{
        	deps: ['jquery']
        },
        'icheck':{
        	deps: ['jquery','css!libs/icheck/skins/square/blue']
        },
        'amazeui':{
        	deps: ['css!libs/amazeui/css/amazeui']
        },
        'sortablejs':{
        	deps: ['underscore']
        },
        'vuedraggable':{
        	deps: ['vue','sortablejs']
        },
        'lightbox':{
        	deps: ['jquery','css!libs/lightbox/css/lightbox.min']
        } ,
        'layer':{
        	deps: ['jquery','css!libs/layer/skin/default/layer']
        } 
        ,
        'materialtags':{
        	deps: ['css!libs/materialtags/css/materialize-tags.min','jquery']
        } 
        
        
	}
});

