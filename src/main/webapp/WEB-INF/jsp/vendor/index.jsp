<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<!--<h1>Product Inventory</h1> -->

<div class="container">
    <div class="main">
        <h1 class="page-header">Manage Product</h1>
        <div class="clearfix">
            <div class="pull-right">
                <form id="filterForm" class="form-inline">
                    <input type="text" name="name" class="form-control" width="100" placeholder="Product name">
                    <select name="categoryId" class="form-control">
                        <option value="">Select Category</option>
                        <c:forEach items="${categories}" var="row">
                            <option value="${row.id}">${row.name}</option>
                            <c:if test="${row.childCategories ne null}">
                                <c:forEach items="${row.childCategories}" var="cRow">
                                    <option value="${cRow.id}">---${cRow.name}</option>
                                    <c:if test="${cRow.childCategories ne null}">
                                        <c:forEach items="${cRow.childCategories}" var="sRow">
                                            <option value="${sRow.id}">------${sRow.name}</option>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </select>
                    <button class="btn btn-default" type="button" onclick="module.list()">Search</button>
                </form>
            </div>
        </div>
        <div class="table-responsive">
            <div id="list-target"></div>
            <br/>
            <button class="btn btn-default" type="button" onclick="module.create()">Add product</button>
        </div>
    </div>
</div>



<%@include file="/WEB-INF/jsp/template/footer.jsp"%>

<script type="text/javascript">
    $(function () {
        module.list();
    });
    module = {
        list : function(){
            $.get('/vendor/product/list', $("#filterForm").serialize(), function(data){
                $('#list-target').html(data);
            });
        },
        edit : function(id){
            $('#edit-target').html('');
            $('#edit-modal').modal({
                backdrop: 'static'
            });
            $.get('/vendor/product/save', 'id='+id, function(data){
                $('#edit-target').html(data);
            });
        },
        create : function(){
            $('#edit-target').html('');
            $('#edit-modal').modal({
                backdrop: 'static'
            });
            $.get('/vendor/product/save', null, function(data){
                $('#edit-target').html(data);
            });
        },
        submit: function() {
            $('#productForm').submit();
        },
        init: function() {
            $('#productForm').ajaxForm({
                target:'#edit-target',
                url:'/vendor/product/save'
            });
        },
        success: function() {
            $('#edit-modal').modal('hide');
            module.list();
        },

        delete: function(id){
            smoke.confirm('Are sure to delete this!',function(e){
                if (e){
                    $.get('/vendor/product/delete', 'id='+id, function(msg){
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