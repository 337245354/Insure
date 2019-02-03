<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>
                    保单基本信息 <i class="fa fa-user"></i>
                    <small>${devUserSession.devName}
                        &nbsp;&nbsp;&nbsp;&nbsp; 单号： XXXX &nbsp;&nbsp;&nbsp;&nbsp; 状态：XXXXX
                    </small>
                </h2>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <form method="post" action="list">
                    <input type="hidden" name="pageIndex" value="1"/>
                    <ul>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">保单编号</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <%--<input name="querySoftwareName" type="text" class="form-control col-md-7 col-xs-12" value="${querySoftwareName }">--%>
                                    <input name="policyNumber" type="text" class="form-control col-md-7 col-xs-12"
                                           value="${policyNumber }">
                                </div>
                            </div>
                        </li>

                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">保单状态</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="queryStatus" class="form-control">
                                        <c:if test="${statusList != null }">
                                            <option value="">--请选择--</option>
                                            <c:forEach var="dataDictionary" items="${statusList}">
                                                <option
                                                        <c:if test="${dataDictionary.valueId == queryStatus }">selected="selected"</c:if>
                                                        value="${dataDictionary.valueId}">${dataDictionary.valueName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>
                        </li>
                        <li>
                            <button type="" class="btn btn-primary"> 查 &nbsp;&nbsp;&nbsp;&nbsp;询</button>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_content">
                <p class="text-muted font-13 m-b-30"></p>
                <div id="datatable-responsive_wrapper"
                     class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                    <div class="row">
                        <div class="col-sm-12">
                            <a href="${pageContext.request.contextPath}/dev/flatform/app/appinfoadd"
                               class="btn btn-success btn-sm">新增APP基础信息</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="inputEmail" class="col-sm-1 control-label">购买保险人姓名</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="inputEmail" placeholder="购买保险人姓名">
                </div>
            </div>
            <div class="form-group col-md-offset-6">
                <label for="inputPassword" class="col-sm-2 control-label">购买者身份证号</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="inputPassword" placeholder="购买保险人身份证号">
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div>
                <label for="inputEmail2" class="col-sm-1 control-label">购买者联系方式</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="inputEmail2" placeholder="购买保险人联系方式">
                </div>
            </div>
            <div class="col-md-offset-6">
                <label for="inputPassword2" class="col-sm-2 control-label">受益人姓名</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="inputPassword2" placeholder="受益人姓名">
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div>
                <label for="inputEmail2" class="col-sm-1 control-label">Email</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="inputEmail3" placeholder="Email">
                </div>
            </div>
            <div class="col-md-offset-6">
                <label for="inputPassword2" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="inputPassword3" placeholder="Password">
                </div>
            </div>

            <div class="row"></div>
            <br>
            <div>
                <label for="inputEmail3" class="col-sm-1 control-label">Email</label>
                <div class="col-sm-3">
                    <input type="text" value="2017-07-31" id="datetimepicker"  class="form_datetime" data-date-format="yyyy-mm-dd">
                </div>
            </div>



            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-2">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> Remember me
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Sign in</button>
                </div>
            </div>
            <%--</form>--%>

        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>
<script src="${pageContext.request.contextPath }/statics/localjs/rollpage.js"></script>
<script src="${pageContext.request.contextPath }/statics/localjs/appinfolist.js"></script>