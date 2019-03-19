<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {

		$("#dg").datagrid({
			fitColumns : true,
			toolbar : [ {
				text : "新增",
				iconCls : 'icon-add',
				handler : function() {
					add();
				}
			} ],
			pagination : true,
			pageSize : 30
		});
	});
	//打开弹出框
	function openFormDialog() {
		$("#dd").dialog({
			buttons : [ {
				text : '保存',
				iconCls : "icon-save",
				handler : function() {
					save();
				}
			}, {
				text : '关闭',
				iconCls : "icon-cancel",
				handler : function() {
					$("#dd").dialog("close");
				}
			} ]
		});
		//打开对话框
		$("#dd").dialog("open");
	}
	//新增记录
	function add() {
		//重置表单内容
		$("#ff").form("reset");
		//新增记录的请求地址
		url = "User/add";
		//打开弹出框
		openFormDialog();
		//设置弹出框标题
		$("#dd").dialog("setTitle", "新增用户信息");
	}
	//保存或者更新数据
	function save() {
		$("#ff").form("submit", {
			url : url,
			onSubmit : function() {
				//调用validate方法校验表单中所有的字段有效性，只有所有的字段有效才返回true
				return $(this).form("validate");
			},
			success : function(result) {
				//接收服务器返回的json格式字符串数据转换成json对象
				var data = eval('(' + result + ')');
				if (data.state == 0) {
					$.messager.alert("提示消息", data.msg, "info");
					//刷新列表
					$("#dg").datagrid("reload");
				} else {
					$.messager.alert("提示消息", data.msg, "error");
				}
				//关闭弹出框
				$("#dd").dialog("close");
			}
		});
	}
</script>
</head>
<body>
	<div id="dd" class="easyui-dialog"
		style="width: 400px; text-align: center; padding: 10px;" closed="true">
		<form id="ff" method="post">
			<table style="margin: 0 auto;">
				<tr>
					<td class="right">姓名：</td>
					<td><input type="text" name="name" class="easyui-textbox"
						data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">内容：</td>
					<td><input type="text" name="content" class="easyui-textbox"
						data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">类型：</td>
					<td><select name="type" data-options="editable:false"
						class="easyu-comboxx">
							<option value="-1">请选择</option>
							<option value="图片">图片</option>
							<option value="文字">文字</option>
							<option value="图文混合">图文混合</option>
					</select></td>
				</tr>
				<tr>
					<td class="right">备注：</td>
					<td><input type="text" name="remark" class="easyui-textbox"
						data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">创建人id：</td>
					<td><input type="text" name="user_id" class="easyui-numberbox"
						data-options="required:true"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>