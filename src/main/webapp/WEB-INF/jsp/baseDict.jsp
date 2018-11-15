<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="itheima" uri="http://itheima.com/common/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1"%>
<%--
  Created by IntelliJ IDEA.
  User: 11
  Date: 2018/10/30
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>数据管理</title>
    <!-- 引入css样式文件 -->
    <!-- Bootstrap Core CSS -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" />
    <!-- MetisMenu CSS -->
    <link href="<%=basePath%>css/metisMenu.min.css" rel="stylesheet" />
    <!-- DataTables CSS -->
    <link href="<%=basePath%>css/dataTables.bootstrap.css" rel="stylesheet" />
    <!-- Custom CSS -->
    <link href="<%=basePath%>css/sb-admin-2.css" rel="stylesheet" />
    <!-- Custom Fonts -->
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>css/boot-crm.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="wrapper">
    <!-- 导航栏部分 -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation"
         style="margin-bottom: 0">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/list.action">BOOT客户管理系统 v2.0</a>
        </div>

        <!-- 左侧显示列表部分 start-->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="sidebar-search">
                        <div class="input-group custom-search-form">
                            <input type="text" class="form-control" placeholder="查询内容...">
                            <span class="input-group-btn">
							<button class="btn btn-default" type="button">
								<i class="fa fa-search" style="padding: 3px 0 3px 0;"></i>
							</button>
						</span>
                        </div>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath }/list.action" >
                            <i class="fa fa-edit fa-fw"></i> 客户管理
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/search.action" class="active">
                            <i class="fa fa-dashboard fa-fw" ></i> 数据管理
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- 左侧显示列表部分 end-->
    </nav>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">数据管理</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <%--查询部分--%>
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" method="get"
                      action="${pageContext.request.contextPath }/search.action">

                    <div class="form-group">
                        <label for="dict_typeName">类别名称</label>
                        <select class="form-control" id="dict_typeName" name="dict_type_name">
                            <option value="" >--请选择--</option>
                        </select>
                    </div>


                    <div class="form-group">
                        <label for="dict_itemName">项目名称</label>
                        <select class="form-control" id="dict_itemName" name="dict_item_name">
                            <option value="" >--请选择--</option>

                        </select>
                    </div>


                    <button type="submit" class="btn btn-primary">查询</button>
                </form>
            </div>
        </div>
        <%--查询部分结束--%>

        <a href="#" class="btn btn-primary" data-toggle="modal"
           data-target="#newBaseDictModal" onclick="clearBaseDict()">新建</a>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">基础数据列表 <span style="text-align:right">共有数据：<strong>${count}</strong>条</span></div>

                    <!-- /.panel-heading -->
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>数据字典ID</th>
                            <th>数据类别代码</th>
                            <th>数据类别名称</th>
                            <th>数据项目名称</th>
                            <th>排序字段</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.rows}" var="row">
                            <tr style="text-align: center">
                                <td>${row.dict_id}</td>
                                <td>${row.dict_type_code}</td>
                                <td>${row.dict_type_name}</td>
                                <td>${row.dict_item_name}</td>
                                <td>${row.dict_sort}</td>
                                <td>${row.dict_enable}</td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#editModal" onclick= "getBaseDictID(${row.dict_id})">修改</a>
                                    <a href="#" class="btn btn-danger btn-xs" onclick="deleteBase_dict(${row.dict_id})">删除</a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>

                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
                <div class="col-md text-right">
                    <itheima:page url="${pageContext.request.contextPath}/search.action" />
                </div>
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </div>
</div>

<%--新建数据模态框--%>
<div class="modal fade" id="newBaseDictModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel1">
                    新建数据
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="new_BaseDict_form">
                    <div class="form-group">
                        <label for="new_typeCode" class="col-sm-2 control-label">类别代码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_typeCode" placeholder="类别代码" name="dict_type_code" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_typeName" class="col-sm-2 control-label">类别名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_typeName" placeholder="类别名称" name="dict_type_name" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_itemName" class="col-sm-2 control-label">项目名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_itemName" placeholder="项目名称" name="dict_item_name" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_sort" class="col-sm-2 control-label">排序字段</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_sort" placeholder="排序字段" name="dict_sort" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_enable" class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_enable" placeholder="状态：0:停用;1:使用" name="dict_enable" />
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="addBaseDict()">
                    保存数据
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<%--修改的模态框--%>
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    修改数据
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_BaseDict_form">

                    <input type="hidden" id="edit_dict_id" name="dict_id"/>

                    <div class="form-group">
                        <label for="edit_typeCode" class="col-sm-2 control-label">类别代码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_typeCode" placeholder="类别代码" name="dict_type_code" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_typeName" class="col-sm-2 control-label">类别名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_typeName" placeholder="类别代码" name="dict_type_name" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_itemName" class="col-sm-2 control-label">项目名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_itemName" placeholder="项目名称" name="dict_item_name" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_sort" class="col-sm-2 control-label">排序字段</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_sort" placeholder="排序字段" name="dict_sort" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_enable" class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_enable" placeholder="状态：0:停用;1:使用" name="dict_enable" />
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="updateBaseDict()">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 引入js文件 -->
<!-- jQuery -->
<script src="<%=basePath%>js/jquery-1.11.3.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="<%=basePath%>js/metisMenu.min.js"></script>
<!-- DataTables JavaScript -->
<script src="<%=basePath%>js/jquery.dataTables.min.js"></script>
<script src="<%=basePath%>js/dataTables.bootstrap.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="<%=basePath%>js/sb-admin-2.js"></script>
<!-- 编写js代码 -->
<script type="text/javascript">

    //清空新建客户窗口中因为前一次填写而没有保存的数据
    function clearBaseDict(){
        $("#new_typeName").val("");
        $("#new_typeCode").val("");
        $("#new_itemName").val("");
        $("#new_sort").val("");
        $("#new_enable").val("");
    }

    //添加
    function addBaseDict() {
        console.log("js!!!")
        $.post("<%=basePath%>add.action",
            $("#new_BaseDict_form").serialize(),
            function(data){
                if(data =="OK"){
                    alert("数据创建成功！");
                    window.location.reload();
                }else{
                    alert("数据创建失败！");
                    window.location.reload();
                }
            });
    }
    //通过id获取各个值
    function getBaseDictID(id) {
        $.ajax({
            type:"get",
            url:"<%=basePath%>getBaseDictById.action",
            data:{"id":id},
            success: function(data)
            {
               $("#edit_dict_id").val(data.dict_id);
               $("#edit_typeCode").val(data.dict_type_code);
               $("#edit_typeName").val(data.dict_type_name);
               $("#edit_itemName").val(data.dict_item_name);
               $("#edit_sort").val(data.dict_sort);
               $("#edit_enable").val(data.dict_enable);

            }
        });
    }

    //执行修改的操作
    function updateBaseDict() {
        $.post("<%=basePath%>editBaseDict.action",
            $("#edit_BaseDict_form").serialize(),
            function(data){
                if(data =="OK"){
                    alert("数据信息更新成功！");
                    window.location.reload();
                }else{
                    alert("数据信息更新失败！");
                    window.location.reload();
                }
            });
    }


     //执行假删除，将其状态改为0;
    function deleteBase_dict(id) {
        if(confirm("确定要删除吗")){
            $.post("<%=basePath%>deleteBase_Dict.action",
                {dict_id:id},
                function (data) {
                    if(data =="OK"){
                        alert("数据删除成功！");
                        window.location.reload();
                    }else{
                        alert("删除数据失败！");
                        window.location.reload();
                    }
                }
            );
        }
    }

</script>
<script type="text/javascript">
    var data=${data};
    console.log(data);//查看json数组是否传递过来；
    var _sel1=$("#dict_typeName");
    var _sel2=$("#dict_itemName");
    $.each(data,function (i,e) {                                    //each()循环遍历的函数，第一个参数data即为需要遍历的值，后面的就是循环要做的事情
        $(_sel1).append('<option>' +e.dict_type_name+ '</option>');


    });
    $(_sel1).on('change',function () {                              //change事件，当第一个下拉框发生改变时
        $(_sel2).empty();                                            //清空第二个下拉框中的内容
        $.each(data,function (i,e) {                                //i就是index,是索引,e是element,是每个元素
            if(_sel1.val()==e.dict_type_name){
                $.each(e.dict_item_list,function (i1,e1) {
                    $(_sel2).append('<option selected>'+e1.dict_item_name+'</option>');
                });

            }
        });
    })

</script>
</body>
</html>
