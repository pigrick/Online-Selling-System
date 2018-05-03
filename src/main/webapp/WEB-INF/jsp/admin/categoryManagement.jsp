<%@include file="/WEB-INF/include.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: chanpiseth
  Date: 4/25/2018
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>
<%@include file="/WEB-INF/include.jsp"%>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <h1 class="page-header">Category Management</h1>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>MainCategory</th>
                <th>SubCategory</th>
                <th>Status</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categoryList}" var="category">
                <tr>
                    <td>${category.parentCategory.name}</td>
                    <td>${category.name}</td>
                    <td>${category.status}</td>

                    <td>
                            <%--<a href="#" type="button" onclick="module.delete('${row.id}')">
                           <i class="glyphicon glyphicon-info-sign"></i>
                       </a>--%>
                        <a href="#edit" onclick="module.edit('${category.id}')">
                            <i class="glyphicon glyphicon-pencil"></i>
                        </a>
                        <c:if test="${category.status eq 'ENABLED'}">
                            <a href="#create" type="button" onclick="module.delete('${category.id}')">
                                <i class="glyphicon glyphicon-remove"></i>
                            </a>
                        </c:if>
                        <c:if test="${category.status ne 'ENABLED'}">
                            <a href="#changeStatus" type="button"
                               onclick="module.changeStatus('${category.id}', 'ENABLED')">
                                <i class="glyphicon glyphicon-ok"></i>
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="/admin/category/save"><button class="btn btn-primary">Add Category</button></a>

    </div>
</div>
</div>
</div>

<%@include file="/WEB-INF/jsp/template/footer.jsp"%>

<script type="text/javascript">
    $(function () {
        module.list();
    });
    module = {

        create : function(){
            $('#edit-target').html('');
            $('#edit-modal').modal({
                backdrop: 'static'
            });
            $.get('/admin/user/admin/create', null, function(data){
                $('#edit-target').html(data);
            });
        },
        edit : function(id){
            $('#edit-target').html('');
            $('#edit-modal').modal({
                backdrop: 'static'
            });
            $.get('/admin/user/admin/edit', 'id='+id, function(data){
                $('#edit-target').html(data);
            });
        },
        changeStatus: function(id,status){
            smoke.confirm('Are sure to change status of this!',function(e){
                if (e){
                    $.get('/admin/user/changeStatus', 'id='+id+'&status='+status, function(msg){
                        var message = msg.message || msg;
                        var type = 'st-'+(msg.type || 'success').toLowerCase();
                        $.sticky(message, {autoclose: 10000, position:'top-right', type: type});
                        module.list();
                    });
                }
            }, {cancel:"Cancel", ok:"Change"});
        },
        delete: function(id){
            smoke.confirm('Are sure to delete this!',function(e){
                if (e){
                    $.get('/admin/user/delete', 'id='+id, function(msg){
                        var message = msg.message || msg;
                        var type = 'st-'+(msg.type || 'success').toLowerCase();
                        $.sticky(message, {autoclose: 10000, position:'top-right', type: type});
                        module.list();
                    });
                }
            }, {cancel:"Cancel", ok:"Delete"});
        }
    };
</script>