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
                <label for="inputEmail" class="col-sm-1 control-label">被保人姓名</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="inputEmail" placeholder="被保人姓名">
                </div>
            </div>
            <div class="form-group col-md-offset-6">
                <label for="inputPassword" class="col-sm-2 control-label">被保人身份证号</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="inputPassword" placeholder="被保人身份证号">
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div>
                <label for="inputEmail2" class="col-sm-1 control-label">被保人年龄</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="inputEmail2" placeholder="被保人年龄">
                </div>
            </div>
            <div class="col-md-offset-6">
                <label for="inputPassword2" class="col-sm-2 control-label">赔付额上限</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="inputPassword2" placeholder="赔付额上限">
                </div>
            </div>
            <div class="row"></div>
            <br>
            <label for="inputPassword2" class="col-sm-1 control-label">被保人性别</label>
            <div class="form-group col-sm-3">
                <label class="radio-inline">
                    <input type="radio" value="option1" name="payment">男
                </label>
                <label class="radio-inline">
                    <input type="radio" value="option2" name="payment">女
                </label>
            </div>
            <div class="col-md-offset-6 form-group">
                <label for="inputPassword2" class="col-sm-2 control-label">可选保险</label>
                <div class="form-group col-sm-3">
                    <label class="checkbox-inline">
                        <input type="checkbox" value="option1" name="payment">人身意外险
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" value="option2" name="payment">重疾险
                    </label>
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div>
                <label for="inputEmail4" class="col-sm-1 control-label">备注</label>
                <div class="col-sm-9">
                    <textarea type="text" class="form-control" id="inputEmail4" rows="5" placeholder="备注,最多可填100字"></textarea>
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">提交</button>
                </div>
            </div>

        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>
<script src="${pageContext.request.contextPath }/statics/localjs/rollpage.js"></script>
<script src="${pageContext.request.contextPath }/statics/localjs/appinfolist.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/jquery.min.js" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath }/statics/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/datepicker/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath }/statics/localjs/policyBasicInfoList.js"></script>