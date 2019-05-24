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
        <form class="form-label-left" action="insuredOverviewinfoaddsave" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="inputEmail" class="col-sm-1 control-label">购买保险人姓名</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="inputEmail" placeholder="购买保险人姓名" name="buyerName" value="${newPolicy.buyerName}" >
                </div>
            </div>
            <div class="form-group col-md-offset-6">
                <label for="inputPassword" class="col-sm-2 control-label">购买者身份证号</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="inputPassword" placeholder="购买保险人身份证号"  name="buyerID" value="${newPolicy.buyerID}">
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div>
                <label for="inputEmail2" class="col-sm-1 control-label">购买者联系方式</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="inputEmail2" placeholder="购买保险人联系方式" name="buyerPhone" value="${newPolicy.buyerPhone }" >
                </div>
            </div>
            <div class="col-md-offset-6">
                <label for="inputPassword2" class="col-sm-2 control-label">受益人姓名</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="inputPassword2" placeholder="受益人姓名"  name="beneficiaryName" value="${newPolicy.beneficiaryName }" >
                </div>
            </div>
            <div class="row"></div>
            <br>
            <%--<div>--%>
                <%--<label for="inputEmail2" class="col-sm-1 control-label">Email</label>--%>
                <%--<div class="col-sm-3">--%>
                    <%--<input type="text" class="form-control" id="inputEmail3" placeholder="Email">--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-md-offset-6">--%>
                <%--<label for="inputPassword2" class="col-sm-2 control-label">Password</label>--%>
                <%--<div class="col-sm-6">--%>
                    <%--<input type="text" class="form-control" id="inputPassword3" placeholder="Password">--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="row"></div>--%>
            <%--<br>--%>
            <label for="inputPassword2" class="col-sm-1 control-label">支付方式</label>
            <div class="col-md-offset-6 showPayMathod hidden">
                <label for="inputPassword2" class="col-sm-2 control-label">paymethodhidden</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control " id="paymethodhidden"  name="paymentType" value="${newPolicy.paymentType }" >
                </div>
            </div>

            <div class="form-group col-sm-3" id="paymethod" value="${newPolicy.paymentType }" >
                <label class="radio-inline">
                    <input type="radio" value="1" name="payment" >现金
                </label>
                <label class="radio-inline">
                    <input type="radio" value="2" name="payment">支付宝/微信
                </label>
                <label class="radio-inline">
                    <input type="radio" value="3" name="payment">信用卡
                </label>
            </div>
            <div class="col-md-offset-6 form-group">
                <label for="dtp_input2" class="col-sm-2 control-label">保单开始时间</label>
                <div class="input-group date form_date col-sm-6" data-date="" data-date-format="MM dd yyyy"
                     data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="1" type="text"  name="startDate" value="${newPolicy.startDate }" >
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
                <input type="hidden" id="dtp_input2" value=""/><br/>
            </div>
            <div class="row"></div>
            <br>
            <div class="form-group">
                <label for="inputEmail" class="col-sm-1 control-label">车辆型号</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="inputEmai2l" placeholder="车辆型号"  name="vehicleModel" value="${CALine.vehicleModel }">
                </div>
            </div>
            <div class="form-group col-md-offset-6">
                <label for="inputPassword" class="col-sm-2 control-label">保额赔付上限</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="inputPassword22" placeholder="保额赔付上限（元人民币）" name="limitCA" value="${CALine.limitCA }" >
                </div>
            </div>
            <div class="row"></div>
            <br>
            <label for="inputPassword" class="col-sm-1 control-label">可选保险</label>
            <div class="col-md-offset-6 showCALineCoverage hidden">
                <label for="inputPassword2" class="col-sm-2 control-label">CALineCoveragehidden</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control " id="CALineCarDamagehidden"   name="carDamage" value="${CALine.carDamage}" >
                    <input type="text" class="form-control " id="CALineCarFirehidden"   name="carFire" value="${CALine.carFire}" >
                    <input type="text" class="form-control " id="CALineCarGlasshidden"   name="carGlass" value="${CALine.carGlass}">
                </div>
            </div>
            <div class="form-group col-sm-3">
                <label class="checkbox-inline">
                    <input type="checkbox" value="true"  name="carDamage" ${CALine.carDamage}>车损险
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" value="true"  name="carFire" ${CALine.carFire}>自燃险
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" value="true"  name="carGlass" ${CALine.carGlass}>玻璃险
                </label>
            </div>
            <div class="col-md-offset-6 form-group">
                <label for="dtp_input2" class="col-sm-2 control-label">车辆买入时间</label>
                <div class="input-group date form_date col-sm-6" data-date="" data-date-format="MM dd yyyy"
                     data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="1" type="text" name="vehicleBuyInTime" value="${CALine.vehicleBuyInTime }">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
                <input type="hidden" id="dtp_input22" value=""/><br/>
            </div>
            <div class="row"></div>
            <br>
            <div class="form-group">
                <label for="inputEmail" class="col-sm-1 control-label">被保人姓名</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="inputEmai3l" placeholder="被保人姓名" name="insuredPeople" value="${GLLine.insuredPeople }" >
                </div>
            </div>
            <div class="form-group col-md-offset-6">
                <label for="inputPassword" class="col-sm-2 control-label">被保人身份证号</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="inputPassword33" placeholder="被保人身份证号"  name="insuredPeopleID" value="${GLLine.insuredPeopleID }" >
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div>
                <label for="inputEmail2" class="col-sm-1 control-label">被保人年龄</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="inputEmail32" placeholder="被保人年龄" name="insuredPeopleAge" value="${GLLine.insuredPeopleAge }">
                </div>
            </div>
            <div class="col-md-offset-6">
                <label for="inputPassword2" class="col-sm-2 control-label">赔付额上限</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="inputPassword32" placeholder="赔付额上限"  name="limitGL" value="${GLLine.limitGL }">
                </div>
            </div>
            <div class="row"></div>
            <br>
            <label for="inputPassword2" class="col-sm-1 control-label">被保人性别</label>
            <div class="col-md-offset-6 showInsuredPeopleGender hidden">
                <label for="inputPassword2" class="col-sm-2 control-label">genderhidden</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control " id="genderhidden"   name="insuredPeopleGender" value="${GLLine.insuredPeopleGender }" >
                </div>
            </div>
            <div class="form-group col-sm-3 choiceGender" id="peoplegender">
                <label class="radio-inline">
                    <input type="radio" value="true" name="gender">男
                </label>
                <label class="radio-inline">
                    <input type="radio" value="false" name="gender">女
                </label>
            </div>
            <div class="col-md-offset-6 form-group">
                <label for="inputPassword2" class="col-sm-2 control-label">可选保险</label>
                <div class="col-md-offset-6 showGLLineCoverage hidden">
                    <label for="inputPassword2" class="col-sm-2 control-label">GLLineCoveragehidden</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control " id="GLLinePeopleAccidenthidden"   name="peopleAccident" value="${GLLine.peopleAccident}"  >
                        <input type="text" class="form-control " id="GLLinePeopleIllnesshidden"   name="peopleIllness" value="${GLLine.peopleIllness}" >
                    </div>
                </div>
                <div class="form-group col-sm-3">
                    <label class="checkbox-inline">
                        <input type="checkbox" value="true"  name="peopleAccident" value="${GLLine.peopleAccident}" >人身意外险
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" value="true"  name="peopleIllness" value="${GLLine.peopleIllness}" >重疾险
                    </label>
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div>
                <label for="inputEmail4" class="col-sm-1 control-label">基本信息备注</label>
                <div class="col-sm-9">
                    <textarea type="text" class="form-control" id="inputEmail4" rows="3" placeholder="备注,最多可填100字"  name="comment" >${newPolicy.comment }</textarea>
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div>
                <label for="inputEmail24" class="col-sm-1 control-label">车险信息备注</label>
                <div class="col-sm-9">
                    <textarea type="text" class="form-control" id="inputEmail24" rows="3" placeholder="备注,最多可填100字"  name="comment" >${CALine.comment }</textarea>
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div>
                <label for="inputEmail34" class="col-sm-1 control-label">寿险信息备注</label>
                <div class="col-sm-9">
                    <textarea type="text" class="form-control" id="inputEmail34" rows="3" placeholder="备注,最多可填100字" name="comment">${GLLine.comment}</textarea>
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div class="form-group">
                <label for="inputEmail" class="col-sm-2 control-label">保费</label>
                <div class="col-sm-3">
                    <%--<input type="text" class="form-control" id="premium" placeholder="最终保费" name="premium" value="${newPolicy.premium}" readonly="readonly">--%>
                    ${newPolicy.premium}
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button id="send" type="submit" class="btn btn-success">提交</button>
                    <button type="button" class="btn btn-primary" id="back">取消</button>
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
<script src="${pageContext.request.contextPath }/statics/localjs/policyOverview.js"></script>