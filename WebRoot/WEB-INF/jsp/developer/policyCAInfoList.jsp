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
                        &nbsp;&nbsp;&nbsp;&nbsp; 单号： XXXX &nbsp;&nbsp;
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
            <%--<div class="x_content">--%>
                <%--<p class="text-muted font-13 m-b-30"></p>--%>
                <%--<div id="datatable-responsive_wrapper"--%>
                     <%--class="dataTables_wrapper form-inline dt-bootstrap no-footer">--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-sm-12">--%>
                            <%--<a href="${pageContext.request.contextPath}/dev/flatform/app/appinfoadd"--%>
                               <%--class="btn btn-success btn-sm">新增APP基础信息</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <form class="form-label-left" action="insuredCAinfoaddsave" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="inputEmail" class="col-sm-1 control-label">车辆型号</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="inputEmail" placeholder="车辆型号"  name="vehicleModel" required ${insuredCAInfo.vehicleModel }>
                </div>
            </div>
            <div class="form-group col-md-offset-6">
                <label for="inputPassword" class="col-sm-2 control-label">保额赔付上限</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="inputPassword" placeholder="保额赔付上限（元人民币"  name="limitCA" required ${insuredCAInfo.limitCA }>
                </div>
            </div>
            <div class="row"></div>
            <br>
            <label for="inputPassword" class="col-sm-1 control-label">可选保险</label>
            <div class="form-group col-sm-3" >
                <label class="checkbox-inline">
                    <input type="checkbox" value="1"  name="carDamage"  ${insuredCAInfo.carDamage} >车损险
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" value="1"  name="carFire"  ${insuredCAInfo.carFire}>自燃险
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" value="1"  name="carGlass"  ${insuredCAInfo.carGlass}>玻璃险
                </label>
            </div>
            <div class="col-md-offset-6 form-group">
                <label for="dtp_input2" class="col-sm-2 control-label">车辆买入时间</label>
                <div class="input-group date form_date col-sm-6" data-date="" data-date-format="MM dd yyyy"
                     data-link-field="dtp_input2" data-link-format="yyyy-mm-dd" >
                    <input class="form-control" size="1" type="text" value="" name="vehicleBuyInTime" required ${insuredCAInfo.vehicleBuyInTime }>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
                <input type="hidden" id="dtp_input2" value=""/><br/>
            </div>
            <div class="row"></div>
            <br>
            <div>
                <label for="inputEmail4" class="col-sm-1 control-label">备注</label>
                <div class="col-sm-9">
                    <textarea type="text" class="form-control" id="inputEmail4" rows="5" placeholder="备注,最多可填100字"  name="commentCA" required ${insuredCAInfo.commentCA }></textarea>
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button id="send" type="submit" class="btn btn-success">下一页</button>
                    <button type="button" class="btn btn-primary" id="back">清除</button>
                </div>
            </div>
            <div class="clearfix"></div>
            <br/><br/>
        </form>
        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>
<script src="${pageContext.request.contextPath }/statics/localjs/rollpage.js"></script>
<script src="${pageContext.request.contextPath }/statics/localjs/appinfolist.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/jquery.min.js" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath }/statics/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/datepicker/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath }/statics/localjs/policyCAInfoList.js"></script>