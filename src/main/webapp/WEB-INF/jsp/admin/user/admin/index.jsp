<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<!--<h1>Product Inventory</h1> -->

<div class="container">
    <div class="main">

        <h1 class="page-header">Product Inventory</h1>

        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Name</th>
                    <th>Status</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="row">
                    <tr>
                        <td><i>${row.username}</i></td>
                        <td><i>${row.email}</i></td>
                        <td>${row.firstName} - ${row.lastName}</td>
                        <td>${row.status}</td>
                        <td>
                                <%--<a href="#" type="button" onclick="module.delete('${row.id}')">
                                    <i class="glyphicon glyphicon-info-sign"></i>
                                </a>--%>
                            <a href="/admin/user/admin/edit">
                                <i class="glyphicon glyphicon-pencil"></i>
                            </a>
                            <c:if test="${row.status eq 'ENABLED'}">
                                <a href="#delete" type="button" onclick="module.delete('${row.id}')">
                                    <i class="glyphicon glyphicon-remove"></i>
                                </a>
                            </c:if>
                            <c:if test="${row.status ne 'ENABLED'}">
                                <a href="#changeStatus" type="button" onclick="module.changeStatus('${row.id}', 'ENABLED')">
                                    <i class="glyphicon glyphicon-ok"></i>
                                </a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br/>
            <a href="<c:url value="/admin/u/c/a"/>"><button class="btn btn-primary">Add Admin</button></a>
        </div>
    </div>
</div>



<%@include file="/WEB-INF/jsp/template/footer.jsp"%>

<script type="text/javascript">
    module = {
        list : function(){
            window.location.replace("/admin/user/admin");
        },
        edit : function(id){
            $('#edit-target').html('');
            $('#createModal').modal({
                backdrop: 'static'
            });
            $.get('/admin/user/admin/edit', 'id='+id, function(data){
                $('#edit-target').html(data);
            });
        },
        changeStatus: function(id,status){
            //            smoke.confirm('Are sure to delete this!',function(e){
//                if (e){
            $.get('/admin/user/changeStatus', 'id='+id+'&status='+status, function(msg){
                var message = msg.message || msg;
                var type = 'st-'+(msg.type || 'success').toLowerCase();
//                        $.sticky(message, {autoclose: 10000, position:'top-right', type: type});
                module.list();
            });
//                }
//            }, {cancel:"Cancel", ok:"Delete"});
        },
        delete: function(id){
//            smoke.confirm('Are sure to delete this!',function(e){
//                if (e){
            $.get('/admin/user/delete', 'id='+id, function(msg){
                var message = msg.message || msg;
                var type = 'st-'+(msg.type || 'success').toLowerCase();
//                        $.sticky(message, {autoclose: 10000, position:'top-right', type: type});
                module.list();
            });
//                }
//            }, {cancel:"Cancel", ok:"Delete"});
        }
    };
</script>