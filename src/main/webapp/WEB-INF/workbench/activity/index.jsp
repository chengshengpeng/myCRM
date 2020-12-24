<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="/crm/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="/crm/jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="/crm/jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="/crm/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/crm/jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="/crm/jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
    <%--导入分页插件--%>
    <link href="/crm/jquery/bs_pagination/jquery.bs_pagination.min.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/crm/jquery/bs_pagination/en.js"></script>
    <script type="text/javascript" src="/crm/jquery/bs_pagination/jquery.bs_pagination.min.js"></script>


</head>
<body>

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form" action="/crm/workbench/activity/createActivity" id="createActivityForm">
					
						<div class="form-group">
							<label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-marketActivityOwner" name="owner">
								 <%-- <option>zhangsan</option>
								  <option>lisi</option>
								  <option>wangwu</option>--%>
								</select>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-marketActivityName" name="name">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-startTime" name="startDate">
							</div>
							<label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-endTime" name="endDate">
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost" name="cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-describe" name="description"></textarea>
							</div>
						</div>

					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="createActivityBtn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form" id="editActivityForm">
						<input type="hidden" id="activityId"/>
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityOwner">
								  <%--<option>zhangsan</option>
								  <option>lisi</option>
								  <option>wangwu</option>--%>
								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-marketActivityName" value="发传单">
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-startTime" value="2020-10-10">
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-endTime" value="2020-10-20">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost" value="5,000">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-describe">市场活动Marketing，是指品牌主办或参与的展览会议与公关市场活动，包括自行主办的各类研讨会、客户交流会、演示会、新产品发布会、体验会、答谢会、年会和出席参加并布展或演讲的展览会、研讨会、行业交流会、颁奖典礼等</textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" onclick="updateActivity()" class="btn btn-primary" data-dismiss="modal">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" name="name" >
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" name="owner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control" type="text" id="startTime" name="startTime" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control" type="text" id="endTime" name="endTime">
				    </div>
				  </div>
				  
				  <button type="button" class="btn btn-default" id="activityQueryBtn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" onclick="queryAllUser()" class="btn btn-primary" data-toggle="modal" data-target="#createActivityModal"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" onclick="editActivity()" class="btn btn-default" data-toggle="modal" ><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" /></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="activityBody">
						<%--<tr class="active">
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.html';">发传单</a></td>
                            <td>zhangsan</td>
							<td>2020-10-10</td>
							<td>2020-10-20</td>
						</tr>
                        <tr class="active">
                            <td><input type="checkbox" /></td>
                            <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.html';">发传单</a></td>
                            <td>zhangsan</td>
                            <td>2020-10-10</td>
                            <td>2020-10-20</td>
                        </tr>--%>
					</tbody>
				</table>
			</div>
			
			<div style="height: 50px; position: relative;top: 30px;">

                <div id="activityPage"></div>

			</div>
			
		</div>
		
	</div>
</body>
</html>
<script>
	//点击修改按钮，js判断
	function editActivity() {
		//获取勾中的市场活动
		var length = $('input[type=checkbox]:checked').length;
		if(length ==0){
			alert("至少选中一项！")
		}else if(length>1) {
			alert("只能选择一项！")
		}else {
			//当程序执行到这里，说明此时选中一条信息是正确的，所以在这里要弹出修改市场活动模态窗口
			$('#editActivityModal').modal('show')
			//发送修改请求
			//先获取被选中信息的id（这里的id如何获取? 我们可以考虑在一开始查询市场列表信息，在前台拼接活动信息json串环节，将每条遍历出来的信息的复选框的value值设置为当前信息的id）
			var id = $('input[type=checkbox]:checked')[0].value;
			//alert(id)
			//根据此id发送异步请求查询选中活动的相关信息
			$.ajax({
				url : '/crm/workbench/activity/queryById',
				data :{'id':id},
				type :'get',
				dataType : 'json',
				success : function(data){
					//console.log(data) 程序执行到这里表示通过id查询到了当前选中条目的信息
					//取出当前活动信息的owner
					var owner = data.owner;
					//此时需要将当前选中信息的内容回显到修改模态窗口中
					//采用异步方式在查询出当前所有用户
					$.ajax({
						url : '/crm/queryAllUser',
						data :'',
						type :'get',
						dataType : 'json',
						success : function(data){
							//console.log(data) 成功获取到当前所有用户
							//遍历当前所有用户
							for(var i=0;i<data.length;i++){
								$('#edit-marketActivityOwner').append("<option value="+data[i].id+">"+data[i].name+"</option>")
							}
							$('#edit-marketActivityOwner option').each(function () {
								//遍历每个option标签 取出每一个标签的value
								var userId = $(this).val();
								if(userId==owner){
									$(this).prop('selected',true);
								}

							})
						}
					});
					//完善表单其他信息
					$('#edit-marketActivityName').val(data.name)//这里的data是通过选中条目的id查询到的json串信息
					$('#edit-startTime').val(data.startDate)
					$('#edit-endTime').val(data.endDate)
					$('#edit-cost').val(data.cost)
					$('#edit-describe').val(data.description)
					//由于在进行数据修改是，需要一个隐藏域带着id去后台进行修改操作
					$('#activityId').val(data.id)
				}
			});

		}
	}
	//点击更新按钮，更新市场活动信息
	function updateActivity() {
		$.ajax({
			url : '/crm/workbench/activity/createOrUpdateActivity',
			data :{
				'id':$('#activityId').val(),
				'owner':$('#edit-marketActivityOwner').val(),
				'name':$('#edit-marketActivityName').val(),
				'satrtDate':$('#edit-startTime').val(),
				'endDate':$('#edit-endTime').val(),
				'cost':$('#edit-cost').val(),
				'description':$('#edit-describe').val()
			},
			type :'get',
			dataType : 'json',
			success : function(data){
				if(data.flag){
					alert(data.mess)
				}else{
					alert(data.mess)
				}
				//关闭模态窗口
				$('#editActivityModal').modal('hide')
				//重置表单
				document.getElementById("editActivityForm").reset();

			}
		});
	}
	//点击新建按钮，异步查询所有用户信息
	function queryAllUser(){
		$.ajax({
			url : '/crm/queryAllUser',
			data :'',
			type :'get',
			dataType : 'json',
			success : function(data){
				for(var i = 0; i<data.length;i++){
					$('#create-marketActivityOwner').append("<option value="+data[i].id+">"+data[i].name+"</option>\n" )
				}
			}
		});
	}
	//异步提交表单
	//给保存按钮绑定单击事件
	$('#createActivityBtn').click(function () {
		$.ajax({
			url : '/crm/workbench/activity/createOrUpdateActivity',
			data :{
				'owner':$('#create-marketActivityOwner').val(),
				'name':$('#create-marketActivityName').val(),
				'startDate':$('#create-startTime').val(),
				'endDate':$('#create-endTime').val(),
				'cost':$('#create-cost').val(),
				'description':$('#create-describe').val()
			},
			type :'get',
			dataType : 'json',
			success : function(data){
				//alert(data.flag)
				if(data.flag){
					alert(data.mess);
				}else {
					alert(data.mess)
				}
				//关闭模态窗口
				$('#createActivityModal').hide();
				//重置表单
				document.getElementById("createActivityForm").reset()
			}
		});
	})

	//点击查询按钮，出发点击事件，调用refresh函数开始查询数据
	$('#activityQueryBtn').click(function () {
		refresh(1,3);
	});
	//第一次进入列表页面
	refresh(1,3);
	//刷新页面的方法
	function refresh(page,pageSize){
		//异步查询所有市场活动的信息
		$.ajax({
			url : '${pageContext.request.contextPath}/workbench/activity/list',
			data :{
				'page':page,
				'pageSize':pageSize,
				'name':$('input[name=name]').val(),//根据name属性取值
				'owner':$('input[owner=owner]').val(),
				'startDate':$('#startTime').val(),
				'endDate':$('#endTime').val()
			},
			type :'get',
			dataType : 'json',
			success : function(data){
				//清空上一次动态拼接的内容
				$('#activityBody').html("");
				//遍历出所有的市场活动信息
				//console.log(data)   //data  就是ResultVo
				//data中有好多数据，在这里取出所有的市场活动信息的数据 dataList
				var dataList = data.pageInfo.list;
				for(var i=0; i<dataList.length;i++){
					//获取到展示市场活动信息的表格（通过id获取），然后将遍历出的信息追加到表格中
					$('#activityBody').append("<tr class=\"active\">\n" +
							"\t\t\t\t\t\t\t<td><input type=\"checkbox\" value="+dataList[i].id+" /></td>\n" +
							"\t\t\t\t\t\t\t<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href='detail.html';\">"+dataList[i].name+"</a></td>\n" +
							"                            <td>"+dataList[i].owner+"</td>\n" +
							"\t\t\t\t\t\t\t<td>"+dataList[i].startDate+"</td>\n" +
							"\t\t\t\t\t\t\t<td>"+dataList[i].endDate+"-10-20</td>\n" +
							"\t\t\t\t\t\t</tr>")
				}
				//利用分页插件，查询第一页数据
				$("#activityPage").bs_pagination({
					//此时，下面分页所需要的数据信息都包含在data中，所以一次取出
					currentPage: data.pageInfo.pageNum, // 页码
					rowsPerPage: data.pageInfo.pageSize, // 每页显示的记录条数
					maxRowsPerPage: 20, // 每页最多显示的记录条数
					totalPages: data.pageInfo.pages, // 总页数
					totalRows: data.pageInfo.total, // 总记录条数
					visiblePageLinks: 3, // 显示几个卡片
					showGoToPage: true,
					showRowsPerPage: true,
					showRowsInfo: true,
					showRowsDefaultInfo: true,
					//回调函数，用户每次点击分页插件进行翻页时候，就会触发该函数
					onChangePage : function(event, obj){
						//在此处将页码传入后台
						//刷新页面  obj.currentPage:当前点击的页码   obj.rowsPerPage,
						refresh(obj.currentPage,obj.rowsPerPage);
					}
				});
			}
		});
	}
	//日历插件
	//起始时间
	$("#startTime").datetimepicker({
		language:  "zh-CN",
		format: "yyyy-mm-dd",//显示格式
		minView: "month",//设置只显示到月份
		initialDate: new Date(),//初始化当前日期
		autoclose: true,//选中自动关闭
		todayBtn: true, //显示今日按钮
		clearBtn : true,
		pickerPosition: "bottom-left"
	});
	//结束时间
	$("#endTime").datetimepicker({
		language:  "zh-CN",
		format: "yyyy-mm-dd",//显示格式
		minView: "month",//设置只显示到月份
		initialDate: new Date(),//初始化当前日期
		autoclose: true,//选中自动关闭
		todayBtn: true, //显示今日按钮
		clearBtn : true,
		pickerPosition: "bottom-left"
	});
	$("#create-startTime").datetimepicker({
		language:  "zh-CN",
		format: "yyyy-mm-dd",//显示格式
		minView: "month",//设置只显示到月份
		initialDate: new Date(),//初始化当前日期
		autoclose: true,//选中自动关闭
		todayBtn: true, //显示今日按钮
		clearBtn : true,
		pickerPosition: "bottom-left"
	});
	//结束时间
	$("#create-endTime").datetimepicker({
		language:  "zh-CN",
		format: "yyyy-mm-dd",//显示格式
		minView: "month",//设置只显示到月份
		initialDate: new Date(),//初始化当前日期
		autoclose: true,//选中自动关闭
		todayBtn: true, //显示今日按钮
		clearBtn : true,
		pickerPosition: "bottom-left"
	});
</script>