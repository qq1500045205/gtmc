<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/common/geturl.jsp"%>
		<form>
			<table class="table table-striped">  
			<s:iterator value="#request.productOrderF"  status="sta">
			<input type="hidden" id="product_order_num" class="input-text" value="<s:property value="product_order_num"/>">
			<input type="hidden" id="orderStatus" class="input-text" value="<s:property value="orderStatusM.order_status_guid"/>">
				<tr>
					<td width="20%">商品名称 </td>
					<td width="80%" style="text-align: center;"><s:property value="productListF.productName"/></td>
				</tr>
				<tr>
					<td>商品LOGO</td>
					<td style="text-align: center;"><img id="preview" src="${ctx }<s:property value="productListF.productLogo"/>"
						style="width: 150px; height: 120px; border: 1px solid #ddd;">
					</td>
				</tr>
				<tr>
					<td>商品数量 </td>
					<td style="text-align: center;"><s:property value="product_count"/></td>
				</tr>
				<tr>
					<td>兑换积分 </td>
					<td style="text-align: center;"><s:property value="productListF.productCredit"/></td>
				</tr>
				<tr>
					<td>订单状态 </td>
					<td style="text-align: center; "><s:if test='orderStatusM.order_status_guid !="3"'>
						<select id="order_status_name" style="width: 70% " >
						<option selected="selected"  value="<s:property value="orderStatusM.order_status_guid"/>"><s:property value="orderStatusM.order_status_name"/></option>
						<s:if test='orderStatusM.order_status_guid !="1"'><option value="1" >待审核</option></s:if>
						<s:if test='orderStatusM.order_status_guid !="2"'><option value="2" >已经发货</option></s:if>
						<s:if test='orderStatusM.order_status_guid !="3"'><option value="3" >已经签收</option></s:if>
						</select>
					</s:if><s:else>
						<s:property value="orderStatusM.order_status_name"/>
					</s:else>
					</td>
				</tr>
				<tr>
					<td>商品分类</td>
					<td style="text-align: center;"><s:property value="productTypeM.productName"/></td>
				</tr>
				<tr>
					<td>订单日期</td>
					<td style="text-align: center;"><s:property value="product_order_date"/></td>
				</tr>
				<tr>
					<td>收货地址
					</td>
					<td style="text-align: center;"><textarea name="address" id="address"
							class="area-text" style="width: 100%; height: 140px; font-weight: bold;" disabled="disabled">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="userLocationFTemp.user_location_name"/> 
									    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="userLocationFTemp.user_location_provice"/>&nbsp;&nbsp; <s:property value="userLocationFTemp.user_location_city"/>&nbsp;&nbsp; <s:property value="userLocationFTemp.user_location_district"/>
          
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="userLocationFTemp.user_location_street"/>&nbsp;&nbsp;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="userLocationFTemp.user_location_phone"/>
                                   </textarea></td>
				</tr>
				</s:iterator>
			</table>
		</form>
