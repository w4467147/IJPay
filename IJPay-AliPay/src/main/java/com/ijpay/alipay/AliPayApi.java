package com.ijpay.alipay;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.alipay.api.BatchAlipayRequest;
import com.alipay.api.BatchAlipayResponse;
import com.alipay.api.domain.AlipayCommerceCityfacilitatorStationQueryModel;
import com.alipay.api.domain.AlipayCommerceCityfacilitatorVoucherBatchqueryModel;
import com.alipay.api.domain.AlipayCommerceCityfacilitatorVoucherGenerateModel;
import com.alipay.api.domain.AlipayCommerceCityfacilitatorVoucherRefundModel;
import com.alipay.api.domain.AlipayDataDataserviceBillDownloadurlQueryModel;
import com.alipay.api.domain.AlipayFundAccountQueryModel;
import com.alipay.api.domain.AlipayFundAuthOperationCancelModel;
import com.alipay.api.domain.AlipayFundAuthOperationDetailQueryModel;
import com.alipay.api.domain.AlipayFundAuthOrderFreezeModel;
import com.alipay.api.domain.AlipayFundAuthOrderUnfreezeModel;
import com.alipay.api.domain.AlipayFundAuthOrderVoucherCreateModel;
import com.alipay.api.domain.AlipayFundCouponOperationQueryModel;
import com.alipay.api.domain.AlipayFundCouponOrderAgreementPayModel;
import com.alipay.api.domain.AlipayFundCouponOrderAppPayModel;
import com.alipay.api.domain.AlipayFundCouponOrderDisburseModel;
import com.alipay.api.domain.AlipayFundCouponOrderPagePayModel;
import com.alipay.api.domain.AlipayFundCouponOrderRefundModel;
import com.alipay.api.domain.AlipayFundTransCommonQueryModel;
import com.alipay.api.domain.AlipayFundTransOrderQueryModel;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.domain.AlipayFundTransUniTransferModel;
import com.alipay.api.domain.AlipayOpenAuthTokenAppModel;
import com.alipay.api.domain.AlipayOpenAuthTokenAppQueryModel;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeCancelModel;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradeCreateModel;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradeOrderSettleModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradePageRefundModel;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeRoyaltyRelationBatchqueryModel;
import com.alipay.api.domain.AlipayTradeRoyaltyRelationBindModel;
import com.alipay.api.domain.AlipayTradeRoyaltyRelationUnbindModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.domain.ZolozAuthenticationCustomerFacemanageCreateModel;
import com.alipay.api.domain.ZolozAuthenticationCustomerFacemanageDeleteModel;
import com.alipay.api.domain.ZolozAuthenticationCustomerFtokenQueryModel;
import com.alipay.api.domain.ZolozAuthenticationCustomerSmilepayInitializeModel;
import com.alipay.api.domain.ZolozAuthenticationSmilepayInitializeModel;
import com.alipay.api.domain.ZolozIdentificationUserWebInitializeModel;
import com.alipay.api.domain.ZolozIdentificationUserWebQueryModel;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.request.AlipayCommerceAdContractSignRequest;
import com.alipay.api.request.AlipayCommerceCityfacilitatorStationQueryRequest;
import com.alipay.api.request.AlipayCommerceCityfacilitatorVoucherBatchqueryRequest;
import com.alipay.api.request.AlipayCommerceCityfacilitatorVoucherGenerateRequest;
import com.alipay.api.request.AlipayCommerceCityfacilitatorVoucherRefundRequest;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayEbppBillGetRequest;
import com.alipay.api.request.AlipayFundAccountQueryRequest;
import com.alipay.api.request.AlipayFundAuthOperationCancelRequest;
import com.alipay.api.request.AlipayFundAuthOperationDetailQueryRequest;
import com.alipay.api.request.AlipayFundAuthOrderFreezeRequest;
import com.alipay.api.request.AlipayFundAuthOrderUnfreezeRequest;
import com.alipay.api.request.AlipayFundAuthOrderVoucherCreateRequest;
import com.alipay.api.request.AlipayFundCouponOperationQueryRequest;
import com.alipay.api.request.AlipayFundCouponOrderAgreementPayRequest;
import com.alipay.api.request.AlipayFundCouponOrderAppPayRequest;
import com.alipay.api.request.AlipayFundCouponOrderDisburseRequest;
import com.alipay.api.request.AlipayFundCouponOrderPagePayRequest;
import com.alipay.api.request.AlipayFundCouponOrderRefundRequest;
import com.alipay.api.request.AlipayFundTransCommonQueryRequest;
import com.alipay.api.request.AlipayFundTransOrderQueryRequest;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayFundTransUniTransferRequest;
import com.alipay.api.request.AlipayOpenAuthTokenAppQueryRequest;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeOrderSettleRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePageRefundRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeRoyaltyRelationBatchqueryRequest;
import com.alipay.api.request.AlipayTradeRoyaltyRelationBindRequest;
import com.alipay.api.request.AlipayTradeRoyaltyRelationUnbindRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.request.ZolozAuthenticationCustomerFacemanageCreateRequest;
import com.alipay.api.request.ZolozAuthenticationCustomerFacemanageDeleteRequest;
import com.alipay.api.request.ZolozAuthenticationCustomerFtokenQueryRequest;
import com.alipay.api.request.ZolozAuthenticationCustomerSmilepayInitializeRequest;
import com.alipay.api.request.ZolozAuthenticationSmilepayInitializeRequest;
import com.alipay.api.request.ZolozIdentificationUserWebInitializeRequest;
import com.alipay.api.request.ZolozIdentificationUserWebQueryRequest;
import com.alipay.api.response.AlipayCommerceAdContractSignResponse;
import com.alipay.api.response.AlipayCommerceCityfacilitatorStationQueryResponse;
import com.alipay.api.response.AlipayCommerceCityfacilitatorVoucherBatchqueryResponse;
import com.alipay.api.response.AlipayCommerceCityfacilitatorVoucherGenerateResponse;
import com.alipay.api.response.AlipayCommerceCityfacilitatorVoucherRefundResponse;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayEbppBillGetResponse;
import com.alipay.api.response.AlipayFundAccountQueryResponse;
import com.alipay.api.response.AlipayFundAuthOperationCancelResponse;
import com.alipay.api.response.AlipayFundAuthOperationDetailQueryResponse;
import com.alipay.api.response.AlipayFundAuthOrderFreezeResponse;
import com.alipay.api.response.AlipayFundAuthOrderUnfreezeResponse;
import com.alipay.api.response.AlipayFundAuthOrderVoucherCreateResponse;
import com.alipay.api.response.AlipayFundCouponOperationQueryResponse;
import com.alipay.api.response.AlipayFundCouponOrderAgreementPayResponse;
import com.alipay.api.response.AlipayFundCouponOrderAppPayResponse;
import com.alipay.api.response.AlipayFundCouponOrderDisburseResponse;
import com.alipay.api.response.AlipayFundCouponOrderPagePayResponse;
import com.alipay.api.response.AlipayFundCouponOrderRefundResponse;
import com.alipay.api.response.AlipayFundTransCommonQueryResponse;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.alipay.api.response.AlipayOpenAuthTokenAppQueryResponse;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeOrderSettleResponse;
import com.alipay.api.response.AlipayTradePageRefundResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.api.response.AlipayTradeRoyaltyRelationBatchqueryResponse;
import com.alipay.api.response.AlipayTradeRoyaltyRelationBindResponse;
import com.alipay.api.response.AlipayTradeRoyaltyRelationUnbindResponse;
import com.alipay.api.response.ZolozAuthenticationCustomerFacemanageCreateResponse;
import com.alipay.api.response.ZolozAuthenticationCustomerFacemanageDeleteResponse;
import com.alipay.api.response.ZolozAuthenticationCustomerFtokenQueryResponse;
import com.alipay.api.response.ZolozAuthenticationCustomerSmilepayInitializeResponse;
import com.alipay.api.response.ZolozAuthenticationSmilepayInitializeResponse;
import com.alipay.api.response.ZolozIdentificationUserWebInitializeResponse;
import com.alipay.api.response.ZolozIdentificationUserWebQueryResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>
 * IJPay ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
 * </p>
 *
 * <p>
 * ???????????????????????? mvc ???????????????????????????????????????????????????????????????????????????????????????????????????????????????
 * </p>
 *
 * <p>
 * IJPay ?????????: 723992875???864988890
 * </p>
 *
 * <p>
 * Node.js ???: https://gitee.com/javen205/TNWX
 * </p>
 *
 * <p>
 * ???????????????????????????
 * </p>
 *
 * @author Javen
 */
public class AliPayApi {

	/**
	 * ?????????????????????????????????????????????URL(???)
	 */
	private static final String GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";

	public static <T extends AlipayResponse> T doExecute(AlipayRequest<T> request) throws AlipayApiException {
		if (AliPayApiConfigKit.getAliPayApiConfig().isCertModel()) {
			return certificateExecute(request);
		} else {
			return execute(request);
		}
	}

	public static <T extends AlipayResponse> T doExecute(AlipayClient alipayClient, Boolean certModel, AlipayRequest<T> request) throws AlipayApiException {
		if (alipayClient == null) {
			throw new IllegalStateException("aliPayClient ???????????????");
		}
		if (certModel) {
			return certificateExecute(alipayClient, request);
		} else {
			return execute(alipayClient, request);
		}
	}


	public static <T extends AlipayResponse> T doExecute(AlipayClient alipayClient, Boolean certModel, AlipayRequest<T> request, String authToken) throws AlipayApiException {
		if (alipayClient == null) {
			throw new IllegalStateException("aliPayClient ???????????????");
		}
		if (certModel) {
			return certificateExecute(alipayClient, request, authToken);
		} else {
			return execute(alipayClient, request, authToken);
		}
	}


	public static <T extends AlipayResponse> T doExecute(AlipayRequest<T> request, String authToken) throws AlipayApiException {
		if (AliPayApiConfigKit.getAliPayApiConfig().isCertModel()) {
			return certificateExecute(request, authToken);
		} else {
			return execute(request, authToken);
		}
	}


	public static <T extends AlipayResponse> T doExecute(AlipayClient alipayClient, AlipayRequest<T> request, String authToken) throws AlipayApiException {
		if (alipayClient == null) {
			throw new IllegalStateException("aliPayClient ???????????????");
		}
		if (AliPayApiConfigKit.getAliPayApiConfig().isCertModel()) {
			return certificateExecute(alipayClient, request, authToken);
		} else {
			return execute(alipayClient, request, authToken);
		}
	}

	public static <T extends AlipayResponse> T execute(AlipayRequest<T> request) throws AlipayApiException {
		return AliPayApiConfigKit.getAliPayApiConfig().getAliPayClient().execute(request);
	}


	public static <T extends AlipayResponse> T execute(AlipayClient alipayClient, AlipayRequest<T> request) throws AlipayApiException {
		if (alipayClient == null) {
			throw new IllegalStateException("aliPayClient ???????????????");
		}
		return alipayClient.execute(request);
	}

	public static <T extends AlipayResponse> T execute(AlipayRequest<T> request, String authToken) throws AlipayApiException {
		return AliPayApiConfigKit.getAliPayApiConfig().getAliPayClient().execute(request, authToken);
	}

	public static <T extends AlipayResponse> T execute(AlipayClient alipayClient, AlipayRequest<T> request, String authToken) throws AlipayApiException {
		if (alipayClient == null) {
			throw new IllegalStateException("aliPayClient ???????????????");
		}
		return alipayClient.execute(request, authToken);
	}

	public static <T extends AlipayResponse> T execute(AlipayRequest<T> request, String accessToken, String appAuthToken) throws AlipayApiException {
		return AliPayApiConfigKit.getAliPayApiConfig().getAliPayClient().execute(request, accessToken, appAuthToken);
	}

	public static <T extends AlipayResponse> T execute(AlipayClient alipayClient, AlipayRequest<T> request, String accessToken, String appAuthToken) throws AlipayApiException {
		if (alipayClient == null) {
			throw new IllegalStateException("aliPayClient ???????????????");
		}
		return alipayClient.execute(request, accessToken, appAuthToken);
	}

	public static <T extends AlipayResponse> T execute(AlipayRequest<T> request, String accessToken, String appAuthToken, String targetAppId) throws AlipayApiException {
		return AliPayApiConfigKit.getAliPayApiConfig().getAliPayClient().execute(request, accessToken, appAuthToken, targetAppId);
	}

	public static <T extends AlipayResponse> T execute(AlipayClient alipayClient, AlipayRequest<T> request, String accessToken, String appAuthToken, String targetAppId) throws AlipayApiException {
		if (alipayClient == null) {
			throw new IllegalStateException("aliPayClient ???????????????");
		}
		return alipayClient.execute(request, accessToken, appAuthToken, targetAppId);
	}

	public static <T extends AlipayResponse> T pageExecute(AlipayRequest<T> request) throws AlipayApiException {
		return AliPayApiConfigKit.getAliPayApiConfig().getAliPayClient().pageExecute(request);
	}

	public static <T extends AlipayResponse> T pageExecute(AlipayClient alipayClient, AlipayRequest<T> request) throws AlipayApiException {
		if (alipayClient == null) {
			throw new IllegalStateException("aliPayClient ???????????????");
		}
		return alipayClient.pageExecute(request);
	}

	public static <T extends AlipayResponse> T pageExecute(AlipayRequest<T> request, String method) throws AlipayApiException {
		return AliPayApiConfigKit.getAliPayApiConfig().getAliPayClient().pageExecute(request, method);
	}

	public static <T extends AlipayResponse> T pageExecute(AlipayClient alipayClient, AlipayRequest<T> request, String method) throws AlipayApiException {
		if (alipayClient == null) {
			throw new IllegalStateException("aliPayClient ???????????????");
		}
		return alipayClient.pageExecute(request, method);
	}

	public static <T extends AlipayResponse> T sdkExecute(AlipayRequest<T> request) throws AlipayApiException {
		return AliPayApiConfigKit.getAliPayApiConfig().getAliPayClient().sdkExecute(request);
	}

	public static <T extends AlipayResponse> T sdkExecute(AlipayClient alipayClient, AlipayRequest<T> request) throws AlipayApiException {
		if (alipayClient == null) {
			throw new IllegalStateException("aliPayClient ???????????????");
		}
		return alipayClient.sdkExecute(request);
	}

	public static BatchAlipayResponse execute(BatchAlipayRequest request) throws AlipayApiException {
		return AliPayApiConfigKit.getAliPayApiConfig().getAliPayClient().execute(request);
	}

	public static BatchAlipayResponse execute(AlipayClient alipayClient, BatchAlipayRequest request) throws AlipayApiException {
		if (alipayClient == null) {
			throw new IllegalStateException("aliPayClient ???????????????");
		}
		return alipayClient.execute(request);
	}

	public static <T extends AlipayResponse> T certificateExecute(AlipayRequest<T> request) throws AlipayApiException {
		return AliPayApiConfigKit.getAliPayApiConfig().getAliPayClient().certificateExecute(request);
	}

	public static <T extends AlipayResponse> T certificateExecute(AlipayClient alipayClient, AlipayRequest<T> request) throws AlipayApiException {
		if (alipayClient == null) {
			throw new IllegalStateException("aliPayClient ???????????????");
		}
		return alipayClient.certificateExecute(request);
	}

	public static <T extends AlipayResponse> T certificateExecute(AlipayRequest<T> request, String authToken) throws AlipayApiException {
		return AliPayApiConfigKit.getAliPayApiConfig().getAliPayClient().certificateExecute(request, authToken);
	}

	public static <T extends AlipayResponse> T certificateExecute(AlipayClient alipayClient, AlipayRequest<T> request, String authToken) throws AlipayApiException {
		if (alipayClient == null) {
			throw new IllegalStateException("aliPayClient ???????????????");
		}
		return alipayClient.certificateExecute(request, authToken);
	}

	public static <T extends AlipayResponse> T certificateExecute(AlipayRequest<T> request, String accessToken, String appAuthToken) throws AlipayApiException {
		return AliPayApiConfigKit.getAliPayApiConfig().getAliPayClient().certificateExecute(request, accessToken, appAuthToken);
	}

	public static <T extends AlipayResponse> T certificateExecute(AlipayClient alipayClient, AlipayRequest<T> request, String accessToken, String appAuthToken) throws AlipayApiException {
		if (alipayClient == null) {
			throw new IllegalStateException("aliPayClient ???????????????");
		}
		return alipayClient.certificateExecute(request, accessToken, appAuthToken);
	}

	public static <T extends AlipayResponse> T certificateExecute(AlipayRequest<T> request, String accessToken, String appAuthToken, String targetAppId) throws AlipayApiException {
		return AliPayApiConfigKit.getAliPayApiConfig().getAliPayClient().certificateExecute(request, accessToken, appAuthToken, targetAppId);
	}

	public static <T extends AlipayResponse> T certificateExecute(AlipayClient alipayClient, AlipayRequest<T> request, String accessToken, String appAuthToken, String targetAppId)
		throws AlipayApiException {
		if (alipayClient == null) {
			throw new IllegalStateException("aliPayClient ???????????????");
		}
		return alipayClient.certificateExecute(request, accessToken, appAuthToken, targetAppId);
	}

	/**
	 * APP??????
	 *
	 * @param model     {@link AlipayTradeAppPayModel}
	 * @param notifyUrl ???????????? URL
	 * @return {@link AlipayTradeAppPayResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeAppPayResponse appPayToResponse(AlipayTradeAppPayModel model, String notifyUrl) throws AlipayApiException {
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		return sdkExecute(request);
	}


	/**
	 * APP??????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param model        {@link AlipayTradeAppPayModel}
	 * @param notifyUrl    ???????????? URL
	 * @return {@link AlipayTradeAppPayResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeAppPayResponse appPayToResponse(AlipayClient alipayClient, AlipayTradeAppPayModel model, String notifyUrl) throws AlipayApiException {
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		return sdkExecute(alipayClient, request);
	}

	/**
	 * APP??????
	 *
	 * @param model        {@link AlipayTradeAppPayModel}
	 * @param notifyUrl    ???????????? URL
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradeAppPayResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeAppPayResponse appPayToResponse(AlipayTradeAppPayModel model, String notifyUrl, String appAuthToken) throws AlipayApiException {
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		request.putOtherTextParam("app_auth_token", appAuthToken);
		return sdkExecute(request);
	}


	/**
	 * APP??????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param model        {@link AlipayTradeAppPayModel}
	 * @param notifyUrl    ???????????? URL
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradeAppPayResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeAppPayResponse appPayToResponse(AlipayClient alipayClient, AlipayTradeAppPayModel model, String notifyUrl, String appAuthToken) throws AlipayApiException {
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		request.putOtherTextParam("app_auth_token", appAuthToken);
		return sdkExecute(alipayClient, request);
	}

	/**
	 * WAP??????
	 *
	 * @param response  {@link HttpServletResponse}
	 * @param model     {@link AlipayTradeWapPayModel}
	 * @param returnUrl ????????????URL
	 * @param notifyUrl ????????????URL
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void wapPay(HttpServletResponse response, AlipayTradeWapPayModel model, String returnUrl, String notifyUrl) throws AlipayApiException, IOException {
		String form = wapPayStr(model, returnUrl, notifyUrl);
		response.setContentType("text/html;charset=" + AliPayApiConfigKit.getAliPayApiConfig().getCharset());
		PrintWriter out = response.getWriter();
		out.write(form);
		out.flush();
		out.close();
	}


	/**
	 * WAP??????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param response     {@link HttpServletResponse}
	 * @param model        {@link AlipayTradeWapPayModel}
	 * @param returnUrl    ????????????URL
	 * @param notifyUrl    ????????????URL
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void wapPay(AlipayClient alipayClient, HttpServletResponse response, AlipayTradeWapPayModel model, String returnUrl, String notifyUrl) throws AlipayApiException, IOException {
		String form = wapPayStr(alipayClient, model, returnUrl, notifyUrl);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(form);
		out.flush();
		out.close();
	}


	/**
	 * WAP??????
	 *
	 * @param response     {@link HttpServletResponse}
	 * @param model        {@link AlipayTradeWapPayModel}
	 * @param returnUrl    ????????????URL
	 * @param notifyUrl    ????????????URL
	 * @param appAuthToken ????????????token
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void wapPay(HttpServletResponse response, AlipayTradeWapPayModel model, String returnUrl, String notifyUrl, String appAuthToken) throws AlipayApiException, IOException {
		String form = wapPayStr(model, returnUrl, notifyUrl, appAuthToken);
		response.setContentType("text/html;charset=" + AliPayApiConfigKit.getAliPayApiConfig().getCharset());
		PrintWriter out = response.getWriter();
		out.write(form);
		out.flush();
		out.close();
	}


	/**
	 * WAP??????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param response     {@link HttpServletResponse}
	 * @param model        {@link AlipayTradeWapPayModel}
	 * @param returnUrl    ????????????URL
	 * @param notifyUrl    ????????????URL
	 * @param appAuthToken ????????????token
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void wapPay(AlipayClient alipayClient, HttpServletResponse response, AlipayTradeWapPayModel model, String returnUrl, String notifyUrl, String appAuthToken) throws AlipayApiException, IOException {
		String form = wapPayStr(alipayClient, model, returnUrl, notifyUrl, appAuthToken);
		String charset = "UTF-8";
		response.setContentType("text/html;charset=" + charset);
		PrintWriter out = response.getWriter();
		out.write(form);
		out.flush();
		out.close();
	}

	/**
	 * <p>
	 * WAP??????
	 * </p>
	 *
	 * <p>
	 * ???????????? Filter ????????? OutputStream getOutputStream() ??? PrintWriter getWriter() ??????????????????
	 * </p>
	 *
	 * @param response     {@link HttpServletResponse}
	 * @param model        {@link AlipayTradeWapPayModel}
	 * @param returnUrl    ????????????URL
	 * @param notifyUrl    ????????????URL
	 * @param appAuthToken ????????????token
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void wapPayByOutputStream(HttpServletResponse response, AlipayTradeWapPayModel model, String returnUrl, String notifyUrl, String appAuthToken)
		throws AlipayApiException, IOException {
		String form = wapPayStr(model, returnUrl, notifyUrl, appAuthToken);
		response.setContentType("text/html;charset=" + AliPayApiConfigKit.getAliPayApiConfig().getCharset());
		OutputStream out = response.getOutputStream();
		out.write(form.getBytes(AliPayApiConfigKit.getAliPayApiConfig().getCharset()));
		response.getOutputStream().flush();
	}


	/**
	 * <p>
	 * WAP??????
	 * </p>
	 *
	 * <p>
	 * ???????????? Filter ????????? OutputStream getOutputStream() ??? PrintWriter getWriter() ??????????????????
	 * </p>
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param response     {@link HttpServletResponse}
	 * @param model        {@link AlipayTradeWapPayModel}
	 * @param returnUrl    ????????????URL
	 * @param notifyUrl    ????????????URL
	 * @param appAuthToken ????????????token
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void wapPayByOutputStream(AlipayClient alipayClient, HttpServletResponse response, AlipayTradeWapPayModel model, String returnUrl, String notifyUrl, String appAuthToken)
		throws AlipayApiException, IOException {
		String form = wapPayStr(alipayClient, model, returnUrl, notifyUrl, appAuthToken);
		String charset = "UTF-8";
		response.setContentType("text/html;charset=" + charset);
		OutputStream out = response.getOutputStream();
		out.write(form.getBytes(charset));
		response.getOutputStream().flush();
	}

	/**
	 * <p>
	 * WAP??????
	 * </p>
	 *
	 * <p>
	 * ???????????? Filter ????????? OutputStream getOutputStream() ??? PrintWriter getWriter() ??????????????????
	 * </p>
	 *
	 * @param response  {@link HttpServletResponse}
	 * @param model     {@link AlipayTradeWapPayModel}
	 * @param returnUrl ????????????URL
	 * @param notifyUrl ????????????URL
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void wapPayByOutputStream(HttpServletResponse response, AlipayTradeWapPayModel model, String returnUrl, String notifyUrl) throws AlipayApiException, IOException {
		String form = wapPayStr(model, returnUrl, notifyUrl);
		response.setContentType("text/html;charset=" + AliPayApiConfigKit.getAliPayApiConfig().getCharset());
		OutputStream out = response.getOutputStream();
		out.write(form.getBytes(AliPayApiConfigKit.getAliPayApiConfig().getCharset()));
		response.getOutputStream().flush();
	}


	/**
	 * <p>
	 * WAP??????
	 * </p>
	 *
	 * <p>
	 * ???????????? Filter ????????? OutputStream getOutputStream() ??? PrintWriter getWriter() ??????????????????
	 * </p>
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param response     {@link HttpServletResponse}
	 * @param model        {@link AlipayTradeWapPayModel}
	 * @param returnUrl    ????????????URL
	 * @param notifyUrl    ????????????URL
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void wapPayByOutputStream(AlipayClient alipayClient, HttpServletResponse response, AlipayTradeWapPayModel model, String returnUrl, String notifyUrl) throws AlipayApiException, IOException {
		String form = wapPayStr(alipayClient, model, returnUrl, notifyUrl);
		String charset = "UTF-8";
		response.setContentType("text/html;charset=" + charset);
		OutputStream out = response.getOutputStream();
		out.write(form.getBytes(charset));
		response.getOutputStream().flush();
	}

	/**
	 * WAP??????
	 *
	 * @param model     {@link AlipayTradeWapPayModel}
	 * @param returnUrl ????????????URL
	 * @param notifyUrl ????????????URL
	 * @return {String}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static String wapPayStr(AlipayTradeWapPayModel model, String returnUrl, String notifyUrl) throws AlipayApiException {
		AlipayTradeWapPayRequest aliPayRequest = new AlipayTradeWapPayRequest();
		aliPayRequest.setReturnUrl(returnUrl);
		aliPayRequest.setNotifyUrl(notifyUrl);
		aliPayRequest.setBizModel(model);
		return pageExecute(aliPayRequest).getBody();
	}

	/**
	 * WAP??????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param model        {@link AlipayTradeWapPayModel}
	 * @param returnUrl    ????????????URL
	 * @param notifyUrl    ????????????URL
	 * @return {String}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static String wapPayStr(AlipayClient alipayClient, AlipayTradeWapPayModel model, String returnUrl, String notifyUrl) throws AlipayApiException {
		AlipayTradeWapPayRequest aliPayRequest = new AlipayTradeWapPayRequest();
		aliPayRequest.setReturnUrl(returnUrl);
		aliPayRequest.setNotifyUrl(notifyUrl);
		aliPayRequest.setBizModel(model);
		return pageExecute(alipayClient, aliPayRequest).getBody();
	}

	/**
	 * WAP??????
	 *
	 * @param model        {@link AlipayTradeWapPayModel}
	 * @param returnUrl    ????????????URL
	 * @param notifyUrl    ????????????URL
	 * @param appAuthToken ????????????token
	 * @return {String}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static String wapPayStr(AlipayTradeWapPayModel model, String returnUrl, String notifyUrl, String appAuthToken) throws AlipayApiException {
		AlipayTradeWapPayRequest aliPayRequest = new AlipayTradeWapPayRequest();
		aliPayRequest.setReturnUrl(returnUrl);
		aliPayRequest.setNotifyUrl(notifyUrl);
		aliPayRequest.setBizModel(model);
		aliPayRequest.putOtherTextParam("app_auth_token", appAuthToken);
		return pageExecute(aliPayRequest).getBody();
	}

	/**
	 * WAP??????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param model        {@link AlipayTradeWapPayModel}
	 * @param returnUrl    ????????????URL
	 * @param notifyUrl    ????????????URL
	 * @param appAuthToken ????????????token
	 * @return {String}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static String wapPayStr(AlipayClient alipayClient, AlipayTradeWapPayModel model, String returnUrl, String notifyUrl, String appAuthToken) throws AlipayApiException {
		AlipayTradeWapPayRequest aliPayRequest = new AlipayTradeWapPayRequest();
		aliPayRequest.setReturnUrl(returnUrl);
		aliPayRequest.setNotifyUrl(notifyUrl);
		aliPayRequest.setBizModel(model);
		aliPayRequest.putOtherTextParam("app_auth_token", appAuthToken);
		return pageExecute(alipayClient, aliPayRequest).getBody();
	}

	/**
	 * ???????????????????????????????????? <br>
	 * ?????????:????????????????????????????????? <br>
	 *
	 * @param model     {@link AlipayTradePayModel}
	 * @param notifyUrl ????????????URL
	 * @return {@link AlipayTradePayResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradePayResponse tradePayToResponse(AlipayTradePayModel model, String notifyUrl) throws AlipayApiException {
		AlipayTradePayRequest request = new AlipayTradePayRequest();
		// ??????????????????
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		return doExecute(request);
	}

	/**
	 * ???????????????????????????????????? <br>
	 * ?????????:????????????????????????????????? <br>
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayTradePayModel}
	 * @param notifyUrl    ????????????URL
	 * @return {@link AlipayTradePayResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradePayResponse tradePayToResponse(AlipayClient alipayClient, Boolean certModel, AlipayTradePayModel model, String notifyUrl) throws AlipayApiException {
		AlipayTradePayRequest request = new AlipayTradePayRequest();
		// ??????????????????
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ???????????????????????????????????? <br>
	 * ?????????:????????????????????????????????? <br>
	 *
	 * @param model        {AlipayTradePayModel}
	 * @param notifyUrl    ????????????URL
	 * @param appAuthToken ????????????token
	 * @return {AlipayTradePayResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradePayResponse tradePayToResponse(AlipayTradePayModel model, String notifyUrl, String appAuthToken) throws AlipayApiException {
		AlipayTradePayRequest request = new AlipayTradePayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		request.putOtherTextParam("app_auth_token", appAuthToken);
		return doExecute(request);
	}

	/**
	 * ???????????????????????????????????? <br>
	 * ?????????:????????????????????????????????? <br>
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {AlipayTradePayModel}
	 * @param notifyUrl    ????????????URL
	 * @param appAuthToken ????????????token
	 * @return {AlipayTradePayResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradePayResponse tradePayToResponse(AlipayClient alipayClient, Boolean certModel, AlipayTradePayModel model, String notifyUrl, String appAuthToken) throws AlipayApiException {
		AlipayTradePayRequest request = new AlipayTradePayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		request.putOtherTextParam("app_auth_token", appAuthToken);
		return doExecute(alipayClient, certModel, request);
	}


	/**
	 * ????????????????????????????????? <br>
	 * ??????????????????????????? <br>
	 *
	 * @param model     {@link AlipayTradePrecreateModel}
	 * @param notifyUrl ????????????URL
	 * @return {@link AlipayTradePrecreateResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradePrecreateResponse tradePrecreatePayToResponse(AlipayTradePrecreateModel model, String notifyUrl) throws AlipayApiException {
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		return doExecute(request);
	}


	/**
	 * ????????????????????????????????? <br>
	 * ??????????????????????????? <br>
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayTradePrecreateModel}
	 * @param notifyUrl    ????????????URL
	 * @return {@link AlipayTradePrecreateResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradePrecreateResponse tradePrecreatePayToResponse(AlipayClient alipayClient, Boolean certModel, AlipayTradePrecreateModel model, String notifyUrl) throws AlipayApiException {
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		return doExecute(alipayClient, certModel, request);
	}


	/**
	 * ????????????????????????????????? <br>
	 * ??????????????????????????? <br>
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayTradePrecreateModel}
	 * @param notifyUrl    ????????????URL
	 * @return {@link AlipayTradePrecreateResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradePrecreateResponse tradePrecreatePayToResponse(AlipayClient alipayClient, Boolean certModel, AlipayTradePrecreateModel model, String notifyUrl, String appAuthToken) throws AlipayApiException {
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		return doExecute(alipayClient, certModel, request, appAuthToken);
	}

	/**
	 * ????????????????????????????????? <br>
	 * ??????????????????????????? <br>
	 *
	 * @param model        {@link AlipayTradePrecreateModel}
	 * @param notifyUrl    ????????????URL
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradePrecreateResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradePrecreateResponse tradePrecreatePayToResponse(AlipayTradePrecreateModel model, String notifyUrl, String appAuthToken) throws AlipayApiException {
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		return execute(request, null, appAuthToken);
	}

	/**
	 * ????????????????????????????????? <br>
	 * ??????????????????????????? <br>
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param model        {@link AlipayTradePrecreateModel}
	 * @param notifyUrl    ????????????URL
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradePrecreateResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradePrecreateResponse tradePrecreatePayToResponse(AlipayClient alipayClient, AlipayTradePrecreateModel model, String notifyUrl, String appAuthToken) throws AlipayApiException {
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		return execute(alipayClient, request, null, appAuthToken);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model {@link AlipayFundTransToaccountTransferModel}
	 * @return ??????????????????
	 * @throws AlipayApiException ????????? Api ??????
	 */
	@Deprecated
	public static boolean transfer(AlipayFundTransToaccountTransferModel model) throws AlipayApiException {
		AlipayFundTransToaccountTransferResponse response = transferToResponse(model);
		String result = response.getBody();
		if (response.isSuccess()) {
			return true;
		} else {
			// ??????????????????????????????
			JSONObject jsonObject = JSONObject.parseObject(result);
			String outBizNo = jsonObject.getJSONObject("alipay_fund_trans_toaccount_transfer_response").getString("out_biz_no");
			AlipayFundTransOrderQueryModel queryModel = new AlipayFundTransOrderQueryModel();
			model.setOutBizNo(outBizNo);
			return transferQuery(queryModel);
		}
	}


	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayFundTransToaccountTransferModel}
	 * @return ??????????????????
	 * @throws AlipayApiException ????????? Api ??????
	 */
	@Deprecated
	public static boolean transfer(AlipayClient alipayClient, Boolean certModel, AlipayFundTransToaccountTransferModel model) throws AlipayApiException {
		AlipayFundTransToaccountTransferResponse response = transferToResponse(model);
		String result = response.getBody();
		if (response.isSuccess()) {
			return true;
		} else {
			// ??????????????????????????????
			JSONObject jsonObject = JSONObject.parseObject(result);
			String outBizNo = jsonObject.getJSONObject("alipay_fund_trans_toaccount_transfer_response").getString("out_biz_no");
			AlipayFundTransOrderQueryModel queryModel = new AlipayFundTransOrderQueryModel();
			model.setOutBizNo(outBizNo);
			return transferQuery(alipayClient, certModel, queryModel);
		}
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model {@link AlipayFundTransToaccountTransferModel}
	 * @return {@link AlipayFundTransToaccountTransferResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundTransToaccountTransferResponse transferToResponse(AlipayFundTransToaccountTransferModel model) throws AlipayApiException {
		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		request.setBizModel(model);
		return doExecute(request);
	}


	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayFundTransToaccountTransferModel}
	 * @return {@link AlipayFundTransToaccountTransferResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundTransToaccountTransferResponse transferToResponse(AlipayClient alipayClient, Boolean certModel, AlipayFundTransToaccountTransferModel model) throws AlipayApiException {
		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????
	 *
	 * @param model {@link AlipayFundTransOrderQueryModel}
	 * @return ???????????????
	 * @throws AlipayApiException ????????? Api ??????
	 */
	@Deprecated
	public static boolean transferQuery(AlipayFundTransOrderQueryModel model) throws AlipayApiException {
		AlipayFundTransOrderQueryResponse response = transferQueryToResponse(model);
		return response.isSuccess();
	}

	/**
	 * ??????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayFundTransOrderQueryModel}
	 * @return ???????????????
	 * @throws AlipayApiException ????????? Api ??????
	 */
	@Deprecated
	public static boolean transferQuery(AlipayClient alipayClient, Boolean certModel, AlipayFundTransOrderQueryModel model) throws AlipayApiException {
		AlipayFundTransOrderQueryResponse response = transferQueryToResponse(alipayClient, certModel, model);
		return response.isSuccess();
	}

	/**
	 * ??????????????????
	 *
	 * @param model {@link AlipayFundTransOrderQueryModel}
	 * @return {@link AlipayFundTransOrderQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundTransOrderQueryResponse transferQueryToResponse(AlipayFundTransOrderQueryModel model) throws AlipayApiException {
		AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ??????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayFundTransOrderQueryModel}
	 * @return {@link AlipayFundTransOrderQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundTransOrderQueryResponse transferQueryToResponse(AlipayClient alipayClient, Boolean certModel, AlipayFundTransOrderQueryModel model) throws AlipayApiException {
		AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????
	 *
	 * @param model        model {@link AlipayFundTransUniTransferModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayFundTransUniTransferResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundTransUniTransferResponse uniTransferToResponse(AlipayFundTransUniTransferModel model, String appAuthToken) throws AlipayApiException {
		AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
		request.setBizModel(model);
		if (!StringUtils.isEmpty(appAuthToken)) {
			request.putOtherTextParam("app_auth_token", appAuthToken);
		}
		return doExecute(request);
	}

	/**
	 * ??????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        model {@link AlipayFundTransUniTransferModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayFundTransUniTransferResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundTransUniTransferResponse uniTransferToResponse(AlipayClient alipayClient, Boolean certModel, AlipayFundTransUniTransferModel model, String appAuthToken) throws AlipayApiException {
		AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
		request.setBizModel(model);
		if (!StringUtils.isEmpty(appAuthToken)) {
			request.putOtherTextParam("app_auth_token", appAuthToken);
		}
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model        model {@link AlipayFundTransCommonQueryModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayFundTransCommonQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundTransCommonQueryResponse transCommonQueryToResponse(AlipayFundTransCommonQueryModel model, String appAuthToken) throws AlipayApiException {
		AlipayFundTransCommonQueryRequest request = new AlipayFundTransCommonQueryRequest();
		request.setBizModel(model);
		if (!StringUtils.isEmpty(appAuthToken)) {
			request.putOtherTextParam("app_auth_token", appAuthToken);
		}
		return doExecute(request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        model {@link AlipayFundTransCommonQueryModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayFundTransCommonQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundTransCommonQueryResponse transCommonQueryToResponse(AlipayClient alipayClient, Boolean certModel, AlipayFundTransCommonQueryModel model, String appAuthToken) throws AlipayApiException {
		AlipayFundTransCommonQueryRequest request = new AlipayFundTransCommonQueryRequest();
		request.setBizModel(model);
		if (!StringUtils.isEmpty(appAuthToken)) {
			request.putOtherTextParam("app_auth_token", appAuthToken);
		}
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ???????????????????????????????????????
	 *
	 * @param model        model {@link AlipayFundAccountQueryModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayFundAccountQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundAccountQueryResponse accountQueryToResponse(AlipayFundAccountQueryModel model, String appAuthToken) throws AlipayApiException {
		AlipayFundAccountQueryRequest request = new AlipayFundAccountQueryRequest();
		request.setBizModel(model);
		if (!StringUtils.isEmpty(appAuthToken)) {
			request.putOtherTextParam("app_auth_token", appAuthToken);
		}
		return doExecute(request);
	}


	/**
	 * ???????????????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        model {@link AlipayFundAccountQueryModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayFundAccountQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundAccountQueryResponse accountQueryToResponse(AlipayClient alipayClient, Boolean certModel, AlipayFundAccountQueryModel model, String appAuthToken) throws AlipayApiException {
		AlipayFundAccountQueryRequest request = new AlipayFundAccountQueryRequest();
		request.setBizModel(model);
		if (!StringUtils.isEmpty(appAuthToken)) {
			request.putOtherTextParam("app_auth_token", appAuthToken);
		}
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ????????????????????????????????????
	 *
	 * @param model {@link AlipayTradeQueryModel}
	 * @return {@link AlipayTradeQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeQueryResponse tradeQueryToResponse(AlipayTradeQueryModel model) throws AlipayApiException {
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ????????????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayTradeQueryModel}
	 * @return {@link AlipayTradeQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeQueryResponse tradeQueryToResponse(AlipayClient alipayClient, Boolean certModel, AlipayTradeQueryModel model) throws AlipayApiException {
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ????????????????????????????????????
	 *
	 * @param model        {@link AlipayTradeQueryModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradeQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeQueryResponse tradeQueryToResponse(AlipayTradeQueryModel model, String appAuthToken) throws AlipayApiException {
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizModel(model);
		return execute(request, null, appAuthToken);
	}

	/**
	 * ????????????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param model        {@link AlipayTradeQueryModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradeQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeQueryResponse tradeQueryToResponse(AlipayClient alipayClient, AlipayTradeQueryModel model, String appAuthToken) throws AlipayApiException {
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizModel(model);
		return execute(alipayClient, request, null, appAuthToken);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model        {@link AlipayTradeCancelModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradeCancelResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeCancelResponse tradeCancelToResponse(AlipayTradeCancelModel model, String appAuthToken) throws AlipayApiException {
		AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
		request.setBizModel(model);
		return execute(request, null, appAuthToken);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param model        {@link AlipayTradeCancelModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradeCancelResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeCancelResponse tradeCancelToResponse(AlipayClient alipayClient, AlipayTradeCancelModel model, String appAuthToken) throws AlipayApiException {
		AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
		request.setBizModel(model);
		return execute(alipayClient, request, null, appAuthToken);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model {@link AlipayTradeCancelModel}
	 * @return {@link AlipayTradeCancelResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeCancelResponse tradeCancelToResponse(AlipayTradeCancelModel model) throws AlipayApiException {
		AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayTradeCancelModel}
	 * @return {@link AlipayTradeCancelResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeCancelResponse tradeCancelToResponse(AlipayClient alipayClient, Boolean certModel, AlipayTradeCancelModel model) throws AlipayApiException {
		AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model        {@link AlipayTradeCloseModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradeCloseResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeCloseResponse tradeCloseToResponse(AlipayTradeCloseModel model, String appAuthToken) throws AlipayApiException {
		AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
		request.setBizModel(model);
		return execute(request, null, appAuthToken);

	}

	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param model        {@link AlipayTradeCloseModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradeCloseResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeCloseResponse tradeCloseToResponse(AlipayClient alipayClient, AlipayTradeCloseModel model, String appAuthToken) throws AlipayApiException {
		AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
		request.setBizModel(model);
		return execute(alipayClient, request, null, appAuthToken);

	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model {@link AlipayTradeCloseModel}
	 * @return {@link AlipayTradeCloseResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeCloseResponse tradeCloseToResponse(AlipayTradeCloseModel model) throws AlipayApiException {
		AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayTradeCloseModel}
	 * @return {@link AlipayTradeCloseResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeCloseResponse tradeCloseToResponse(AlipayClient alipayClient, Boolean certModel, AlipayTradeCloseModel model) throws AlipayApiException {
		AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model     {@link AlipayTradeCreateModel}
	 * @param notifyUrl ????????????URL
	 * @return {@link AlipayTradeCreateResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeCreateResponse tradeCreateToResponse(AlipayTradeCreateModel model, String notifyUrl) throws AlipayApiException {
		AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		return doExecute(request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayTradeCreateModel}
	 * @param notifyUrl    ????????????URL
	 * @return {@link AlipayTradeCreateResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeCreateResponse tradeCreateToResponse(AlipayClient alipayClient, Boolean certModel, AlipayTradeCreateModel model, String notifyUrl) throws AlipayApiException {
		AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model        {@link AlipayTradeCreateModel}
	 * @param notifyUrl    ????????????URL
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradeCreateResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeCreateResponse tradeCreateToResponse(AlipayTradeCreateModel model, String notifyUrl, String appAuthToken) throws AlipayApiException {
		AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		return execute(request, null, appAuthToken);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param model        {@link AlipayTradeCreateModel}
	 * @param notifyUrl    ????????????URL
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradeCreateResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeCreateResponse tradeCreateToResponse(AlipayClient alipayClient, AlipayTradeCreateModel model, String notifyUrl, String appAuthToken) throws AlipayApiException {
		AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		return execute(alipayClient, request, null, appAuthToken);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model {@link AlipayTradeRefundModel}
	 * @return {@link AlipayTradeRefundResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeRefundResponse tradeRefundToResponse(AlipayTradeRefundModel model) throws AlipayApiException {
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayTradeRefundModel}
	 * @return {@link AlipayTradeRefundResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeRefundResponse tradeRefundToResponse(AlipayClient alipayClient, Boolean certModel, AlipayTradeRefundModel model) throws AlipayApiException {
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model        {@link AlipayTradeRefundModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradeRefundResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeRefundResponse tradeRefundToResponse(AlipayTradeRefundModel model, String appAuthToken) throws AlipayApiException {
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizModel(model);
		return execute(request, null, appAuthToken);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param model        {@link AlipayTradeRefundModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradeRefundResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeRefundResponse tradeRefundToResponse(AlipayClient alipayClient, AlipayTradeRefundModel model, String appAuthToken) throws AlipayApiException {
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizModel(model);
		return execute(alipayClient, request, null, appAuthToken);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model {@link AlipayTradePageRefundModel}
	 * @return {@link AlipayTradePageRefundResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradePageRefundResponse tradeRefundToResponse(AlipayTradePageRefundModel model) throws AlipayApiException {
		AlipayTradePageRefundRequest request = new AlipayTradePageRefundRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayTradePageRefundModel}
	 * @return {@link AlipayTradePageRefundResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradePageRefundResponse tradeRefundToResponse(AlipayClient alipayClient, Boolean certModel, AlipayTradePageRefundModel model) throws AlipayApiException {
		AlipayTradePageRefundRequest request = new AlipayTradePageRefundRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}


	/**
	 * ??????????????????????????????
	 *
	 * @param model        {@link AlipayTradePageRefundModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradePageRefundResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradePageRefundResponse tradeRefundToResponse(AlipayTradePageRefundModel model, String appAuthToken) throws AlipayApiException {
		AlipayTradePageRefundRequest request = new AlipayTradePageRefundRequest();
		request.setBizModel(model);
		return execute(request, null, appAuthToken);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param model        {@link AlipayTradePageRefundModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradePageRefundResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradePageRefundResponse tradeRefundToResponse(AlipayClient alipayClient, AlipayTradePageRefundModel model, String appAuthToken) throws AlipayApiException {
		AlipayTradePageRefundRequest request = new AlipayTradePageRefundRequest();
		request.setBizModel(model);
		return execute(alipayClient, request, null, appAuthToken);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model {@link AlipayTradeFastpayRefundQueryModel}
	 * @return {@link AlipayTradeFastpayRefundQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeFastpayRefundQueryResponse tradeRefundQueryToResponse(AlipayTradeFastpayRefundQueryModel model) throws AlipayApiException {
		AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayTradeFastpayRefundQueryModel}
	 * @return {@link AlipayTradeFastpayRefundQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeFastpayRefundQueryResponse tradeRefundQueryToResponse(AlipayClient alipayClient, Boolean certModel, AlipayTradeFastpayRefundQueryModel model) throws AlipayApiException {
		AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model        {@link AlipayTradeFastpayRefundQueryModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradeFastpayRefundQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeFastpayRefundQueryResponse tradeRefundQueryToResponse(AlipayTradeFastpayRefundQueryModel model, String appAuthToken) throws AlipayApiException {
		AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
		request.setBizModel(model);
		return execute(request, null, appAuthToken);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param model        {@link AlipayTradeFastpayRefundQueryModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradeFastpayRefundQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeFastpayRefundQueryResponse tradeRefundQueryToResponse(AlipayClient alipayClient, AlipayTradeFastpayRefundQueryModel model, String appAuthToken) throws AlipayApiException {
		AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
		request.setBizModel(model);
		return execute(alipayClient, request, null, appAuthToken);
	}

	/**
	 * ???????????????????????????
	 *
	 * @param model {@link AlipayDataDataserviceBillDownloadurlQueryModel}
	 * @return ?????????????????????
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static String billDownloadUrlQuery(AlipayDataDataserviceBillDownloadurlQueryModel model) throws AlipayApiException {
		AlipayDataDataserviceBillDownloadurlQueryResponse response = billDownloadUrlQueryToResponse(model);
		return response.getBillDownloadUrl();
	}

	/**
	 * ???????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayDataDataserviceBillDownloadurlQueryModel}
	 * @return ?????????????????????
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static String billDownloadUrlQuery(AlipayClient alipayClient, Boolean certModel, AlipayDataDataserviceBillDownloadurlQueryModel model) throws AlipayApiException {
		AlipayDataDataserviceBillDownloadurlQueryResponse response = billDownloadUrlQueryToResponse(alipayClient, certModel, model);
		return response.getBillDownloadUrl();
	}

	/**
	 * ???????????????????????????
	 *
	 * @param model {@link AlipayDataDataserviceBillDownloadurlQueryModel}
	 * @return {@link AlipayDataDataserviceBillDownloadurlQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayDataDataserviceBillDownloadurlQueryResponse billDownloadUrlQueryToResponse(AlipayDataDataserviceBillDownloadurlQueryModel model) throws AlipayApiException {
		AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ???????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayDataDataserviceBillDownloadurlQueryModel}
	 * @return {@link AlipayDataDataserviceBillDownloadurlQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayDataDataserviceBillDownloadurlQueryResponse billDownloadUrlQueryToResponse(AlipayClient alipayClient, Boolean certModel, AlipayDataDataserviceBillDownloadurlQueryModel model) throws AlipayApiException {
		AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ???????????????????????????
	 *
	 * @param model        {@link AlipayDataDataserviceBillDownloadurlQueryModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayDataDataserviceBillDownloadurlQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayDataDataserviceBillDownloadurlQueryResponse billDownloadUrlQueryToResponse(AlipayDataDataserviceBillDownloadurlQueryModel model, String appAuthToken)
		throws AlipayApiException {
		AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
		request.setBizModel(model);
		request.putOtherTextParam("app_auth_token", appAuthToken);
		return doExecute(request);
	}

	/**
	 * ???????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayDataDataserviceBillDownloadurlQueryModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayDataDataserviceBillDownloadurlQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayDataDataserviceBillDownloadurlQueryResponse billDownloadUrlQueryToResponse(AlipayClient alipayClient, Boolean certModel, AlipayDataDataserviceBillDownloadurlQueryModel model, String appAuthToken)
		throws AlipayApiException {
		AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
		request.setBizModel(model);
		request.putOtherTextParam("app_auth_token", appAuthToken);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model        {@link AlipayTradeOrderSettleModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradeOrderSettleResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeOrderSettleResponse tradeOrderSettleToResponse(AlipayTradeOrderSettleModel model, String appAuthToken) throws AlipayApiException {
		AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
		request.setBizModel(model);
		return execute(request, null, appAuthToken);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param model        {@link AlipayTradeOrderSettleModel}
	 * @param appAuthToken ????????????token
	 * @return {@link AlipayTradeOrderSettleResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeOrderSettleResponse tradeOrderSettleToResponse(AlipayClient alipayClient, AlipayTradeOrderSettleModel model, String appAuthToken) throws AlipayApiException {
		AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
		request.setBizModel(model);
		return execute(alipayClient, request, null, appAuthToken);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model {@link AlipayTradeOrderSettleModel}
	 * @return {@link AlipayTradeOrderSettleResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeOrderSettleResponse tradeOrderSettleToResponse(AlipayTradeOrderSettleModel model) throws AlipayApiException {
		AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayTradeOrderSettleModel}
	 * @return {@link AlipayTradeOrderSettleResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeOrderSettleResponse tradeOrderSettleToResponse(AlipayClient alipayClient, Boolean certModel, AlipayTradeOrderSettleModel model) throws AlipayApiException {
		AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????(PC??????)
	 *
	 * @param response  {@link HttpServletResponse}
	 * @param model     {@link AlipayTradePagePayModel}
	 * @param notifyUrl ????????????URL
	 * @param returnUrl ????????????URL
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void tradePage(HttpServletResponse response, AlipayTradePagePayModel model, String notifyUrl, String returnUrl) throws AlipayApiException, IOException {
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		request.setReturnUrl(returnUrl);
		String form = pageExecute(request).getBody();
		response.setContentType("text/html;charset=" + AliPayApiConfigKit.getAliPayApiConfig().getCharset());
		PrintWriter out = response.getWriter();
		out.write(form);
		out.flush();
		out.close();
	}

	/**
	 * ??????????????????(PC??????)
	 *
	 * @param response  {@link HttpServletResponse}
	 * @param method    GET/POST GET ??????url,POST ?????? FORM <a href="https://opensupport.alipay.com/support/helpcenter/192/201602488772?ant_source=antsupport">????????????</a>
	 * @param model     {@link AlipayTradePagePayModel}
	 * @param notifyUrl ????????????URL
	 * @param returnUrl ????????????URL
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void tradePage(HttpServletResponse response, String method, AlipayTradePagePayModel model, String notifyUrl, String returnUrl) throws AlipayApiException, IOException {
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		request.setReturnUrl(returnUrl);
		String form = pageExecute(request, method).getBody();
		response.setContentType("text/html;charset=" + AliPayApiConfigKit.getAliPayApiConfig().getCharset());
		PrintWriter out = response.getWriter();
		out.write(form);
		out.flush();
		out.close();
	}

	/**
	 * ??????????????????(PC??????)
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param response     {@link HttpServletResponse}
	 * @param model        {@link AlipayTradePagePayModel}
	 * @param notifyUrl    ????????????URL
	 * @param returnUrl    ????????????URL
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void tradePage(AlipayClient alipayClient, HttpServletResponse response, AlipayTradePagePayModel model, String notifyUrl, String returnUrl) throws AlipayApiException, IOException {
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		request.setReturnUrl(returnUrl);
		String form = pageExecute(alipayClient, request).getBody();
		String charset = "UTF-8";
		response.setContentType("text/html;charset=" + charset);
		PrintWriter out = response.getWriter();
		out.write(form);
		out.flush();
		out.close();
	}

	/**
	 * ??????????????????(PC??????)
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param response     {@link HttpServletResponse}
	 * @param method       GET/POST GET ??????url,POST ?????? FORM <a href="https://opensupport.alipay.com/support/helpcenter/192/201602488772?ant_source=antsupport">????????????</a>
	 * @param model        {@link AlipayTradePagePayModel}
	 * @param notifyUrl    ????????????URL
	 * @param returnUrl    ????????????URL
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void tradePage(AlipayClient alipayClient, HttpServletResponse response, String method, AlipayTradePagePayModel model, String notifyUrl, String returnUrl) throws AlipayApiException, IOException {
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		request.setReturnUrl(returnUrl);
		String form = pageExecute(alipayClient, request, method).getBody();
		String charset = "UTF-8";
		response.setContentType("text/html;charset=" + charset);
		PrintWriter out = response.getWriter();
		out.write(form);
		out.flush();
		out.close();
	}


	/**
	 * ??????????????????(PC??????)
	 *
	 * @param response     {@link HttpServletResponse}
	 * @param model        {@link AlipayTradePagePayModel}
	 * @param notifyUrl    ????????????URL
	 * @param returnUrl    ????????????URL
	 * @param appAuthToken ????????????token
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void tradePage(HttpServletResponse response, AlipayTradePagePayModel model, String notifyUrl, String returnUrl, String appAuthToken) throws AlipayApiException, IOException {
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		request.setReturnUrl(returnUrl);
		request.putOtherTextParam("app_auth_token", appAuthToken);
		String form = pageExecute(request).getBody();
		response.setContentType("text/html;charset=" + AliPayApiConfigKit.getAliPayApiConfig().getCharset());
		PrintWriter out = response.getWriter();
		out.write(form);
		out.flush();
		out.close();
	}

	/**
	 * ??????????????????(PC??????)
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param response     {@link HttpServletResponse}
	 * @param model        {@link AlipayTradePagePayModel}
	 * @param notifyUrl    ????????????URL
	 * @param returnUrl    ????????????URL
	 * @param appAuthToken ????????????token
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void tradePage(AlipayClient alipayClient, HttpServletResponse response, AlipayTradePagePayModel model, String notifyUrl, String returnUrl, String appAuthToken) throws AlipayApiException, IOException {
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		request.setReturnUrl(returnUrl);
		request.putOtherTextParam("app_auth_token", appAuthToken);
		String form = pageExecute(alipayClient, request).getBody();
		String charset = "UTF-8";
		response.setContentType("text/html;charset=" + charset);
		PrintWriter out = response.getWriter();
		out.write(form);
		out.flush();
		out.close();
	}

	/**
	 * ??????????????????(PC??????)
	 *
	 * @param response  {@link HttpServletResponse}
	 * @param model     {@link AlipayTradePagePayModel}
	 * @param notifyUrl ????????????URL
	 * @param returnUrl ????????????URL
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void tradePageByOutputStream(HttpServletResponse response, AlipayTradePagePayModel model, String notifyUrl, String returnUrl) throws AlipayApiException, IOException {
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		request.setReturnUrl(returnUrl);
		String form = pageExecute(request).getBody();
		response.setContentType("text/html;charset=" + AliPayApiConfigKit.getAliPayApiConfig().getCharset());
		OutputStream out = response.getOutputStream();
		out.write(form.getBytes(AliPayApiConfigKit.getAliPayApiConfig().getCharset()));
		response.getOutputStream().flush();
	}

	/**
	 * ??????????????????(PC??????)
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param response     {@link HttpServletResponse}
	 * @param model        {@link AlipayTradePagePayModel}
	 * @param notifyUrl    ????????????URL
	 * @param returnUrl    ????????????URL
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void tradePageByOutputStream(AlipayClient alipayClient, HttpServletResponse response, AlipayTradePagePayModel model, String notifyUrl, String returnUrl) throws AlipayApiException, IOException {
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		request.setReturnUrl(returnUrl);
		String form = pageExecute(alipayClient, request).getBody();
		String charset = "UTF-8";
		response.setContentType("text/html;charset=" + charset);
		OutputStream out = response.getOutputStream();
		out.write(form.getBytes(charset));
		response.getOutputStream().flush();
	}


	/**
	 * ??????????????????(PC??????)
	 *
	 * @param response     {@link HttpServletResponse}
	 * @param model        {@link AlipayTradePagePayModel}
	 * @param notifyUrl    ????????????URL
	 * @param returnUrl    ????????????URL
	 * @param appAuthToken ????????????token
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void tradePageByOutputStream(HttpServletResponse response, AlipayTradePagePayModel model, String notifyUrl, String returnUrl, String appAuthToken)
		throws AlipayApiException, IOException {
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		request.setReturnUrl(returnUrl);
		request.putOtherTextParam("app_auth_token", appAuthToken);
		String form = pageExecute(request).getBody();
		response.setContentType("text/html;charset=" + AliPayApiConfigKit.getAliPayApiConfig().getCharset());
		OutputStream out = response.getOutputStream();
		out.write(form.getBytes(AliPayApiConfigKit.getAliPayApiConfig().getCharset()));
		response.getOutputStream().flush();
	}

	/**
	 * ??????????????????(PC??????)
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param response     {@link HttpServletResponse}
	 * @param model        {@link AlipayTradePagePayModel}
	 * @param notifyUrl    ????????????URL
	 * @param returnUrl    ????????????URL
	 * @param appAuthToken ????????????token
	 * @throws AlipayApiException ????????? Api ??????
	 * @throws IOException        IO ??????
	 */
	public static void tradePageByOutputStream(AlipayClient alipayClient, HttpServletResponse response, AlipayTradePagePayModel model, String notifyUrl, String returnUrl, String appAuthToken)
		throws AlipayApiException, IOException {
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		request.setReturnUrl(returnUrl);
		request.putOtherTextParam("app_auth_token", appAuthToken);
		String form = pageExecute(alipayClient, request).getBody();
		String charset = "UTF-8";
		response.setContentType("text/html;charset=" + charset);
		OutputStream out = response.getOutputStream();
		out.write(form.getBytes(charset));
		response.getOutputStream().flush();
	}

	/**
	 * ???????????????????????????
	 *
	 * @param model {@link AlipayFundAuthOrderFreezeModel}
	 * @return {@link AlipayFundAuthOrderFreezeResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundAuthOrderFreezeResponse authOrderFreezeToResponse(AlipayFundAuthOrderFreezeModel model) throws AlipayApiException {
		AlipayFundAuthOrderFreezeRequest request = new AlipayFundAuthOrderFreezeRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ???????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayFundAuthOrderFreezeModel}
	 * @return {@link AlipayFundAuthOrderFreezeResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundAuthOrderFreezeResponse authOrderFreezeToResponse(AlipayClient alipayClient, Boolean certModel, AlipayFundAuthOrderFreezeModel model) throws AlipayApiException {
		AlipayFundAuthOrderFreezeRequest request = new AlipayFundAuthOrderFreezeRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}


	/**
	 * ????????????????????????
	 *
	 * @param model {@link AlipayFundAuthOrderUnfreezeModel}
	 * @return {@link AlipayFundAuthOrderUnfreezeResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundAuthOrderUnfreezeResponse authOrderUnfreezeToResponse(AlipayFundAuthOrderUnfreezeModel model) throws AlipayApiException {
		AlipayFundAuthOrderUnfreezeRequest request = new AlipayFundAuthOrderUnfreezeRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayFundAuthOrderUnfreezeModel}
	 * @return {@link AlipayFundAuthOrderUnfreezeResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundAuthOrderUnfreezeResponse authOrderUnfreezeToResponse(AlipayClient alipayClient, Boolean certModel, AlipayFundAuthOrderUnfreezeModel model) throws AlipayApiException {
		AlipayFundAuthOrderUnfreezeRequest request = new AlipayFundAuthOrderUnfreezeRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ???????????????????????????
	 *
	 * @param model {@link AlipayFundAuthOrderVoucherCreateModel}
	 * @return {@link AlipayFundAuthOrderVoucherCreateResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundAuthOrderVoucherCreateResponse authOrderVoucherCreateToResponse(AlipayFundAuthOrderVoucherCreateModel model) throws AlipayApiException {
		AlipayFundAuthOrderVoucherCreateRequest request = new AlipayFundAuthOrderVoucherCreateRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ???????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayFundAuthOrderVoucherCreateModel}
	 * @return {@link AlipayFundAuthOrderVoucherCreateResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundAuthOrderVoucherCreateResponse authOrderVoucherCreateToResponse(AlipayClient alipayClient, Boolean certModel, AlipayFundAuthOrderVoucherCreateModel model) throws AlipayApiException {
		AlipayFundAuthOrderVoucherCreateRequest request = new AlipayFundAuthOrderVoucherCreateRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}


	/**
	 * ????????????????????????
	 *
	 * @param model {@link AlipayFundAuthOperationCancelModel}
	 * @return {@link AlipayFundAuthOperationCancelResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundAuthOperationCancelResponse authOperationCancelToResponse(AlipayFundAuthOperationCancelModel model) throws AlipayApiException {
		AlipayFundAuthOperationCancelRequest request = new AlipayFundAuthOperationCancelRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayFundAuthOperationCancelModel}
	 * @return {@link AlipayFundAuthOperationCancelResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundAuthOperationCancelResponse authOperationCancelToResponse(AlipayClient alipayClient, Boolean certModel, AlipayFundAuthOperationCancelModel model) throws AlipayApiException {
		AlipayFundAuthOperationCancelRequest request = new AlipayFundAuthOperationCancelRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param model {@link AlipayFundAuthOperationDetailQueryModel}
	 * @return {@link AlipayFundAuthOperationDetailQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundAuthOperationDetailQueryResponse authOperationDetailQueryToResponse(AlipayFundAuthOperationDetailQueryModel model) throws AlipayApiException {
		AlipayFundAuthOperationDetailQueryRequest request = new AlipayFundAuthOperationDetailQueryRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayFundAuthOperationDetailQueryModel}
	 * @return {@link AlipayFundAuthOperationDetailQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundAuthOperationDetailQueryResponse authOperationDetailQueryToResponse(AlipayClient alipayClient, Boolean certModel, AlipayFundAuthOperationDetailQueryModel model) throws AlipayApiException {
		AlipayFundAuthOperationDetailQueryRequest request = new AlipayFundAuthOperationDetailQueryRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ????????????????????????
	 *
	 * @param model {@link AlipayFundCouponOrderAppPayModel}
	 * @return {@link AlipayFundCouponOrderAppPayResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundCouponOrderAppPayResponse fundCouponOrderAppPayToResponse(AlipayFundCouponOrderAppPayModel model) throws AlipayApiException {
		AlipayFundCouponOrderAppPayRequest request = new AlipayFundCouponOrderAppPayRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayFundCouponOrderAppPayModel}
	 * @return {@link AlipayFundCouponOrderAppPayResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundCouponOrderAppPayResponse fundCouponOrderAppPayToResponse(AlipayClient alipayClient, Boolean certModel, AlipayFundCouponOrderAppPayModel model) throws AlipayApiException {
		AlipayFundCouponOrderAppPayRequest request = new AlipayFundCouponOrderAppPayRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}


	/**
	 * ????????????????????????
	 *
	 * @param model {@link AlipayFundCouponOrderPagePayModel}
	 * @return {@link AlipayFundCouponOrderPagePayResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundCouponOrderPagePayResponse fundCouponOrderPagePayToResponse(AlipayFundCouponOrderPagePayModel model) throws AlipayApiException {
		AlipayFundCouponOrderPagePayRequest request = new AlipayFundCouponOrderPagePayRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayFundCouponOrderPagePayModel}
	 * @return {@link AlipayFundCouponOrderPagePayResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundCouponOrderPagePayResponse fundCouponOrderPagePayToResponse(AlipayClient alipayClient, Boolean certModel, AlipayFundCouponOrderPagePayModel model) throws AlipayApiException {
		AlipayFundCouponOrderPagePayRequest request = new AlipayFundCouponOrderPagePayRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}


	/**
	 * ????????????????????????
	 *
	 * @param model {@link AlipayFundCouponOrderAgreementPayModel}
	 * @return {@link AlipayFundCouponOrderAgreementPayResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundCouponOrderAgreementPayResponse fundCouponOrderAgreementPayToResponse(AlipayFundCouponOrderAgreementPayModel model) throws AlipayApiException {
		AlipayFundCouponOrderAgreementPayRequest request = new AlipayFundCouponOrderAgreementPayRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayFundCouponOrderAgreementPayModel}
	 * @return {@link AlipayFundCouponOrderAgreementPayResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundCouponOrderAgreementPayResponse fundCouponOrderAgreementPayToResponse(AlipayClient alipayClient, Boolean certModel, AlipayFundCouponOrderAgreementPayModel model) throws AlipayApiException {
		AlipayFundCouponOrderAgreementPayRequest request = new AlipayFundCouponOrderAgreementPayRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????
	 *
	 * @param model {@link AlipayFundCouponOrderDisburseModel}
	 * @return {@link AlipayFundCouponOrderDisburseResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundCouponOrderDisburseResponse fundCouponOrderDisburseToResponse(AlipayFundCouponOrderDisburseModel model) throws AlipayApiException {
		AlipayFundCouponOrderDisburseRequest request = new AlipayFundCouponOrderDisburseRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ??????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayFundCouponOrderDisburseModel}
	 * @return {@link AlipayFundCouponOrderDisburseResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundCouponOrderDisburseResponse fundCouponOrderDisburseToResponse(AlipayClient alipayClient, Boolean certModel, AlipayFundCouponOrderDisburseModel model) throws AlipayApiException {
		AlipayFundCouponOrderDisburseRequest request = new AlipayFundCouponOrderDisburseRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????
	 *
	 * @param model {@link AlipayFundCouponOrderRefundModel}
	 * @return {@link AlipayFundCouponOrderRefundResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundCouponOrderRefundResponse fundCouponOrderRefundToResponse(AlipayFundCouponOrderRefundModel model) throws AlipayApiException {
		AlipayFundCouponOrderRefundRequest request = new AlipayFundCouponOrderRefundRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ??????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayFundCouponOrderRefundModel}
	 * @return {@link AlipayFundCouponOrderRefundResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundCouponOrderRefundResponse fundCouponOrderRefundToResponse(AlipayClient alipayClient, Boolean certModel, AlipayFundCouponOrderRefundModel model) throws AlipayApiException {
		AlipayFundCouponOrderRefundRequest request = new AlipayFundCouponOrderRefundRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????
	 *
	 * @param model {@link AlipayFundCouponOperationQueryModel}
	 * @return {@link AlipayFundCouponOperationQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundCouponOperationQueryResponse fundCouponOperationQueryToResponse(AlipayFundCouponOperationQueryModel model) throws AlipayApiException {
		AlipayFundCouponOperationQueryRequest request = new AlipayFundCouponOperationQueryRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ??????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayFundCouponOperationQueryModel}
	 * @return {@link AlipayFundCouponOperationQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayFundCouponOperationQueryResponse fundCouponOperationQueryToResponse(AlipayClient alipayClient, Boolean certModel, AlipayFundCouponOperationQueryModel model) throws AlipayApiException {
		AlipayFundCouponOperationQueryRequest request = new AlipayFundCouponOperationQueryRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ???????????? URL ??????
	 *
	 * @param appId       ????????????
	 * @param redirectUri ?????? URI
	 * @return ???????????? URL
	 * @throws UnsupportedEncodingException ????????????
	 */
	public static String getOauth2Url(String appId, String redirectUri) throws UnsupportedEncodingException {
		return new StringBuffer().append("https://openauth.alipay.com/oauth2/appToAppAuth.htm?app_id=").append(appId).append("&redirect_uri=").append(URLEncoder.encode(redirectUri, "UTF-8"))
			.toString();
	}

	/**
	 * ?????? app_auth_code ?????? app_auth_token
	 *
	 * @param model {@link AlipayOpenAuthTokenAppModel}
	 * @return {@link AlipayOpenAuthTokenAppResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayOpenAuthTokenAppResponse openAuthTokenAppToResponse(AlipayOpenAuthTokenAppModel model) throws AlipayApiException {
		AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ?????? app_auth_code ?????? app_auth_token
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayOpenAuthTokenAppModel}
	 * @return {@link AlipayOpenAuthTokenAppResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayOpenAuthTokenAppResponse openAuthTokenAppToResponse(AlipayClient alipayClient, Boolean certModel, AlipayOpenAuthTokenAppModel model) throws AlipayApiException {
		AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????
	 *
	 * @param model {@link AlipayOpenAuthTokenAppQueryModel}
	 * @return {@link AlipayOpenAuthTokenAppQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayOpenAuthTokenAppQueryResponse openAuthTokenAppQueryToResponse(AlipayOpenAuthTokenAppQueryModel model) throws AlipayApiException {
		AlipayOpenAuthTokenAppQueryRequest request = new AlipayOpenAuthTokenAppQueryRequest();
		request.setBizModel(model);
		return doExecute(request);
	}


	/**
	 * ??????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayOpenAuthTokenAppQueryModel}
	 * @return {@link AlipayOpenAuthTokenAppQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayOpenAuthTokenAppQueryResponse openAuthTokenAppQueryToResponse(AlipayClient alipayClient, Boolean certModel, AlipayOpenAuthTokenAppQueryModel model) throws AlipayApiException {
		AlipayOpenAuthTokenAppQueryRequest request = new AlipayOpenAuthTokenAppQueryRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????
	 *
	 * @param model {@link AlipayCommerceCityfacilitatorVoucherGenerateModel}
	 * @return {@link AlipayCommerceCityfacilitatorVoucherGenerateResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayCommerceCityfacilitatorVoucherGenerateResponse voucherGenerateToResponse(AlipayCommerceCityfacilitatorVoucherGenerateModel model) throws AlipayApiException {
		AlipayCommerceCityfacilitatorVoucherGenerateRequest request = new AlipayCommerceCityfacilitatorVoucherGenerateRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ??????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayCommerceCityfacilitatorVoucherGenerateModel}
	 * @return {@link AlipayCommerceCityfacilitatorVoucherGenerateResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayCommerceCityfacilitatorVoucherGenerateResponse voucherGenerateToResponse(AlipayClient alipayClient, Boolean certModel, AlipayCommerceCityfacilitatorVoucherGenerateModel model) throws AlipayApiException {
		AlipayCommerceCityfacilitatorVoucherGenerateRequest request = new AlipayCommerceCityfacilitatorVoucherGenerateRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}


	/**
	 * ????????????????????????
	 *
	 * @param model {@link AlipayCommerceCityfacilitatorVoucherRefundModel}
	 * @return {@link AlipayCommerceCityfacilitatorVoucherRefundResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayCommerceCityfacilitatorVoucherRefundResponse metroRefundToResponse(AlipayCommerceCityfacilitatorVoucherRefundModel model) throws AlipayApiException {
		AlipayCommerceCityfacilitatorVoucherRefundRequest request = new AlipayCommerceCityfacilitatorVoucherRefundRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayCommerceCityfacilitatorVoucherRefundModel}
	 * @return {@link AlipayCommerceCityfacilitatorVoucherRefundResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayCommerceCityfacilitatorVoucherRefundResponse metroRefundToResponse(AlipayClient alipayClient, Boolean certModel, AlipayCommerceCityfacilitatorVoucherRefundModel model) throws AlipayApiException {
		AlipayCommerceCityfacilitatorVoucherRefundRequest request = new AlipayCommerceCityfacilitatorVoucherRefundRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ????????????????????????
	 *
	 * @param model {@link AlipayCommerceCityfacilitatorStationQueryModel}
	 * @return {@link AlipayCommerceCityfacilitatorStationQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayCommerceCityfacilitatorStationQueryResponse stationQueryToResponse(AlipayCommerceCityfacilitatorStationQueryModel model) throws AlipayApiException {
		AlipayCommerceCityfacilitatorStationQueryRequest request = new AlipayCommerceCityfacilitatorStationQueryRequest();
		request.setBizModel(model);
		return doExecute(request);
	}


	/**
	 * ????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayCommerceCityfacilitatorStationQueryModel}
	 * @return {@link AlipayCommerceCityfacilitatorStationQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayCommerceCityfacilitatorStationQueryResponse stationQueryToResponse(AlipayClient alipayClient, Boolean certModel, AlipayCommerceCityfacilitatorStationQueryModel model) throws AlipayApiException {
		AlipayCommerceCityfacilitatorStationQueryRequest request = new AlipayCommerceCityfacilitatorStationQueryRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}


	/**
	 * ?????????????????????
	 *
	 * @param model {@link AlipayCommerceCityfacilitatorVoucherBatchqueryModel}
	 * @return {@link AlipayCommerceCityfacilitatorVoucherBatchqueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayCommerceCityfacilitatorVoucherBatchqueryResponse voucherBatchqueryToResponse(AlipayCommerceCityfacilitatorVoucherBatchqueryModel model) throws AlipayApiException {
		AlipayCommerceCityfacilitatorVoucherBatchqueryRequest request = new AlipayCommerceCityfacilitatorVoucherBatchqueryRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ?????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayCommerceCityfacilitatorVoucherBatchqueryModel}
	 * @return {@link AlipayCommerceCityfacilitatorVoucherBatchqueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayCommerceCityfacilitatorVoucherBatchqueryResponse voucherBatchqueryToResponse(AlipayClient alipayClient, Boolean certModel, AlipayCommerceCityfacilitatorVoucherBatchqueryModel model) throws AlipayApiException {
		AlipayCommerceCityfacilitatorVoucherBatchqueryRequest request = new AlipayCommerceCityfacilitatorVoucherBatchqueryRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}


	public static void batchTrans(Map<String, String> params, String privateKey, String signType, HttpServletResponse response) throws IOException {
		params.put("service", "batch_trans_notify");
		params.put("_input_charset", "UTF-8");
		params.put("pay_date", DateUtil.format(new Date(), "YYYYMMDD"));
		Map<String, String> param = AliPayCore.buildRequestPara(params, privateKey, signType);
		response.sendRedirect(GATEWAY_NEW.concat(AliPayCore.createLinkString(param)));
	}

	/**
	 * ?????????????????????????????????Map
	 *
	 * @param request {HttpServletRequest}
	 * @return ????????????Map
	 */
	public static Map<String, String> toMap(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
			String name = iter.next();
			String[] values = requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		return params;
	}

	/**
	 * ????????????????????????
	 *
	 * @param orderType       ?????????????????????
	 * @param merchantOrderNo ???????????????
	 * @return {@link AlipayEbppBillGetResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayEbppBillGetResponse ebppBillGet(String orderType, String merchantOrderNo) throws AlipayApiException {
		AlipayEbppBillGetRequest request = new AlipayEbppBillGetRequest();
		request.setOrderType(orderType);
		request.setMerchantOrderNo(merchantOrderNo);
		return doExecute(request);
	}

	/**
	 * ????????????????????????
	 *
	 * @param alipayClient    {@link AlipayClient}
	 * @param certModel       ??????????????????
	 * @param orderType       ?????????????????????
	 * @param merchantOrderNo ???????????????
	 * @return {@link AlipayEbppBillGetResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayEbppBillGetResponse ebppBillGet(AlipayClient alipayClient, Boolean certModel, String orderType, String merchantOrderNo) throws AlipayApiException {
		AlipayEbppBillGetRequest request = new AlipayEbppBillGetRequest();
		request.setOrderType(orderType);
		request.setMerchantOrderNo(merchantOrderNo);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * H5?????????????????????
	 *
	 * @param model {@link ZolozIdentificationUserWebInitializeModel}
	 * @return {@link ZolozIdentificationUserWebInitializeResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static ZolozIdentificationUserWebInitializeResponse identificationUserWebInitialize(ZolozIdentificationUserWebInitializeModel model) throws AlipayApiException {
		ZolozIdentificationUserWebInitializeRequest request = new ZolozIdentificationUserWebInitializeRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * H5?????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link ZolozIdentificationUserWebInitializeModel}
	 * @return {@link ZolozIdentificationUserWebInitializeResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static ZolozIdentificationUserWebInitializeResponse identificationUserWebInitialize(AlipayClient alipayClient, Boolean certModel, ZolozIdentificationUserWebInitializeModel model) throws AlipayApiException {
		ZolozIdentificationUserWebInitializeRequest request = new ZolozIdentificationUserWebInitializeRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * H5??????????????????
	 *
	 * @param model {@link ZolozIdentificationUserWebQueryModel}
	 * @return {@link ZolozIdentificationUserWebQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static ZolozIdentificationUserWebQueryResponse identificationUserWebInitialize(ZolozIdentificationUserWebQueryModel model) throws AlipayApiException {
		ZolozIdentificationUserWebQueryRequest request = new ZolozIdentificationUserWebQueryRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * H5??????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link ZolozIdentificationUserWebQueryModel}
	 * @return {@link ZolozIdentificationUserWebQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static ZolozIdentificationUserWebQueryResponse identificationUserWebInitialize(AlipayClient alipayClient, Boolean certModel, ZolozIdentificationUserWebQueryModel model) throws AlipayApiException {
		ZolozIdentificationUserWebQueryRequest request = new ZolozIdentificationUserWebQueryRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ????????????
	 *
	 * @param model {@link ZolozAuthenticationCustomerFacemanageCreateModel}
	 * @return {@link ZolozAuthenticationCustomerFacemanageCreateResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static ZolozAuthenticationCustomerFacemanageCreateResponse authenticationCustomerFaceManageCreate(ZolozAuthenticationCustomerFacemanageCreateModel model) throws AlipayApiException {
		ZolozAuthenticationCustomerFacemanageCreateRequest request = new ZolozAuthenticationCustomerFacemanageCreateRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link ZolozAuthenticationCustomerFacemanageCreateModel}
	 * @return {@link ZolozAuthenticationCustomerFacemanageCreateResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static ZolozAuthenticationCustomerFacemanageCreateResponse authenticationCustomerFaceManageCreate(AlipayClient alipayClient, Boolean certModel, ZolozAuthenticationCustomerFacemanageCreateModel model) throws AlipayApiException {
		ZolozAuthenticationCustomerFacemanageCreateRequest request = new ZolozAuthenticationCustomerFacemanageCreateRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ????????????
	 *
	 * @param model {@link ZolozAuthenticationCustomerFacemanageDeleteModel}
	 * @return {@link ZolozAuthenticationCustomerFacemanageDeleteResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static ZolozAuthenticationCustomerFacemanageDeleteResponse authenticationCustomerFaceManageDelete(ZolozAuthenticationCustomerFacemanageDeleteModel model) throws AlipayApiException {
		ZolozAuthenticationCustomerFacemanageDeleteRequest request = new ZolozAuthenticationCustomerFacemanageDeleteRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link ZolozAuthenticationCustomerFacemanageDeleteModel}
	 * @return {@link ZolozAuthenticationCustomerFacemanageDeleteResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static ZolozAuthenticationCustomerFacemanageDeleteResponse authenticationCustomerFaceManageDelete(AlipayClient alipayClient, Boolean certModel, ZolozAuthenticationCustomerFacemanageDeleteModel model) throws AlipayApiException {
		ZolozAuthenticationCustomerFacemanageDeleteRequest request = new ZolozAuthenticationCustomerFacemanageDeleteRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ?????? ftoken ??????????????????
	 *
	 * @param model {@link ZolozAuthenticationCustomerFtokenQueryModel}
	 * @return {@link ZolozAuthenticationCustomerFtokenQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static ZolozAuthenticationCustomerFtokenQueryResponse authenticationCustomerFTokenQuery(ZolozAuthenticationCustomerFtokenQueryModel model) throws AlipayApiException {
		ZolozAuthenticationCustomerFtokenQueryRequest request = new ZolozAuthenticationCustomerFtokenQueryRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ?????? ftoken ??????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link ZolozAuthenticationCustomerFtokenQueryModel}
	 * @return {@link ZolozAuthenticationCustomerFtokenQueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static ZolozAuthenticationCustomerFtokenQueryResponse authenticationCustomerFTokenQuery(AlipayClient alipayClient, Boolean certModel, ZolozAuthenticationCustomerFtokenQueryModel model) throws AlipayApiException {
		ZolozAuthenticationCustomerFtokenQueryRequest request = new ZolozAuthenticationCustomerFtokenQueryRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}


	/**
	 * ????????????????????????
	 *
	 * @param model {@link ZolozAuthenticationSmilepayInitializeModel}
	 * @return {@link ZolozAuthenticationSmilepayInitializeResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static ZolozAuthenticationSmilepayInitializeResponse authenticationSmilePayInitialize(ZolozAuthenticationSmilepayInitializeModel model) throws AlipayApiException {
		ZolozAuthenticationSmilepayInitializeRequest request = new ZolozAuthenticationSmilepayInitializeRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ????????????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link ZolozAuthenticationSmilepayInitializeModel}
	 * @return {@link ZolozAuthenticationSmilepayInitializeResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static ZolozAuthenticationSmilepayInitializeResponse authenticationSmilePayInitialize(AlipayClient alipayClient, Boolean certModel, ZolozAuthenticationSmilepayInitializeModel model) throws AlipayApiException {
		ZolozAuthenticationSmilepayInitializeRequest request = new ZolozAuthenticationSmilepayInitializeRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ?????????????????????zim
	 *
	 * @param model {@link ZolozAuthenticationCustomerSmilepayInitializeModel}
	 * @return {@link ZolozAuthenticationCustomerSmilepayInitializeResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static ZolozAuthenticationCustomerSmilepayInitializeResponse authenticationCustomerSmilePayInitialize(ZolozAuthenticationCustomerSmilepayInitializeModel model) throws AlipayApiException {
		ZolozAuthenticationCustomerSmilepayInitializeRequest request = new ZolozAuthenticationCustomerSmilepayInitializeRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ?????????????????????zim
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link ZolozAuthenticationCustomerSmilepayInitializeModel}
	 * @return {@link ZolozAuthenticationCustomerSmilepayInitializeResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static ZolozAuthenticationCustomerSmilepayInitializeResponse authenticationCustomerSmilePayInitialize(AlipayClient alipayClient, Boolean certModel, ZolozAuthenticationCustomerSmilepayInitializeModel model) throws AlipayApiException {
		ZolozAuthenticationCustomerSmilepayInitializeRequest request = new ZolozAuthenticationCustomerSmilepayInitializeRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????ISV???????????????
	 *
	 * @return {@link AlipayCommerceAdContractSignResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayCommerceAdContractSignResponse commerceAdContractSign() throws AlipayApiException {
		AlipayCommerceAdContractSignRequest request = new AlipayCommerceAdContractSignRequest();
		return doExecute(request);
	}

	/**
	 * ??????????????????ISV???????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @return {@link AlipayCommerceAdContractSignResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayCommerceAdContractSignResponse commerceAdContractSign(AlipayClient alipayClient, Boolean certModel) throws AlipayApiException {
		AlipayCommerceAdContractSignRequest request = new AlipayCommerceAdContractSignRequest();
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????
	 *
	 * @param model {@link AlipayTradeRoyaltyRelationBindModel}
	 * @return {@link AlipayTradeRoyaltyRelationBindResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeRoyaltyRelationBindResponse tradeRoyaltyRelationBind(AlipayTradeRoyaltyRelationBindModel model) throws AlipayApiException {
		AlipayTradeRoyaltyRelationBindRequest request = new AlipayTradeRoyaltyRelationBindRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ??????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayTradeRoyaltyRelationBindModel}
	 * @return {@link AlipayTradeRoyaltyRelationBindResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeRoyaltyRelationBindResponse tradeRoyaltyRelationBind(AlipayClient alipayClient, Boolean certModel, AlipayTradeRoyaltyRelationBindModel model) throws AlipayApiException {
		AlipayTradeRoyaltyRelationBindRequest request = new AlipayTradeRoyaltyRelationBindRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????
	 *
	 * @param model {@link AlipayTradeRoyaltyRelationUnbindModel}
	 * @return {@link AlipayTradeRoyaltyRelationUnbindResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeRoyaltyRelationUnbindResponse tradeRoyaltyRelationUnBind(AlipayTradeRoyaltyRelationUnbindModel model) throws AlipayApiException {
		AlipayTradeRoyaltyRelationUnbindRequest request = new AlipayTradeRoyaltyRelationUnbindRequest();
		request.setBizModel(model);
		return doExecute(request);
	}

	/**
	 * ??????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayTradeRoyaltyRelationUnbindModel}
	 * @return {@link AlipayTradeRoyaltyRelationUnbindResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeRoyaltyRelationUnbindResponse tradeRoyaltyRelationUnBind(AlipayClient alipayClient, Boolean certModel, AlipayTradeRoyaltyRelationUnbindModel model) throws AlipayApiException {
		AlipayTradeRoyaltyRelationUnbindRequest request = new AlipayTradeRoyaltyRelationUnbindRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

	/**
	 * ??????????????????
	 *
	 * @param model {@link AlipayTradeRoyaltyRelationBatchqueryModel}
	 * @return {@link AlipayTradeRoyaltyRelationBatchqueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeRoyaltyRelationBatchqueryResponse tradeRoyaltyRelationBatchQuery(AlipayTradeRoyaltyRelationBatchqueryModel model) throws AlipayApiException {
		AlipayTradeRoyaltyRelationBatchqueryRequest request = new AlipayTradeRoyaltyRelationBatchqueryRequest();
		request.setBizModel(model);
		return doExecute(request);
	}


	/**
	 * ??????????????????
	 *
	 * @param alipayClient {@link AlipayClient}
	 * @param certModel    ??????????????????
	 * @param model        {@link AlipayTradeRoyaltyRelationBatchqueryModel}
	 * @return {@link AlipayTradeRoyaltyRelationBatchqueryResponse}
	 * @throws AlipayApiException ????????? Api ??????
	 */
	public static AlipayTradeRoyaltyRelationBatchqueryResponse tradeRoyaltyRelationBatchQuery(AlipayClient alipayClient, Boolean certModel, AlipayTradeRoyaltyRelationBatchqueryModel model) throws AlipayApiException {
		AlipayTradeRoyaltyRelationBatchqueryRequest request = new AlipayTradeRoyaltyRelationBatchqueryRequest();
		request.setBizModel(model);
		return doExecute(alipayClient, certModel, request);
	}

}
