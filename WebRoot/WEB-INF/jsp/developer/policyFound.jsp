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
                        &nbsp;&nbsp;&nbsp;&nbsp; 单号： ${insuredInfo.id} &nbsp;&nbsp;
                    </small>
                </h2>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <form class="form-horizontal form-label-left" >
                    <input type="hidden" name="id" id="id" value="${insuredInfo.id}">
                    <input type="hidden" name="buyerName" id="buyerName" value="${insuredInfo.buyerName}">
                    <input type="hidden" name="pageIndex" value="1"/>
                    <ul>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">保单编号</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <%--<input name="querySoftwareName" type="text" class="form-control col-md-7 col-xs-12" value="${querySoftwareName }">--%>
                                    <input name="policyNumber" type="text"   id="insuredinfoid" method = "get" class="form-control col-md-7 col-xs-12"  >
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
                        <li>
                            <a type="submit" class="btn btn-primary viewPolicy" >查 &nbsp;&nbsp;&nbsp;&nbsp;询</a>
                            <a href="${pageContext.request.contextPath}/dev/flatform/app/insuredinfoadd"
                               class="btn btn-success ">新 &nbsp;&nbsp;&nbsp;&nbsp;增</a>
                            <a href="${pageContext.request.contextPath}/dev/flatform/app/test123"
                               class="btn btn-warning ">修 &nbsp;&nbsp;&nbsp;&nbsp;改</a>
                        </li>

                    </ul>
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">

        <%--<form class="form-label-left" action="insuredinfoaddsave" method="post" enctype="multipart/form-data">--%>
            <div class="form-group" >
                <label for="inputEmail" class="col-sm-1 control-label">购买保险人姓名</label>
                <div class="col-sm-3 ">
                    <input class="form-control " value="${insuredInfo.buyerName }"  readonly="readonly" id="inputEmail"  placeholder="购买保险人姓名" >
                </div>
            </div>
            <div class="form-group col-md-offset-6">
                <label for="inputPassword" class="col-sm-2 control-label">购买者身份证号</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" value="${insuredInfo.buyerID }"  readonly="readonly" id="inputPassword" placeholder="购买保险人身份证号">
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div>
                <label for="inputEmail2" class="col-sm-1 control-label">购买者联系方式</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" value="${insuredInfo.buyerPhone }"  readonly="readonly" id="inputEmail2" placeholder="购买保险人联系方式">
                </div>
            </div>
            <div class="col-md-offset-6">
                <label for="inputPassword2" class="col-sm-2 control-label">受益人姓名</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" value="${insuredInfo.beneficiaryName }"  readonly="readonly" id="inputPassword2" placeholder="受益人姓名">
                </div>
            </div>
            <div class="row"></div>
            <br>
            <label for="inputPassword2" class="col-sm-1 control-label">支付方式</label>
            <div class="col-md-offset-6 showPayMathod hidden">
                <label for="inputPassword2" class="col-sm-2 control-label">paymethodhidden</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control " id="paymethodhidden"  name="paymentType"  value="${insuredInfo.paymentType }" >
                </div>
            </div>
            <div class="form-group col-sm-3" id="paymethod" value="${insuredInfo.paymentType }" >
                <label class="radio-inline">
                    <input type="radio" value="1" name="payment">现金
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
                    <input class="form-control" size="1" type="text"  readonly="readonly"  value="${insuredInfo.startDate }">
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
                    <input type="text" class="form-control" id="inputEmai2l" placeholder="车辆型号"  name="vehicleModel"  readonly="readonly" value="${insuredCAInfo.vehicleModel }">
                </div>
            </div>
            <div class="form-group col-md-offset-6">
                <label for="inputPassword" class="col-sm-2 control-label">保额赔付上限</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="inputPassword22" placeholder="保额赔付上限（元人民币）" name="limitCA"  readonly="readonly" value="${insuredCAInfo.limitCA }" >
                </div>
            </div>
            <div class="row"></div>
            <br>
            <label for="inputPassword" class="col-sm-1 control-label">可选保险</label>
            <div class="col-md-offset-6 showCALineCoverage hidden">
                <label for="inputPassword2" class="col-sm-2 control-label">CALineCoveragehidden</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control " id="CALineCarDamagehidden"   name="carDamage" value="${insuredCAInfo.carDamage}" >
                    <input type="text" class="form-control " id="CALineCarFirehidden"   name="carFire" value="${insuredCAInfo.carFire}" >
                    <input type="text" class="form-control " id="CALineCarGlasshidden"   name="carGlass" value="${insuredCAInfo.carGlass}">
                </div>
            </div>
            <div class="form-group col-sm-3">
                <label class="checkbox-inline">
                    <input type="checkbox" value="true"  name="carDamage" ${insuredCAInfo.carDamage}>车损险
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" value="true"  name="carFire" ${insuredCAInfo.carFire}>自燃险
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" value="true"  name="carGlass" ${insuredCAInfo.carGlass}>玻璃险
                </label>
            </div>
            <div class="col-md-offset-6 form-group">
                <label for="dtp_input2" class="col-sm-2 control-label">车辆买入时间</label>
                <div class="input-group date form_date col-sm-6" data-date="" data-date-format="MM dd yyyy"
                     data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="1" type="text" name="vehicleBuyInTime"  readonly="readonly" value="${insuredCAInfo.vehicleBuyInTime }">
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
                    <input type="text" class="form-control" id="inputEmai3l" placeholder="被保人姓名" name="insuredPeople"  readonly="readonly" value="${insuredGLInfo.insuredPeople }" >
                </div>
            </div>
            <div class="form-group col-md-offset-6">
                <label for="inputPassword" class="col-sm-2 control-label">被保人身份证号</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="inputPassword33" placeholder="被保人身份证号"  name="insuredPeopleID"  readonly="readonly" value="${insuredGLInfo.insuredPeopleID }" >
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div>
                <label for="inputEmail2" class="col-sm-1 control-label">被保人年龄</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="inputEmail32" placeholder="被保人年龄" name="insuredPeopleAge" readonly="readonly" value="${insuredGLInfo.insuredPeopleAge }">
                </div>
            </div>
            <div class="col-md-offset-6">
                <label for="inputPassword2" class="col-sm-2 control-label">赔付额上限</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="inputPassword32" placeholder="赔付额上限"  name="limitGL" readonly="readonly" value="${insuredGLInfo.limitGL }">
                </div>
            </div>
            <div class="row"></div>
            <br>
            <label for="inputPassword2" class="col-sm-1 control-label">被保人性别</label>
            <div class="col-md-offset-6 showInsuredPeopleGender hidden">
                <label for="inputPassword2" class="col-sm-2 control-label">genderhidden</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control " id="genderhidden"   name="insuredPeopleGender" readonly="readonly" value="${insuredGLInfo.insuredPeopleGender }" >
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
                    <%--<div class="col-md-offset-6 showGLLineCoverage ">--%>
                    <label for="inputPassword2" class="col-sm-2 control-label">GLLineCoveragehidden</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control " id="GLLinePeopleAccidenthidden"   name="peopleAccident" value="${insuredGLInfo.peopleAccident}"  >
                        <input type="text" class="form-control " id="GLLinePeopleIllnesshidden"   name="peopleIllness" value="${insuredGLInfo.peopleIllness}" >
                    </div>
                </div>
                <div class="form-group col-sm-3">
                    <label class="checkbox-inline">
                        <input type="checkbox" value="true"  name="peopleAccident" value="${insuredGLInfo.peopleAccident}" >人身意外险
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" value="true"  name="peopleIllness" value="${insuredGLInfo.peopleIllness}" >重疾险
                    </label>
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div>
                <label for="inputEmail4" class="col-sm-1 control-label">基本信息备注</label>
                <div class="col-sm-9">
                    <textarea type="text" class="form-control" id="inputEmail4" rows="3" placeholder="备注,最多可填100字"  readonly="readonly" name="commentCom" >${insuredInfo.commentCom }</textarea>
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div>
                <label for="inputEmail24" class="col-sm-1 control-label">车险信息备注</label>
                <div class="col-sm-9">
                    <textarea type="text" class="form-control" id="inputEmail24" rows="3" placeholder="备注,最多可填100字"  readonly="readonly" name="commentCA" >${insuredCAInfo.commentCA }</textarea>
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div>
                <label for="inputEmail34" class="col-sm-1 control-label">寿险信息备注</label>
                <div class="col-sm-9">
                    <textarea type="text" class="form-control" id="inputEmail34" rows="3" placeholder="备注,最多可填100字"  readonly="readonly" name="commentGL">${insuredGLInfo.commentGL}</textarea>
                </div>
            </div>
            <div class="row"></div>
            <br>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                        <button type="button" class="btn btn-primary" id="back">返回</button>
                </div>
            </div>
            <div class="clearfix"></div>
            <br/><br/>
        <%--</form>--%>
        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>
<script src="${pageContext.request.contextPath }/statics/localjs/appversionmodify.js"></script>
<script src="${pageContext.request.contextPath }/statics/localjs/rollpage.js"></script>
<script src="${pageContext.request.contextPath }/statics/localjs/appinfolist.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/jquery.min.js" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath }/statics/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/datepicker/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath }/statics/localjs/policyFound.js"></script>