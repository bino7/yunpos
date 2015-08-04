var URL = $ctx + "/ajax/privilege";

$(document).ready(function() {
	$("#grid").jqGrid({
		url:URL, 
		editurl:URL, 
		datatype:"json", 
		jsonReader:{repeatitems:false, id:"id"}, // the unique ID of a row
		colModel:[  
	              {name: "id" ,index: "id" ,label:"ID",width:40,sortable:true,editable : true,hidden:true},
	              {
	            	  name:"privilegeMaster",index:"privilegeMaster",label:"权限主体类型",width:80,editable : true,
	            	  edittype : "select",
	            	  editoptions: {value: 'role:role'}
	              },
	              {
	            	  name:"privilegeMasterValue",index:"privilegeMasterValue",label:"权限主体值",width:80,editable : true,
	            	  edittype : "select",
	            	  editoptions: {value: getRoleSelectList()}
	              }, 
	              {
	            	  name:"privilegeAccess",index:"privilegeAccess",label:"资源类型",width:200,editable : true,
	            	  edittype : "select",
	            	  editoptions: {
	            		  value: 'app:app;button:button;menu:menu',
	            		  dataEvents : [{
								type : 'change', //下拉选择的时候
								fn : function (e) {
									var str = "";
									$.ajax({
										url : $ctx+"/ajax/"+this.value+"/select",
										async : false,
										cache : false,
										dataType : "json",
										success : function (json) {
											if (json != null) {
												var jsonobj = eval(json);
												var length = jsonobj.length;
												for (var i = 0; i < length; i++) { //循环option
													if (i != length - 1) {
			                                               str += "<option value=\"" + jsonobj[i].id + "\">" + jsonobj[i].name + "</option>;";
			                                           } else {
			                                               str += "<option value=\"" + jsonobj[i].id + "\">" + jsonobj[i].name + "</option>";
			                                           }
												}
											}
										}
									});
									var rolename = $("select#privilegeAccessValue"); //获取下面下拉框RoleName对象
									rolename.html("");
									rolename.append(str); // 然后绑定下拉框
								}
							}
						]
	            	   }
	              },
	              {
	            	  name:"privilegeAccessValue",index:"privilegeAccessValue",label:"资源",width:160,editable : true,
	            	  edittype : "select",
			          editoptions : {value: { '':'请选择'}}
	              }
	        ],  
		rowNum:5,
		rowList:[5,10,20],
		height:'auto',
		width: 960,
		shrinkToFit:true,
		sortname:"id",
		sortorder:"asc",
		pager:"#pager",
		viewrecords:true,
		loadError:loadErrorCallback, // error handler; add/edit/delete errors are processed by other methods
		loadComplete:loadCompleteCallback, // is called after loading all data to the grid
		autoencode:true, //when set to true encodes (HTML encode) the incoming (from server) and posted data. It prevents Cross-site scripting (XSS) attacks.
		ondblClickRow: function(id) {
			jQuery(this).jqGrid('editGridRow', id, jqGridEditOptions);
		}
	});

	jQuery('#grid').jqGrid('bindKeys');
	$("#grid").jqGrid("navGrid", "#pager", jqGridNavBarOptions, jqGridEditOptions, jqGridAddOptions, jqGridDelOptions, jqGridSearchOptions);

	function getRoleSelectList(type) {
		var str ="";
		$.ajax({
			url : $ctx+"/ajax/role/select",
			async : false,
			success : function (data) {
				if(data!=null){
					var jsonobj = eval(data);
					var length = jsonobj.length;
					for(var i = 0; i < length; i++) {
						if(i != length - 1) {
							str += jsonobj[i].id + ":" + jsonobj[i].roleName + ";";
						}else{
							str += jsonobj[i].id + ":" + jsonobj[i].roleName;
						}
					}
				}
			}
		});
		return str;
	}
	
	
	//获取下拉列表
	function getResSelectList(type) {
		var str ="";
		$.ajax({
			url : $ctx+"/ajax/role/select",
			async : false,
			success : function (data) {
				if(data!=null){
					var jsonobj = eval(data);
					var length = jsonobj.length;
					for(var i = 0; i < length; i++) {
						if(i != length - 1) {
							str += jsonobj[i].id + ":" + jsonobj[i].roleName + ";";
						}else{
							str += jsonobj[i].id + ":" + jsonobj[i].roleName;
						}
					}
				}
			}
		});
		return str;
	};

});



//二级联动
//function select(e) {
//	alert("xxx");
//	var str = "";
//	$.ajax({
//		url : 'Mail.ashx',
//		async : false,
//		cache : false,
//		dataType : "json",
//		data : {
//			actiontype : this.value //传入值，到后台获取json
//		},
//		success : function (json) {
//			if (json != null) {
//				var jsonobj = eval(json);
//
//				var length = jsonobj.length;
//				for (var i = 0; i < length; i++) { //循环option
//					if (i != length - 1) {
//						str += "<option>" + jsonobj[i].text + "</option>;";
//					} else {
//						str += "<option>" + jsonobj[i].text + "</option>";
//					}
//				}
//			}
//		}
//	});
//	var rolename = $("select#RoleName"); //获取下面下拉框RoleName对象
//
//	rolename.append(str); // 然后绑定下拉框
//}
